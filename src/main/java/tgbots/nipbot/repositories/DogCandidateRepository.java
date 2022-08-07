package tgbots.nipbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tgbots.nipbot.models.DogCandidate;

@Repository
public interface DogCandidateRepository extends JpaRepository<DogCandidate, Long> {

    DogCandidate findDogCandidateById(Long id);
}
