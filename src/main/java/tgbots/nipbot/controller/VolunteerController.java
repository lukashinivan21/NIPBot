package tgbots.nipbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tgbots.nipbot.exception.ExceedingLengthException;
import tgbots.nipbot.models.Volunteer;
import tgbots.nipbot.service.by_models.VolunteerServiceImpl;

import java.util.List;

@RestController
@RequestMapping(value = "/volunteer")
public class VolunteerController {
    
    private final VolunteerServiceImpl service;

    public VolunteerController(VolunteerServiceImpl service) {
        this.service = service;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Volunteer> createVolunteer(@RequestParam Long id,
                                                     @RequestParam String firstName,
                                                     @RequestParam(required = false) String secondName,
                                                     @RequestParam String password){
        if(password.length() > 16){
            throw new ExceedingLengthException();
        }
        Volunteer volunteer = new Volunteer();
        volunteer.setId(id);
        volunteer.setFirstName(firstName);
        volunteer.setSecondName(secondName);
        Volunteer saveVolunteer = service.saveVolunteer(volunteer);
        return ResponseEntity.ok().body(saveVolunteer);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Volunteer> updateVolunteer(@RequestParam Long id,
                                                     @RequestParam String firstName,
                                                     @RequestParam(required = false) String secondName,
                                                     @RequestParam(required = false) String password){
        Volunteer volunteer = new Volunteer();
        volunteer.setId(id);
        volunteer.setFirstName(firstName);
        volunteer.setSecondName(secondName);
        volunteer.setPassword(password);

        Volunteer saveVolunteer = service.updateVolunteer(volunteer);
        return ResponseEntity.ok().body(saveVolunteer);
    }

    @GetMapping(value = "/find")
    public ResponseEntity<Volunteer> findVolunteerById(@RequestParam Long id){
        Volunteer volunteer = service.findVolunteerById(id);
        return ResponseEntity.ok().body(volunteer);
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity removeVolunteer(@RequestParam Long id){
        service.removeVolunteer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<Volunteer>> findAllVolunteer(){
        List<Volunteer> volunteers = service.findAll();
        return ResponseEntity.ok().body(volunteers);
    }
}
