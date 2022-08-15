package tgbots.nipbot.service.by_models.interfaces;

import tgbots.nipbot.constants.Shelter;
import tgbots.nipbot.models.Period;

import java.util.List;

public interface PeriodService {

    public Period savePeriod(Period period);

    public Period updatePeriod(Period period);

    public Period findPeriodById(Long id);

    public void removePeriod(Long id);

    public List<Period> findAll();

    Period addPeriodCandidate(Long id, Shelter shelter);

    void removePeriodCandidate(Long id, Shelter shelter);
}
