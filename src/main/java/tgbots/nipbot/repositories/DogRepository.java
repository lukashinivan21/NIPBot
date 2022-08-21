package tgbots.nipbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tgbots.nipbot.models.Dog;

public interface DogRepository extends JpaRepository<Dog, Long> {
}
