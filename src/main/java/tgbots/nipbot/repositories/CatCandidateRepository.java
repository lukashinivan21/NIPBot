package tgbots.nipbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tgbots.nipbot.models.CatCandidate;

public interface CatCandidateRepository extends JpaRepository<CatCandidate, Long> {

    CatCandidate findCatCandidateById(Long id);
}
