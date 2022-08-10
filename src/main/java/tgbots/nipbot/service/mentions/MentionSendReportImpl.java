package tgbots.nipbot.service.mentions;

import org.springframework.stereotype.Service;
import tgbots.nipbot.models.CatCandidate;
import tgbots.nipbot.models.CatReport;
import tgbots.nipbot.models.DogCandidate;
import tgbots.nipbot.models.DogReport;
import tgbots.nipbot.repositories.CatCandidateRepository;
import tgbots.nipbot.repositories.DogCandidateRepository;
import tgbots.nipbot.repositories.ReportCatRepository;
import tgbots.nipbot.repositories.ReportDogRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MentionSendReportImpl implements MentionSendReport {

    private final DogCandidateRepository dogCandidateRepository;
    private final CatCandidateRepository catCandidateRepository;
    private final ReportDogRepository reportDogRepository;
    private final ReportCatRepository reportCatRepository;

    public MentionSendReportImpl(DogCandidateRepository dogCandidateRepository,
                                 CatCandidateRepository catCandidateRepository,
                                 ReportDogRepository reportDogRepository,
                                 ReportCatRepository reportCatRepository) {
        this.dogCandidateRepository = dogCandidateRepository;
        this.catCandidateRepository = catCandidateRepository;
        this.reportDogRepository = reportDogRepository;
        this.reportCatRepository = reportCatRepository;
    }


    @Override
    public List<Long> idsForMentionToSendReport() {

        LocalDate rightNow = LocalDate.now();
        List<Long> resultIds = new ArrayList<>();

//        Список id пользователей из базы приюта для собак, которые проходят испытательный срок и у которых один и более отправленный отчет
        List<Long> dogIds = dogCandidateRepository.findAll()
                .stream()
                .map(DogCandidate::getId)
                .filter(id -> !reportDogRepository.findDogReportsByDogCandidateId(id).isEmpty()).toList();

//        Список id пользователей из базы приюта для кошек, которые проходят испытательный срок и у которых один и более отправленный отчет
        List<Long> catIds = catCandidateRepository.findAll()
                .stream()
                .map(CatCandidate::getId)
                .filter(id -> !reportCatRepository.findCatReportByCatCandidate_Id(id).isEmpty()).toList();

//        Вычисление разницы в днях между текущей датой и датой последнего отправленного отчета пользователем приюта для собак.
//        Если разница больше одного дня, то id пользователя попадает в общий список тех, кому нужно отправить напоминание.
        if (!dogIds.isEmpty()) {
            for (Long id : dogIds) {
                List<DogReport> dogReports = reportDogRepository.findDogReportsByDogCandidateId(id)
                        .stream().sorted(Comparator.comparing(DogReport::getDate)).toList();
                LocalDate date = dogReports.get(dogReports.size() - 1).getDate();
                Period period = Period.between(rightNow, date);
                if (Math.abs(period.getDays()) > 1) {
                    resultIds.add(id);
                }
            }
        }

//        Вычисление разницы в днях между текущей датой и датой последнего отправленного отчета пользователем приюта для кошек.
//        Если разница больше одного дня, то id пользователя попадает в общий список тех, кому нужно отправить напоминание.
        if (!catIds.isEmpty()) {
            for (Long id : catIds) {
                List<CatReport> catReports = reportCatRepository.findCatReportByCatCandidate_Id(id)
                        .stream().sorted(Comparator.comparing(CatReport::getDate)).toList();
                LocalDate date = catReports.get(catReports.size() - 1).getDate();
                Period period = Period.between(rightNow, date);
                if (Math.abs(period.getDays()) > 1) {
                    resultIds.add(id);
                }
            }
        }

        return resultIds;
    }
}
