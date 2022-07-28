package tgbots.nipbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tgbots.nipbot.models.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
}
