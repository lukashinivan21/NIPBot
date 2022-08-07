package tgbots.nipbot.service.by_models.interfaces;

import tgbots.nipbot.models.Period;

import java.util.List;

public interface PeriodService {

    public Period savePeriod(Period period);

    public Period updatePeriod(Period period);

    public Period findPeriodById(Long id);

    public void removePeriod(Long id);

    public List<Period> findAll();

    Period addPeriodCandidate(Long id, Long candidateId);

    void removePeriodCandidate(Long id, Long candidateId);
}
