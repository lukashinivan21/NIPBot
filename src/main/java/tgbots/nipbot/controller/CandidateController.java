package tgbots.nipbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tgbots.nipbot.exception.NotValidPhoneNumberException;
import tgbots.nipbot.models.Candidate;
import tgbots.nipbot.models.DogCandidate;
import tgbots.nipbot.service.Validation;
import tgbots.nipbot.service.by_models.CandidateServiceImpl;

import java.util.List;

@RestController
@RequestMapping(value = "/candidate")
public class CandidateController {

    private final CandidateServiceImpl service;

    public CandidateController(CandidateServiceImpl service) {
        this.service = service;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Candidate> createCandidate(@RequestParam Long id,
                                                     @RequestParam String firstName,
                                                     @RequestParam(required = false) String secondName,
                                                     @RequestParam String phoneNumber,
                                                     @RequestParam(required = false, defaultValue = "false") boolean addPeriod){
        Candidate candidate = new DogCandidate();
        if(Validation.PATTERN_PHONE_NUMBER.matcher(phoneNumber).matches()){
            candidate.setPhoneNumber(phoneNumber);
        } else {
            throw new NotValidPhoneNumberException();
        }
        candidate.setId(id);
        candidate.setFirstName(firstName);
        candidate.setSecondName(secondName);
        Candidate saveCandidate = service.saveCandidate(candidate, addPeriod);
        return ResponseEntity.ok().body(saveCandidate);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Candidate> updateCandidate(@RequestParam Long id,
                                                     @RequestParam String firstName,
                                                     @RequestParam(required = false) String secondName,
                                                     @RequestParam String phoneNumber){
        Candidate candidate = new DogCandidate();
        if(Validation.PATTERN_PHONE_NUMBER.matcher(phoneNumber).matches()){
            candidate.setPhoneNumber(phoneNumber);
        } else {
            throw new NotValidPhoneNumberException();
        }
        candidate.setId(id);
        candidate.setFirstName(firstName);
        candidate.setSecondName(secondName);

        Candidate saveCandidate = service.updateCandidate(candidate);
        return ResponseEntity.ok().body(saveCandidate);
    }

    @GetMapping(value = "/find")
    public ResponseEntity<Candidate> findCandidateById(@RequestParam Long id){
        Candidate candidate = service.findCandidateById(id);
        return ResponseEntity.ok().body(candidate);
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity removeCandidate(@RequestParam Long id){
        service.removeCandidate(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<DogCandidate>> findAllCandidate(){
        List<DogCandidate> candidates = service.findAll();
        return ResponseEntity.ok().body(candidates);
    }
}
