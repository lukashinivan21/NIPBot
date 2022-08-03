package tgbots.nipbot.service.by_models;

import org.springframework.stereotype.Service;
import tgbots.nipbot.models.Dog;
import tgbots.nipbot.repositories.DogRepository;
import tgbots.nipbot.service.by_models.interfaces.DogService;

import java.util.Optional;

@Service
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;

    public DogServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Dog addDog(Dog dog){
        return dogRepository.save(dog);
    }

    public Dog updateDog(Dog dog){
        return dogRepository.save(dog);
    }

    public Dog findDogById(Long id){
        Optional<Dog> dogOptional = dogRepository.findById(id);
        return dogOptional.orElse(null);
    }

    public void removeDog(Long id){
        dogRepository.deleteById(id);
    }
}
