package tgbots.nipbot.service.by_models;

import org.springframework.stereotype.Service;
import tgbots.nipbot.models.Dog;
import tgbots.nipbot.repositories.DogRepository;
import tgbots.nipbot.service.by_models.interfaces.DogService;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;

    public DogServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public Dog saveDog(Dog dog){
        return dogRepository.save(dog);
    }

    @Override
    public Dog updateDog(Dog dog){
        Optional<Dog> dogOptional = dogRepository.findById(dog.getId());
        if(dogOptional.isPresent()){
            return dogRepository.save(dog);
        }
        throw new EntityExistsException();
    }

    @Override
    public Dog findDogById(Long id){
        Optional<Dog> dogOptional = dogRepository.findById(id);
        return dogOptional.orElse(null);
    }

    @Override
    public void removeDog(Long id){
        dogRepository.deleteById(id);
    }

    public List<Dog> findAll(){
        return dogRepository.findAll();
    }
}
