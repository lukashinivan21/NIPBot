package tgbots.nipbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
                                                       @RequestParam Long candidateId){
        Report report = service.addReportCandidate(id, candidateId);
        return ResponseEntity.ok().body(report);
    }

    @DeleteMapping(value = "/remove-report")
    public ResponseEntity removeReportToCandidate(@RequestParam Long id,
                                                  @RequestParam Long candidateId){
        service.removeReportCandidate(id, candidateId);
        return ResponseEntity.ok().build();
    }
}
