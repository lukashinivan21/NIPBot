package tgbots.nipbot.service.by_models;

import org.springframework.stereotype.Service;
import tgbots.nipbot.models.Candidate;
import tgbots.nipbot.models.Report;
import tgbots.nipbot.repositories.ReportRepository;
import tgbots.nipbot.service.by_models.interfaces.ReportService;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final CandidateServiceImpl candidateService;

    public ReportServiceImpl(ReportRepository reportRepository, CandidateServiceImpl candidateService) {
        this.reportRepository = reportRepository;
        this.candidateService = candidateService;
    }

    @Override
    public Report saveReport(Report report){
        return reportRepository.save(report);
    }

    @Override
    public Report updateReport(Report report){
        Optional<Report> reportOptional = reportRepository.findById(report.getId());
        if(reportOptional.isPresent()){
            if(report.getDate() != null){
                report.setDate(reportOptional.get().getDate());
            }
            return reportRepository.save(report);
        }
        throw new EntityExistsException();
    }

    @Override
    public Report findReportById(Long id){
        Optional<Report> reportOptional = reportRepository.findById(id);
        return reportOptional.orElse(null);
    }

    @Override
    public void removeReport(Long id){
        reportRepository.deleteById(id);
    }

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Report addReportCandidate(Long id, Long candidateId){
        Candidate candidate = candidateService.findCandidateById(candidateId);
        Report report = findReportById(id);
        List<Report> reports = candidate.getReports();
        report.setCandidate(candidateService.findCandidateById(candidateId));
        reports.add(report);
        candidate.setReports(reports);
        candidateService.updateCandidate(candidate);
        return report;
    }

    @Override
    public void removeReportCandidate(Long id, Long candidateId){
        Candidate candidate = candidateService.findCandidateById(candidateId);
        Report report = findReportById(id);
        List<Report> reports = candidate.getReports();
        reports.remove(report);
        candidate.setReports(reports);
        candidateService.updateCandidate(candidate);
    }
}
