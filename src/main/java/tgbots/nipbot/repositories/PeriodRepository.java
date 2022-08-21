package tgbots.nipbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tgbots.nipbot.models.Period;

public interface PeriodRepository extends JpaRepository<Period, Long> {
}
