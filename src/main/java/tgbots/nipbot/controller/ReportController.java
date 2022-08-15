package tgbots.nipbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tgbots.nipbot.constants.Shelter;
import tgbots.nipbot.models.Report;
import tgbots.nipbot.service.by_models.ReportServiceImpl;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/report")
public class ReportController {

    private final ReportServiceImpl service;

    public ReportController(ReportServiceImpl service) {
        this.service = service;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Report> createReport(@RequestParam String pathImage,
                                               @RequestParam String diet,
                                               @RequestParam String generalHealth,
                                               @RequestParam(required = false, defaultValue = "DOG") String shelterText){
        Shelter shelter = Shelter.fromString(shelterText);
        Report report = Shelter.choiceReport(shelter);
        report.setPathImage(pathImage);
        report.setDiet(diet);
        report.setGeneralHealth(generalHealth);
        report.setDate(LocalDate.now());
        return ResponseEntity.ok().body(service.saveReport(report, shelter));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Report> updateReport(@RequestParam Long id,
                                               @RequestParam String pathImage,
                                               @RequestParam String diet,
                                               @RequestParam String generalHealth,
                                               @RequestParam(required = false) LocalDate date,
                                               @RequestParam(required = false, defaultValue = "DOG") String shelterText){
        Shelter shelter = Shelter.fromString(shelterText);
        Report report = Shelter.choiceReport(shelter);
        report.setId(id);
        report.setPathImage(pathImage);
        report.setDiet(diet);
        report.setGeneralHealth(generalHealth);
        report.setDate(date);
        return ResponseEntity.ok().body(service.updateReport(report, shelter));
    }

    @GetMapping(value = "/find")
    public ResponseEntity<Report> findReportById(@RequestParam Long id,
                                                 @RequestParam(required = false, defaultValue = "DOG") String shelterText){
        return ResponseEntity.ok().body(service.findReportById(id, Shelter.fromString(shelterText)));
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity removeReport(@RequestParam Long id,
                                       @RequestParam(required = false, defaultValue = "DOG") String shelterText){
        service.removeReport(id, Shelter.fromString(shelterText));
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List> findAllReport(@RequestParam(required = false, defaultValue = "DOG") String shelterText){
        return ResponseEntity.ok().body(service.findAll(Shelter.fromString(shelterText)));
    }

    @PostMapping(value = "/add-report")
    public ResponseEntity<Report> addReportToCandidate(@RequestParam Long id,
                                                       @RequestParam Long candidateId,
                                                       @RequestParam(required = false, defaultValue = "DOG") String shelterText){
        Report report = service.addReportCandidate(id, candidateId, Shelter.fromString(shelterText));
        return ResponseEntity.ok().body(report);
    }

    @DeleteMapping(value = "/remove-report")
    public ResponseEntity removeReportToCandidate(@RequestParam Long id,
                                                  @RequestParam Long candidateId,
                                                  @RequestParam(required = false, defaultValue = "DOG") String shelterText){
        service.removeReportCandidate(id, candidateId, Shelter.fromString(shelterText));
        return ResponseEntity.ok().build();
    }
}
