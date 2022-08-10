package tgbots.nipbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tgbots.nipbot.models.DogReport;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportDogRepository extends JpaRepository<DogReport, Long> {

    DogReport findDogReportById(Long id);

    List<DogReport> findDogReportsByDateAndDogCandidateId(LocalDate date, Long id);

    List<DogReport> findDogReportsByDogCandidateId(Long id);
}
