package tgbots.nipbot.service.by_models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tgbots.nipbot.models.Dog;
import tgbots.nipbot.repositories.DogRepository;
import tgbots.nipbot.service.by_models.interfaces.DogService;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService {

    private final Logger log = LoggerFactory.getLogger(DogServiceImpl.class);
    private final DogRepository dogRepository;

    public DogServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public Dog saveDog(Dog dog){
        Dog dogSave = dogRepository.save(dog);
        log.info("saveDog: {} save in dogRepository", dogSave);
        return dogSave;
    }

    @Override
    public Dog updateDog(Dog dog){
        Optional<Dog> dogOptional = dogRepository.findById(dog.getId());
        if(dogOptional.isPresent()){
            Dog dogSave = dogRepository.save(dog);
            log.info("updateDog: {} update in dogRepository", dogSave);
            return dogSave;
        }
        log.error("updateDog: {} not found in dogRepository", dog);
        throw new EntityExistsException();
    }

    @Override
    public Dog findDogById(Long id){
        Optional<Dog> dogOptional = dogRepository.findById(id);
        if(dogOptional.isPresent()){
            log.info("findDogById: {} in dogRepository", dogOptional.get());
            return dogOptional.get();
        }
        log.error("findDogById: {} not found in dogRepository", dogOptional);
        throw new NotFoundException(id + " not found!");
    }

    @Override
    public void removeDog(Long id){
        dogRepository.deleteById(id);
        log.info("removeDog: {} dog removed in dogRepository", id);
    }

    @Override
    public List<Dog> findAll(){
        List<Dog> dogs = dogRepository.findAll();
        log.info("findAll: {} in dogRepository", dogs);
        return dogs;
    }
}
