package tgbots.nipbot.service.by_models;

import org.springframework.stereotype.Service;
import tgbots.nipbot.models.Period;
import tgbots.nipbot.repositories.PeriodRepository;
import tgbots.nipbot.service.by_models.interfaces.PeriodService;

import java.util.Optional;

@Service
public class PeriodServiceImpl implements PeriodService {

    private final PeriodRepository periodRepository;

    public PeriodServiceImpl(PeriodRepository periodRepository) {
        this.periodRepository = periodRepository;
    }

    public Period savePeriod(Period period){
        return periodRepository.save(period);
    }

    public Period updatePeriod(Period period){
        return periodRepository.save(period);
    }

    public Period findPeriodById(Long id){
        Optional<Period> periodOptional = periodRepository.findById(id);
        return periodOptional.orElse(null);
    }

    public void removeCandidate(Long id){
        periodRepository.deleteById(id);
    }
}
