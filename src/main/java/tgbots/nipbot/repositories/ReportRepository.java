package tgbots.nipbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tgbots.nipbot.models.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
