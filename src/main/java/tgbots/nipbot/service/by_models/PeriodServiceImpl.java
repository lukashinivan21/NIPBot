package tgbots.nipbot.service.by_models;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tgbots.nipbot.models.Candidate;
import tgbots.nipbot.models.Period;
import tgbots.nipbot.repositories.PeriodRepository;
import tgbots.nipbot.service.by_models.interfaces.PeriodService;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodServiceImpl implements PeriodService {

    private final PeriodRepository periodRepository;
    private final CandidateServiceImpl candidateService;

    public PeriodServiceImpl(PeriodRepository periodRepository, CandidateServiceImpl candidateService) {
        this.periodRepository = periodRepository;
        this.candidateService = candidateService;
    }

    @Override
    public Period savePeriod(Period period){
        return periodRepository.save(period);
    }

    @Override
    public Period updatePeriod(Period period){
        Optional<Period> periodOptional = periodRepository.findById(period.getId());
        if(periodOptional.isPresent()){
            period.setStartDate(periodOptional.get().getStartDate());
            period.setCandidate(periodOptional.get().getCandidate());
            return periodRepository.save(period);
        } else {
            throw new NotFoundException(period + " Not found");
        }
    }

    @Override
    public Period findPeriodById(Long id){
        Optional<Period> periodOptional = periodRepository.findById(id);
        return periodOptional.orElse(null);
    }

    @Override
    public void removePeriod(Long id){
        periodRepository.deleteById(id);
    }

    @Override
    public List<Period> findAll(){
        return periodRepository.findAll();
    }

    @Override
    public Period addPeriodCandidate(Long id, Long candidateId){
        Candidate candidate = candidateService.findCandidateById(candidateId);
        Period period = findPeriodById(id);
        candidate.setPeriod(period);
        period.setCandidate(candidate);
        candidateService.updateCandidate(candidate);
        return period;
    }

    @Override
    public void removePeriodCandidate(Long id, Long candidateId){
        Candidate candidate = candidateService.findCandidateById(candidateId);
        Period period = findPeriodById(id);
        period.setCandidate(null);
        candidate.setPeriod(null);
        candidateService.updateCandidate(candidate);
    }

}
