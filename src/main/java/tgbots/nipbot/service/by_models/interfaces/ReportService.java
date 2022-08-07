package tgbots.nipbot.service.by_models.interfaces;

import tgbots.nipbot.models.Report;

import java.util.List;

public interface ReportService {

    public Report saveReport(Report report);

    public Report updateReport(Report report);

    Report addReportCandidate(Long id, Long candidateId);

    void removeReportCandidate(Long id, Long candidateId);

    public Report findReportById(Long id);

    public void removeReport(Long id);

    public List<Report> findAll();
}
