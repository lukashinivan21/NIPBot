package tgbots.nipbot.service.mentions;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tgbots.nipbot.constants.StringConstants;
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

    private static final int DAYS_TEST_PERIOD = 30;
    private static final int EXTRA_DAYS = 14;
    private static final int LOWER_BORDER = 1;
    private static final int UPPER_BORDER = 3;
    private static final int LOWER_BORDER_FOR_VOL = 2;
    private static final int UPPER_BORDER_FOR_VOL = 5;

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
    @Transactional
    public List<Long> idsForMentionToSendReport() {

        LocalDate rightNow = LocalDate.now();
        List<Long> resultIds = new ArrayList<>();

//        Список id пользователей из базы приюта для собак, которые проходят испытательный срок и у которых один и более отправленный отчет
        List<Long> dogIds = idsDogCandidate();

//        Список id пользователей из базы приюта для кошек, которые проходят испытательный срок и у которых один и более отправленный отчет
        List<Long> catIds = idsCatCandidate();

//        Вычисление разницы в днях между текущей датой и датой последнего отправленного отчета пользователя приюта для собак.
//        Если разница больше одного дня и <=3 дня, то id пользователя попадает в общий список тех, кому нужно отправить напоминание.
        if (!dogIds.isEmpty()) {
            for (Long id : dogIds) {
                int diff = getPeriodDog(rightNow, id);
                if (diff > LOWER_BORDER && diff <= UPPER_BORDER) {
                    resultIds.add(id);
                }
            }
        }

//        Вычисление разницы в днях между текущей датой и датой последнего отправленного отчета пользователя приюта для кошек.
//        Если разница больше одного дня и <=3 дня, то id пользователя попадает в общий список тех, кому нужно отправить напоминание.
        if (!catIds.isEmpty()) {
            for (Long id : catIds) {
                int diff = getPeriodCat(rightNow, id);
                if (diff > LOWER_BORDER && diff <= UPPER_BORDER) {
                    resultIds.add(id);
                }
            }
        }

        return resultIds;
    }


    @Override
    @Transactional
    public String mentionForVolunteer() {

        LocalDate rightNow = LocalDate.now();

        List<Long> resultDogIds = new ArrayList<>();
        List<Long> resultCatIds = new ArrayList<>();

//        Список id пользователей из базы приюта для собак, которые проходят испытательный срок и у которых один и более отправленный отчет
        List<Long> dogIds = idsDogCandidate();

//        Список id пользователей из базы приюта для кошек, которые проходят испытательный срок и у которых один и более отправленный отчет
        List<Long> catIds = idsCatCandidate();

//        Вычисление разницы в днях между текущей датой и датой последнего отправленного отчета пользователя приюта для собак.
//        Если разница больше двух дней и <=5 дней, то id пользователя попадает в список тех, с кем волонтер должен связаться напрямую.
        if (!dogIds.isEmpty()) {
            for (Long id : dogIds) {
                int diff = getPeriodDog(rightNow, id);
                if (diff > LOWER_BORDER_FOR_VOL && diff <= UPPER_BORDER_FOR_VOL) {
                    resultDogIds.add(id);
                }
            }
        }

//        Вычисление разницы в днях между текущей датой и датой последнего отправленного отчета пользователя приюта для кошек.
//        Если разница больше двух дней и <=5 дней, то id пользователя попадает в список тех, с кем волонтер должен связаться напрямую.
        if (!catIds.isEmpty()) {
            for (Long id : catIds) {
                int diff = getPeriodCat(rightNow, id);
                if (diff > LOWER_BORDER_FOR_VOL && diff <= UPPER_BORDER_FOR_VOL) {
                    resultCatIds.add(id);
                }
            }
        }

        StringBuilder sbDog = new StringBuilder();
        StringBuilder sbCat = new StringBuilder();

        if (!resultDogIds.isEmpty()) {
            for (Long id : resultDogIds) {
                DogCandidate candidate = dogCandidateRepository.findDogCandidateById(id);
                sbDog.append("\n").append(candidate.toString()).append(";");
            }
        }

        if (!resultCatIds.isEmpty()) {
            for (Long id : resultCatIds) {
                CatCandidate candidate = catCandidateRepository.findCatCandidateById(id);
                sbCat.append("\n").append(candidate.toString()).append(";");
            }
        }

//        Итоговая строка-перечень всех пользователей приюта для собак
        String dogResult = sbDog.toString();
//        Итоговая строка-перечень всех пользователей приюта для кошек
        String catResult = sbCat.toString();

        String result = "";

        if (!dogResult.isEmpty() && !catResult.isEmpty()) {
            result = StringConstants.mentionToVol1(dogResult, catResult);
        }

        if (dogResult.isEmpty() && !catResult.isEmpty()) {
            result = StringConstants.mentionToVol1("У пользователей питомника нет задолжностей по отчетам", catResult);
        }

        if (!dogResult.isEmpty() && catResult.isEmpty()) {
            result = StringConstants.mentionToVol1(dogResult, "У пользователей питомника нет задолжностей по отчетам");
        }

        return result;
    }


    @Override
    @Transactional
    public String mentionForVolunteerAboutTestPeriod() {

        LocalDate rightNow = LocalDate.now();

        List<Long> resultDogIds = new ArrayList<>();
        List<Long> resultCatIds = new ArrayList<>();

        List<Long> dogIds = idsDogCandidate();
        List<Long> catIds = idsCatCandidate();

//        Вычисление разницы в днях между текущей датой и датой первого отправленного отчета пользователя приюта для собак.
//        Если разница равна 30 дням или больше 30 дней на 14 или 30 дней, то id пользователя попадает в список пользователей, по испытательным срокам которых нужно принять решение.
        if (!dogIds.isEmpty()) {
            for (Long id : dogIds) {
                List<DogReport> dogReports = reportDogRepository.findDogReportsByDogCandidateId(id).stream().sorted(Comparator.comparing(DogReport::getDate)).toList();
                LocalDate date = dogReports.get(0).getDate();
                Period period = Period.between(rightNow, date);
                int diff = Math.abs(period.getDays());
                if (diff == DAYS_TEST_PERIOD || diff == DAYS_TEST_PERIOD + EXTRA_DAYS || diff == DAYS_TEST_PERIOD + DAYS_TEST_PERIOD) {
                    resultDogIds.add(id);
                }
            }
        }

//        Вычисление разницы в днях между текущей датой и датой первого отправленного отчета пользователя приюта для кошек.
//        Если разница равна 30 дням или больше 30 дней на 14 или 30 дней, то id пользователя попадает в список пользователей, по испытательным срокам которых нужно принять решение.
        if (!catIds.isEmpty()) {
            for (Long id : catIds) {
                List<CatReport> catReports = reportCatRepository.findCatReportByCatCandidate_Id(id).stream().sorted(Comparator.comparing(CatReport::getDate)).toList();
                LocalDate date = catReports.get(0).getDate();
                Period period = Period.between(rightNow, date);
                int diff = Math.abs(period.getDays());
                if (diff == DAYS_TEST_PERIOD || diff == DAYS_TEST_PERIOD + EXTRA_DAYS || diff == DAYS_TEST_PERIOD + DAYS_TEST_PERIOD) {
                    resultCatIds.add(id);
                }
            }
        }


        StringBuilder sbDog = new StringBuilder();
        StringBuilder sbCat = new StringBuilder();

        if (!resultDogIds.isEmpty()) {
            for (Long id : resultDogIds) {
                DogCandidate candidate = dogCandidateRepository.findDogCandidateById(id);
                sbDog.append("\n").append(candidate.toString()).append(";");
            }
        }

        if (!resultCatIds.isEmpty()) {
            for (Long id : resultCatIds) {
                CatCandidate candidate = catCandidateRepository.findCatCandidateById(id);
                sbCat.append("\n").append(candidate.toString()).append(";");
            }
        }

//        Итоговая строка-перечень всех пользователей приюта для собак
        String dogResult = sbDog.toString();

//         Итоговая строка-перечень всех пользователей приюта для кошек
        String catResult = sbCat.toString();

        String result = "";

        if (!dogResult.isEmpty() && !catResult.isEmpty()) {
            result = StringConstants.mentionToVol2(dogResult, catResult);
        }

        if (dogResult.isEmpty() && !catResult.isEmpty()) {
            result = StringConstants.mentionToVol2("Нет кандидатов для принятия решения", catResult);
        }

        if (!dogResult.isEmpty() && catResult.isEmpty()) {
            result = StringConstants.mentionToVol1(dogResult, "Нет кандидатов для принятия решения");
        }

        return result;

    }


    private int getPeriodCat(LocalDate rightNow, Long id) {
        List<CatReport> catReports = reportCatRepository.findCatReportByCatCandidate_Id(id).stream().sorted(Comparator.comparing(CatReport::getDate)).toList();
        LocalDate date = catReports.get(catReports.size() - 1).getDate();
        Period period = Period.between(rightNow, date);
        return Math.abs(period.getDays());
    }

    private int getPeriodDog(LocalDate rightNow, Long id) {
        List<DogReport> dogReports = reportDogRepository.findDogReportsByDogCandidateId(id).stream().sorted(Comparator.comparing(DogReport::getDate)).toList();
        LocalDate date = dogReports.get(dogReports.size() - 1).getDate();
        Period period = Period.between(rightNow, date);
        return Math.abs(period.getDays());
    }

    private List<Long> idsDogCandidate() {
        return dogCandidateRepository.findAll()
                .stream()
                .map(DogCandidate::getId)
                .filter(id -> !reportDogRepository.findDogReportsByDogCandidateId(id).isEmpty()).toList();
    }

    private List<Long> idsCatCandidate() {
        return catCandidateRepository.findAll()
                .stream()
                .map(CatCandidate::getId)
                .filter(id -> !reportCatRepository.findCatReportByCatCandidate_Id(id).isEmpty()).toList();
    }
}
