package tgbots.nipbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tgbots.nipbot.models.Dog;
import tgbots.nipbot.service.by_models.DogServiceImpl;

import java.util.List;

@RestController
@RequestMapping(value = "/dog")
public class DogController {

    private final DogServiceImpl service;

    public DogController(DogServiceImpl service) {
        this.service = service;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Dog> createDog(@RequestParam String name,
                                         @RequestParam Double age){
        Dog dog = new Dog();
        dog.setName(name);
        dog.setAge(age);
        return ResponseEntity.ok().body(service.saveDog(dog));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Dog> updateDog(@RequestParam Long id,
                                         @RequestParam String name,
                                         @RequestParam Double age){
        Dog dog = new Dog();
        dog.setId(id);
        dog.setName(name);
        dog.setAge(age);
        return ResponseEntity.ok().body(service.updateDog(dog));
    }

    @GetMapping(value = "/find")
    public ResponseEntity<Dog> findDogById(@RequestParam Long id){
        return ResponseEntity.ok().body(service.findDogById(id));
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity removeDog(@RequestParam Long id){
        service.removeDog(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<Dog>> findAllDog(){
        return ResponseEntity.ok().body(service.findAll());
    }
}
