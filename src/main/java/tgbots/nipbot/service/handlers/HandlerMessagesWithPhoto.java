package tgbots.nipbot.service.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tgbots.nipbot.constants.Shelter;
import tgbots.nipbot.models.CatCandidate;
import tgbots.nipbot.models.CatReport;
import tgbots.nipbot.models.DogCandidate;
import tgbots.nipbot.models.DogReport;
import tgbots.nipbot.repositories.CatCandidateRepository;
import tgbots.nipbot.repositories.DogCandidateRepository;
import tgbots.nipbot.repositories.ReportCatRepository;
import tgbots.nipbot.repositories.ReportDogRepository;
import tgbots.nipbot.service.cache.CacheIdsUsersOnTestPeriod;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static tgbots.nipbot.constants.StringConstants.*;

@Service
public class HandlerMessagesWithPhoto implements Handler {

    private final Logger logger = LoggerFactory.getLogger(HandlerMessagesWithPhoto.class);

    private final TelegramBot bot;
    private final DogCandidateRepository dogCandidateRepository;
    private final CatCandidateRepository catCandidateRepository;
    private final ReportDogRepository reportDogRepository;
    private final ReportCatRepository reportCatRepository;
    private final CacheIdsUsersOnTestPeriod idsTestPeriod;

    public HandlerMessagesWithPhoto(TelegramBot bot,
                                    DogCandidateRepository dogCandidateRepository,
                                    CatCandidateRepository catCandidateRepository,
                                    ReportDogRepository reportDogRepository,
                                    ReportCatRepository reportCatRepository,
                                    CacheIdsUsersOnTestPeriod idsTestPeriod) {
        this.bot = bot;
        this.dogCandidateRepository = dogCandidateRepository;
        this.catCandidateRepository = catCandidateRepository;
        this.reportDogRepository = reportDogRepository;
        this.reportCatRepository = reportCatRepository;
        this.idsTestPeriod = idsTestPeriod;
    }

    /**
     * Метод обрабатывает отчеты от пользователей и сохраняет фото из отчета на жесткий диск.
     * Прочая информация по отчету сохраняется в базу данных.
     * Если в присылаемом сообщении отсутсвует подпись (текстовое содержание), то пользователю будет направлено
     * уведомление о том, что необходимо добавить подпись к фото.
     * @return {@link BaseRequest}
     */
    @Override
    public BaseRequest handle(Update update, Shelter shelter) {

        Message message = update.message();

        Long chatId = message.chat().id();

        String userName = message.from().username();

        SendMessage sendMessage;

        if (message.caption() != null) {
            String caption = message.caption();
            LocalDate dateToday = LocalDate.now();
            LocalTime timeNow = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
            String today = dateToday.toString();
            String time = timeNow.toString();
            PhotoSize[] photo = message.photo();
            String fileId = photo[0].fileId();
            GetFile request = new GetFile(fileId);
            GetFileResponse response = bot.execute(request);
            File file = response.file();
            String path = file.filePath();
            try {
                byte[] data = bot.getFileContent(file);
                uploadReport(chatId, data, file, userName, today, time, caption, path, dateToday, shelter);
                sendMessage = collectSendMessage(chatId, REPORT_OK);
                idsTestPeriod.addId(chatId);
            } catch (IOException e) {
                logger.info("Something happens...");
                e.printStackTrace();
                sendMessage = collectSendMessage(chatId, ERROR);
            }
        } else  {
            sendMessage = collectSendMessage(chatId, REPORT_NOT_FULL);
        }
        return sendMessage;
    }

    private SendMessage collectSendMessage(Long chatId, String textAnswer) {
        return new SendMessage(chatId, textAnswer);
    }

    @Value("${path.to.reports.folder}")
    private String reportsDir;

    @Value("${telegram.bot.token}")
    private String token;

//    метод, отвечающий за загрузку фото из отчетов и сохранение информации в базу данных
    private void uploadReport(Long id, byte[] data, File file, String userName, String today, String time, String caption, String path, LocalDate dateToday, Shelter shelter) throws IOException {
        logger.info("Upload report from user with id: {}, username: {}", id, userName);
//        List<Long> dogIds = dogCandidateRepository.findAll().stream().map(DogCandidate::getId).toList();
//        List<Long> catIds = catCandidateRepository.findAll().stream().map(CatCandidate::getId).toList();
        String secondFolder = null;
        if (shelter.equals(Shelter.DOG)) {
            secondFolder = "/dog_reports/";
        } else if (shelter.equals(Shelter.CAT)) {
            secondFolder = "/cat_reports/";
        }
        Path filePath = Path.of(reportsDir + secondFolder + id + " " + userName + "/" + today, userName + " " + time.replace(":", ".") + "." + getExtension(path));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        URL url = new URL("https://api.telegram.org/file/bot" + token + "/" + path);

        try (InputStream is = url.openStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }

        if (shelter.equals(Shelter.DOG)) {
            DogCandidate dogCandidate = dogCandidateRepository.findDogCandidateById(id);
            DogReport newDogReport = new DogReport();
            newDogReport.setData(data);
            newDogReport.setDate(dateToday);
            newDogReport.setCaption(caption);
            newDogReport.setFileSize(file.fileSize());
            newDogReport.setPathImage(filePath.toString());
            newDogReport.setDogCandidate(dogCandidate);

            reportDogRepository.save(newDogReport);

        } else if (shelter.equals(Shelter.CAT)) {
            CatCandidate catCandidate = catCandidateRepository.findCatCandidateById(id);
            CatReport newCatReport = new CatReport();
            newCatReport.setCatCandidate(catCandidate);
            newCatReport.setDate(dateToday);
            newCatReport.setCaption(caption);
            newCatReport.setData(data);
            newCatReport.setPathImage(filePath.toString());
            newCatReport.setFileSize(file.fileSize());

            reportCatRepository.save(newCatReport);
        }
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
