package tgbots.nipbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tgbots.nipbot.models.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
