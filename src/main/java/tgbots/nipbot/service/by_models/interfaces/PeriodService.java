package tgbots.nipbot.service.by_models.interfaces;

import tgbots.nipbot.models.Period;

public interface PeriodService {

    public Period savePeriod(Period period);

    public Period updatePeriod(Period period);

    public Period findPeriodById(Long id);

    public void removeCandidate(Long id);

}
