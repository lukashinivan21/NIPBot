package tgbots.nipbot.controller;

import org.springframework.http.HttpStatus;
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
                                               @RequestParam String generalHealth){
        Report report = new Report();
        report.setPathImage(pathImage);
        report.setDiet(diet);
        report.setGeneralHealth(generalHealth);
        report.setDate(LocalDate.now());
        return ResponseEntity.ok().body(service.saveReport(report));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Report> updateReport(@RequestParam Long id,
                                               @RequestParam String pathImage,
                                               @RequestParam String diet,
                                               @RequestParam String generalHealth,
                                               @RequestParam(required = false) LocalDate date){
        Report report = new Report();
        report.setId(id);
        report.setPathImage(pathImage);
        report.setDiet(diet);
        report.setGeneralHealth(generalHealth);
        report.setDate(date);
        return ResponseEntity.ok().body(service.updateReport(report));
    }

    @GetMapping(value = "/find")
    public ResponseEntity<Report> findReportById(@RequestParam Long id){
        return ResponseEntity.ok().body(service.findReportById(id));
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity removeReport(@RequestParam Long id){
        service.removeReport(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<Report>> findAllReport(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping(value = "/add-report")
    public ResponseEntity<Report> addReportToCandidate(@RequestParam Long id,
                                                       @RequestParam Long candidateId,
                                                       @RequestParam(required = false, defaultValue = "DOG") String shelter){
        String shelterUp = shelter.toUpperCase();
        if(shelterUp.equals(Shelter.DOG.name())){
            Report report = service.addReportCandidate(id, candidateId, Shelter.DOG);
            return ResponseEntity.ok().body(report);
        }
        if(shelterUp.equals(Shelter.CAT.name())){
            Report report = service.addReportCandidate(id, candidateId, Shelter.CAT);
            return ResponseEntity.ok().body(report);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //!!!НЕ ЗАБЫТЬ СДЕЛАТЬ УСЛОВИЕ
    @DeleteMapping(value = "/remove-report")
    public ResponseEntity removeReportToCandidate(@RequestParam Long id,
                                                  @RequestParam Long candidateId){
        service.removeReportCandidate(id, candidateId, Shelter.DOG);
        return ResponseEntity.ok().build();
    }
}
