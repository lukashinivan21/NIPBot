package tgbots.nipbot.botapi.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tgbots.nipbot.repositories.VolunteerRepository;
import tgbots.nipbot.service.handlers.HandlerUpdates;
import tgbots.nipbot.service.mentions.MentionSendReport;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

import static tgbots.nipbot.constants.StringConstants.MENTION_TO_SEND_REPORT;

/**
 * Класс, содержащий логику получения обновлений с TelegramAPI,
 * выполняет request с прикрепленной логикой
 */
@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final HandlerUpdates handlerUpdates;
    private final MentionSendReport mentionSendReport;
    private final VolunteerRepository volunteerRepository;

    public TelegramBotUpdatesListener(HandlerUpdates handlerUpdates, MentionSendReport mentionSendReport, VolunteerRepository volunteerRepository) {
        this.handlerUpdates = handlerUpdates;
        this.mentionSendReport = mentionSendReport;
        this.volunteerRepository = volunteerRepository;
    }

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Метод, содержащий логику получения обновлений с TelegramAPI
     * Также метод выполняет request с прикрепленной логикой
     * @param updates, которые бот получил в чате
     * @return CONFIRMED_UPDATES_ALL
     */
    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            BaseRequest request = handlerUpdates.choiceHandler(update);
            if(request != null){
                telegramBot.execute(request);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }


    /**
     * Метод для рассылки напоминаний пользователям, находящимся на испытательном сроке,
     * и которые не пресылают отчеты более 1 и <= 3 дней.
     */
    @Scheduled(cron = "0 0 12 * * *")
    public void mentionForUserToSendReport() {
        List<Long> ids = mentionSendReport.idsForMentionToSendReport();
        if (!ids.isEmpty()) {
            ids.forEach(id -> telegramBot.execute(new SendMessage(id, MENTION_TO_SEND_REPORT)));
        }
    }


    /**
     * Метод для рассылки напоминаний волонтеру с информацией о пользователях, находящихся на испытательном сроке,
     * и которые не присылают отчеты более 2 и <= 5 дней.
     * Волонтер выбирается случайным образом из общего списка волонтеров.
     */
    @Scheduled(cron = "0 30 10 * * *")
    public void mentionToVolunteer1() {
        String result = mentionSendReport.mentionForVolunteer();
        int size = volunteerRepository.findAll().size();
        if (size > 0 && !result.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(0, size);
            Long volunteerId = volunteerRepository.findAll().get(index).getId();
            telegramBot.execute(new SendMessage(volunteerId, result));
        }
    }

    /**
     * Метод для рассылки напоминаний волонтеру со списком пользователей, по которым нужно принять решение:
     * прошел пользователь испытательный срок или нет, или испытательный срок будет продлен.
     * Волонтер выбирается случайным образом из общего списка волонтеров.
     */
    @Scheduled(cron = "0 15 11 * * * ")
    public void mentionToVolunteerAboutTestPeriod() {
        String result = mentionSendReport.mentionForVolunteerAboutTestPeriod();
        int size = volunteerRepository.findAll().size();
        if (size > 0 && !result.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(0, size);
            Long volunteerId = volunteerRepository.findAll().get(index).getId();
            telegramBot.execute(new SendMessage(volunteerId, result));
        }
    }

}
