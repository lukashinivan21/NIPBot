package tgbots.nipbot.service.by_models.interfaces;

import tgbots.nipbot.models.Report;

public interface ReportService {

    public Report saveReport(Report report);

    public Report updateReport(Report report);

    public Report findReportById(Long id);

    public void removeReport(Long id);
}
