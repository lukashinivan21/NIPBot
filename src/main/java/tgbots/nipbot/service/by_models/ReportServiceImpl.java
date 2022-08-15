package tgbots.nipbot.service.by_models;

import org.springframework.stereotype.Service;
import tgbots.nipbot.constants.Shelter;
import tgbots.nipbot.exception.ShelterNotFoundException;
import tgbots.nipbot.models.Candidate;
import tgbots.nipbot.models.CatReport;
import tgbots.nipbot.models.DogReport;
import tgbots.nipbot.models.Report;
import tgbots.nipbot.repositories.ReportCatRepository;
import tgbots.nipbot.repositories.ReportDogRepository;
import tgbots.nipbot.service.by_models.interfaces.ReportService;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

import static tgbots.nipbot.constants.Shelter.*;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportDogRepository reportDogRepository;
    private final ReportCatRepository reportCatRepository;
    private final CandidateServiceImpl candidateService;

    public ReportServiceImpl(ReportDogRepository reportDogRepository, ReportCatRepository reportCatRepository, CandidateServiceImpl candidateService) {
        this.reportDogRepository = reportDogRepository;
        this.reportCatRepository = reportCatRepository;
        this.candidateService = candidateService;
    }

    @Override
    public Report saveReport(Report report, Shelter shelter){
        if(shelter.equals(DOG)){
            report.setFileSize(1L);
            report.setCaption("caption");
            byte[] bytes = new byte[1];
            report.setData(bytes);
            return reportDogRepository.save((DogReport) report);
        }
        if (shelter.equals(CAT)) {
            report.setFileSize(1L);
            report.setCaption("caption");
            byte[] bytes = new byte[1];
            report.setData(bytes);
            return reportCatRepository.save((CatReport) report);
        }
        throw new ShelterNotFoundException();
    }

    @Override
    public Report updateReport(Report report, Shelter shelter){
        if (shelter.equals(DOG)) {
            Optional<DogReport> reportOptional = reportDogRepository.findById(report.getId());
            if(reportOptional.isPresent()){
                if(report.getDate() == null){
                    report.setDate(reportOptional.get().getDate());
                }
                if(report.getCaption() == null){
                    report.setCaption(reportOptional.get().getCaption());
                }
                if(report.getData() == null){
                    report.setData(reportOptional.get().getData());
                }
                if(report.getFileSize() == null){
                    report.setFileSize(reportOptional.get().getFileSize());
                }
                return reportDogRepository.save((DogReport) report);
            }
            throw new EntityExistsException();
        }
        if (shelter.equals(CAT)) {
            Optional<CatReport> reportOptional = reportCatRepository.findById(report.getId());
            if(reportOptional.isPresent()){
                if(report.getDate() == null){
                    report.setDate(reportOptional.get().getDate());
                }
                if(report.getCaption() == null){
                    report.setCaption(reportOptional.get().getCaption());
                }
                if(report.getData() == null){
                    report.setData(reportOptional.get().getData());
                }
                if(report.getFileSize() == null){
                    report.setFileSize(reportOptional.get().getFileSize());
                }
                return reportCatRepository.save((CatReport) report);
            }
            throw new EntityExistsException();
        }
        throw new ShelterNotFoundException();
    }

    @Override
    public Report findReportById(Long id, Shelter shelter){
        if (shelter.equals(DOG)) {
            Optional<DogReport> reportOptional = reportDogRepository.findById(id);
            return reportOptional.orElseThrow(EntityExistsException::new);
        }
        if (shelter.equals(CAT)) {
            Optional<CatReport> reportOptional = reportCatRepository.findById(id);
            return reportOptional.orElseThrow(EntityExistsException::new);
        }
        throw new ShelterNotFoundException();
    }

    @Override
    public void removeReport(Long id, Shelter shelter){
        if (shelter.equals(DOG)) {
            reportDogRepository.deleteById(id);
        } else if (shelter.equals(CAT)){
            reportCatRepository.deleteById(id);
        } else {
            throw new ShelterNotFoundException();
        }

    }

    @Override
    public List findAll(Shelter shelter) {
        if (shelter.equals(DOG)) {
            return reportDogRepository.findAll();
        }
        if (shelter.equals(CAT)){
            return reportCatRepository.findAll();
        }
        throw new ShelterNotFoundException();
    }

    @Override
    public Report addReportCandidate(Long id, Long candidateId, Shelter shelter){
        Candidate candidate = candidateService.findCandidateById(candidateId, shelter);
        Report report = findReportById(id, shelter);
        List<Report> reports = candidate.getReports();
        report.setCandidate(candidateService.findCandidateById(candidateId, shelter));
        reports.add(report);
        candidate.setReports(reports);
        candidateService.updateCandidate(candidate, shelter);
        return report;
    }

    @Override
    public void removeReportCandidate(Long id, Long candidateId, Shelter shelter){
        Candidate candidate = candidateService.findCandidateById(candidateId, shelter);
        Report report = findReportById(id, shelter);
        List<Report> reports = candidate.getReports();
        reports.remove(report);
        if(shelter.equals(DOG)){
            reportDogRepository.deleteById(id);
            candidate.setReports(reports);
            candidateService.updateCandidate(candidate, shelter);
        } else if(shelter.equals(CAT)){
            reportCatRepository.deleteById(id);
            candidate.setReports(reports);
            candidateService.updateCandidate(candidate, shelter);
        } else {
            throw new ShelterNotFoundException();
        }
    }
}
