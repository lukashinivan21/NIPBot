package tgbots.nipbot.service.by_models;

import org.springframework.stereotype.Service;
import tgbots.nipbot.models.Report;
import tgbots.nipbot.repositories.ReportRepository;
import tgbots.nipbot.service.by_models.interfaces.ReportService;

import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report addReport(Report report){
        return reportRepository.save(report);
    }

    public Report updateReport(Report report){
        return reportRepository.save(report);
    }

    public Report findReportById(Long id){
        Optional<Report> reportOptional = reportRepository.findById(id);
        return reportOptional.orElse(null);
    }

    public void removeReport(Long id){
        reportRepository.deleteById(id);
    }
}
