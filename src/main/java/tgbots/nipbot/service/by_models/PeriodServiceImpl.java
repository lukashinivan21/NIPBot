package tgbots.nipbot.service.by_models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tgbots.nipbot.constants.Shelter;
import tgbots.nipbot.models.Candidate;
import tgbots.nipbot.models.Period;
import tgbots.nipbot.repositories.PeriodRepository;
import tgbots.nipbot.service.by_models.interfaces.PeriodService;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodServiceImpl implements PeriodService {

    private final Logger log = LoggerFactory.getLogger(PeriodServiceImpl.class);
    private final PeriodRepository periodRepository;
    private final CandidateServiceImpl candidateService;

    public PeriodServiceImpl(PeriodRepository periodRepository, CandidateServiceImpl candidateService) {
        this.periodRepository = periodRepository;
        this.candidateService = candidateService;
    }

    @Override
    public Period savePeriod(Period period){
        Period periodSave = periodRepository.save(period);
        log.info("savePeriod: {} save in periodRepository", period);
        return periodSave;
    }

    @Override
    public Period updatePeriod(Period period){
        Optional<Period> periodOptional = periodRepository.findById(period.getId());
        if(periodOptional.isPresent()){
            period.setStartDate(periodOptional.get().getStartDate());
            period.setCandidate(periodOptional.get().getCandidate());
            log.info("updatePeriod: {} update in periodRepository", period);
            return periodRepository.save(period);
        } else {
            log.error("updatePeriod: {} not found in periodRepository", period);
            throw new NotFoundException(period + " Not found");
        }
    }

    @Override
    public Period findPeriodById(Long id){
        Optional<Period> periodOptional = periodRepository.findById(id);
        if(periodOptional.isPresent()){
            log.info("findPeriodById: {} in periodRepository", periodOptional.get());
            return periodOptional.get();
        }
        log.error("findPeriodById: {} not found in periodRepository", periodOptional);
        throw new NotFoundException(id + " not found!");
    }

    @Override
    public void removePeriod(Long id){
        periodRepository.deleteById(id);
        log.info("removePeriod: {} period removed in periodRepository", id);
    }

    @Override
    public List<Period> findAll(){
        List<Period> periods = periodRepository.findAll();
        log.info("findAll: {} in periodRepository", periods);
        return periods;
    }

    @Override
    public Period addPeriodCandidate(Long id, Shelter shelter){
        Candidate candidate = candidateService.findCandidateById(id, shelter);
        Period period = findPeriodById(id);
        if(candidate == null){
            log.error("addPeriodCandidate: candidate Not Found!");
            throw new NotFoundException(id + " candidate Not found!");
        }
        if(period == null){
            log.error("addPeriodCandidate: period Not Found!");
            throw new NotFoundException(id + " period Not found!");
        }
        period.setCandidate(candidateService.findCandidateById(id, shelter));
        candidate.setPeriod(findPeriodById(id));
        candidateService.updateCandidate(candidate, shelter);
        System.out.println(candidate);
        System.out.println(period);
        log.info("id:{} period add to candidate", id);
        return period;
    }

    @Override
    public void removePeriodCandidate(Long id, Shelter shelter){
        Candidate candidate = candidateService.findCandidateById(id, shelter);
        Period period = findPeriodById(id);
        period.setCandidate(null);
        candidate.setPeriod(null);
        candidateService.updateCandidate(candidate, shelter);
        log.info("id:{} period remove to candidate", id);
    }

}
