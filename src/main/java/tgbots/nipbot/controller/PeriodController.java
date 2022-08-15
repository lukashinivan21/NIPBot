package tgbots.nipbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tgbots.nipbot.constants.Shelter;
import tgbots.nipbot.models.Period;
import tgbots.nipbot.service.by_models.PeriodServiceImpl;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/period")
public class PeriodController {
    
    private final PeriodServiceImpl service;

    public PeriodController(PeriodServiceImpl service) {
        this.service = service;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Period> createPeriod(@RequestParam Long id,
                                               @RequestParam(required = false, defaultValue = "30") Integer trialDays){
        Period period = new Period();
        period.setId(id);
        period.setStartDate(LocalDate.now());
        period.setTrialDays(trialDays);
        Period periodToSave = service.savePeriod(period);
        return ResponseEntity.ok().body(periodToSave);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Period> updatePeriod(@RequestParam Long id,
                                               @RequestParam Integer trialDays,
                                               @RequestParam(required = false, defaultValue = "0") Integer extraDays){
        Period period = new Period();
        period.setId(id);
        period.setTrialDays(trialDays);
        period.setExtraDays(extraDays);
        Period periodToSave = service.updatePeriod(period);
        return ResponseEntity.ok().body(periodToSave);
    }

    @GetMapping(value = "/find")
    public ResponseEntity<Period> findPeriodById(@RequestParam Long id){
        Period period = service.findPeriodById(id);
        return ResponseEntity.ok().body(period);
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity removePeriod(@RequestParam Long id){
        service.removePeriod(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<Period>> findAllPeriod(){
        List<Period> periods = service.findAll();
        return ResponseEntity.ok().body(periods);
    }

    @PostMapping(value = "/add-period")
    public ResponseEntity<Period> addPeriodToCandidate(@RequestParam Long complexId,
                                                       @RequestParam(required = false, defaultValue = "DOG") String shelterText){
        Period period = service.addPeriodCandidate(complexId, Shelter.fromString(shelterText));
        return ResponseEntity.ok().body(period);
    }

    @DeleteMapping(value = "/remove-period")
    public ResponseEntity removePeriodToCandidate(@RequestParam Long complexId,
                                                  @RequestParam(required = false, defaultValue = "DOG") String shelterText){
        service.removePeriodCandidate(complexId, Shelter.fromString(shelterText));
        return ResponseEntity.ok().build();
    }
}
