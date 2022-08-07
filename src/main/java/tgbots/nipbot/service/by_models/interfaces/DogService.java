package tgbots.nipbot.service.by_models.interfaces;

import tgbots.nipbot.models.Dog;

import java.util.List;

public interface DogService {

    public Dog saveDog(Dog dog);

    public Dog updateDog(Dog dog);

    public Dog findDogById(Long id);

    public void removeDog(Long id);

    public List<Dog> findAll();
}
