package tgbots.nipbot.service.by_models.interfaces;

import tgbots.nipbot.models.Period;

import java.util.Optional;

public interface PeriodService {

    public Period addPeriod(Period period);

    public Period updatePeriod(Period period);

    public Period findPeriodById(Long id);

    public void removeCandidate(Long id);

}
