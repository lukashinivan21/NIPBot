package tgbots.nipbot.service.by_models.interfaces;

import tgbots.nipbot.constants.Shelter;
import tgbots.nipbot.models.Report;

import java.util.List;

public interface ReportService {

    public Report saveReport(Report report, Shelter shelter);

    public Report updateReport(Report report, Shelter shelter);

    Report addReportCandidate(Long id, Long candidateId, Shelter shelter);

    void removeReportCandidate(Long id, Long candidateId, Shelter shelter);

    public Report findReportById(Long id, Shelter shelter);

    public void removeReport(Long id, Shelter shelter);

    public List<Report> findAll(Shelter shelter);
}
