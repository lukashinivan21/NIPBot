package tgbots.nipbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tgbots.nipbot.models.CatReport;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportCatRepository extends JpaRepository<CatReport, Long> {

    CatReport findCatReportById(Long id);

    CatReport findCatReportByDateAndCatCandidate_Id(LocalDate date, Long id);

    List<CatReport> findCatReportByCatCandidate_Id(Long id);

}
