package tgbots.nipbot.service.by_models;

import com.pengrad.telegrambot.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tgbots.nipbot.constants.Shelter;
import tgbots.nipbot.exception.ShelterNotFoundException;
import tgbots.nipbot.models.Candidate;
import tgbots.nipbot.models.CatCandidate;
import tgbots.nipbot.models.DogCandidate;
import tgbots.nipbot.models.Period;
import tgbots.nipbot.repositories.CatCandidateRepository;
import tgbots.nipbot.repositories.DogCandidateRepository;
import tgbots.nipbot.service.by_models.interfaces.CandidateService;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

import static tgbots.nipbot.constants.Shelter.CAT;
import static tgbots.nipbot.constants.Shelter.DOG;
import static tgbots.nipbot.service.Validation.PATTERN_PHONE_NUMBER_AND_FULL_NAME;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final Logger log = LoggerFactory.getLogger(CandidateServiceImpl.class);
    private final DogCandidateRepository dogCandidateRepository;
    private final CatCandidateRepository catCandidateRepository;

    public CandidateServiceImpl(DogCandidateRepository dogCandidateRepository, CatCandidateRepository catCandidateRepository) {
        this.dogCandidateRepository = dogCandidateRepository;
        this.catCandidateRepository = catCandidateRepository;
    }

    @Override
    public Candidate saveCandidate(Candidate candidate, boolean addPeriod, Shelter shelter){
        if (shelter.equals(DOG)) {
            if(dogCandidateRepository.findById(candidate.getId()).isEmpty()){
                if(addPeriod){
                    Period period = new Period();
                    period.setStartDate(LocalDate.now());
                    period.setTrialDays(30);
                    period.setCandidate(candidate);
                    candidate.setPeriod(period);
                }
                DogCandidate dogCandidate = dogCandidateRepository.save((DogCandidate) candidate);
                log.info("{} save in dogCandidateRepository", dogCandidate);
                return dogCandidate;
            }
            log.error("saveCandidate: {} not found in DataBase", candidate);
            throw new EntityExistsException();
        }
        if (shelter.equals(CAT)) {
            if(catCandidateRepository.findById(candidate.getId()).isEmpty()){
                if(addPeriod){
                    Period period = new Period();
                    period.setStartDate(LocalDate.now());
                    period.setTrialDays(30);
                    period.setCandidate(candidate);
                    candidate.setPeriod(period);
                }
                CatCandidate catCandidate = catCandidateRepository.save((CatCandidate) candidate);
                log.info("{} save in catCandidateRepository", catCandidate);
                return catCandidate;
            }
            log.error("saveCandidate: {} not found in DataBase", candidate);
            throw new EntityExistsException();
        }
        log.error("saveCandidate: {} is not found", shelter);
        throw new ShelterNotFoundException();
    }

    @Override
    public Candidate updateCandidate(Candidate candidate, Shelter shelter){
        if (shelter.equals(DOG)) {
            Optional<DogCandidate> candidateOptional = dogCandidateRepository.findById(candidate.getId());
            if(candidateOptional.isPresent()){
                DogCandidate candidateByOptional = candidateOptional.get();
                if(candidate.getSecondName() == null) {
                    candidate.setSecondName(candidateByOptional.getSecondName());
                }
                if(candidate.getPeriod() == null){
                    candidate.setPeriod(candidateByOptional.getPeriod());
                }
                if(candidate.getReports().size() < candidateByOptional.getReports().size() || candidate.getReports() == null){
                    candidate.setReports(candidateByOptional.getReports());
                }
                log.info("{} update in dogCandidateRepository", candidate);
                return dogCandidateRepository.save((DogCandidate) candidate);
            }
            log.error("updateCandidate: {} not found in DataBase", candidate);
            throw new NotFoundException(candidate + " Not found");
        }
        if (shelter.equals(CAT)) {
            Optional<CatCandidate> candidateOptional = catCandidateRepository.findById(candidate.getId());
            if(candidateOptional.isPresent()){
                CatCandidate candidateByOptional = candidateOptional.get();
                if(candidate.getSecondName() == null) {
                    candidate.setSecondName(candidateByOptional.getSecondName());
                }
                if(candidate.getPeriod() == null){
                    candidate.setPeriod(candidateByOptional.getPeriod());
                }
                if(candidate.getReports().size() < candidateByOptional.getReports().size() || candidate.getReports() == null){
                    candidate.setReports(candidateByOptional.getReports());
                }
                log.info("{} update in catCandidateRepository", candidate);
                return catCandidateRepository.save((CatCandidate) candidate);
            }
            log.error("updateCandidate: {} not found in DataBase", candidate);
            throw new NotFoundException(candidate + " Not found");
        }
        log.error("updateCandidate: {} is not found", shelter);
        throw new ShelterNotFoundException();
    }

    @Override
    public Candidate updateCandidate(Message msg, String candidateString, Shelter shelter){
        Matcher matcher = PATTERN_PHONE_NUMBER_AND_FULL_NAME.matcher(candidateString);
        if (shelter.equals(DOG)) {
            if (matcher.matches()) {
                DogCandidate candidate = new DogCandidate();
                candidate.setId(msg.chat().id());
                candidate.setUsername(msg.from().username());
                System.out.println(matcher.group(0));
                String[] strings = matcher.group(0).split("\\s+");
                if(strings[0].startsWith("+7")){
                    strings[0] = strings[0].replace("+7", "8");
                }
                candidate.setPhoneNumber(strings[0]);
                candidate.setFirstName(strings[1]);
                if(strings.length >= 3){
                    candidate.setSecondName(strings[2]);
                }
                dogCandidateRepository.save(candidate);
                log.info("updateCandidate: {} update in dogCandidateRepository", candidate);
                return candidate;
            }
            log.error("updateCandidate: {} not valid regex", candidateString);
            throw new NotFoundException(candidateString + " Not found");
        }
        if (shelter.equals(CAT)) {
            if (matcher.matches()) {
                CatCandidate candidate = new CatCandidate();
                candidate.setId(msg.chat().id());
                candidate.setUsername(msg.from().username());
                System.out.println(matcher.group(0));
                String[] strings = matcher.group(0).split("\\s+");
                if(strings[0].startsWith("+7")){
                    strings[0] = strings[0].replace("+7", "8");
                }
                candidate.setPhoneNumber(strings[0]);
                candidate.setFirstName(strings[1]);
                if(strings.length >= 3){
                    candidate.setSecondName(strings[2]);
                }
                catCandidateRepository.save(candidate);
                log.info("updateCandidate: {} update in dogCandidateRepository", candidate);
                return candidate;
            }
            log.error("updateCandidate: {} not valid regex", candidateString);
            throw new NotFoundException(candidateString + " Not found");
        }
        log.error("updateCandidate: {} is not found", shelter);
        throw new ShelterNotFoundException();
    }

    @Override
    public Candidate findCandidateById(Long id, Shelter shelter){
        if (shelter.equals(DOG)) {
            Optional<DogCandidate> candidateOptional = dogCandidateRepository.findById(id);
            if(candidateOptional.isPresent()){
                log.info("findCandidateById: {} found dogCandidateRepository", candidateOptional.get());
                return candidateOptional.get();
            }
            log.error("findCandidateById: {} not found dogCandidateRepository", candidateOptional);
            throw new NotFoundException(id + " candidate Not found!");
        }
        if (shelter.equals(CAT)) {
            Optional<CatCandidate> candidateOptional = catCandidateRepository.findById(id);
            if(candidateOptional.isPresent()){
                log.info("findCandidateById: {} found catCandidateRepository", candidateOptional.get());
                return candidateOptional.get();
            }
            log.error("findCandidateById: {} not found catCandidateRepository", candidateOptional);
            throw new NotFoundException(id + " candidate Not found!");
        }
        log.error("findCandidateById: {} is not found", shelter);
        throw new ShelterNotFoundException();
    }

    @Override
    public void removeCandidate(Long id, Shelter shelter){
        if (shelter.equals(DOG)) {
            log.info("removeCandidate: {} by candidate remove dogCandidateRepository", id);
            dogCandidateRepository.deleteById(id);
        } else if (shelter.equals(CAT)) {
            log.info("removeCandidate: {} by candidate remove catCandidateRepository", id);
            catCandidateRepository.deleteById(id);
        } else {
            log.error("findCandidateById: {} is not found", shelter);
            throw new ShelterNotFoundException();
        }
    }

    @Override
    public List findAll(Shelter shelter){
        if(shelter.equals(DOG)){
            log.info("findAll dogCandidateRepository");
            return dogCandidateRepository.findAll();
        }
        if(shelter.equals(CAT)){
            log.info("findAl catCandidateRepository");
            return catCandidateRepository.findAll();
        }
        log.error("findAll: {} is not found", shelter);
        throw new ShelterNotFoundException();
    }
}
