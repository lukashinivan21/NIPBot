package tgbots.nipbot.service.by_models;

import com.pengrad.telegrambot.model.Message;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tgbots.nipbot.models.Candidate;
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

import static tgbots.nipbot.service.Validation.PATTERN_PHONE_NUMBER_AND_FULL_NAME;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final DogCandidateRepository dogCandidateRepository;
    private final CatCandidateRepository catCandidateRepository;

    public CandidateServiceImpl(DogCandidateRepository dogCandidateRepository, CatCandidateRepository catCandidateRepository) {
        this.dogCandidateRepository = dogCandidateRepository;
        this.catCandidateRepository = catCandidateRepository;
    }

    @Override
    public Candidate saveCandidate(Candidate candidate, boolean addPeriod){
        if(dogCandidateRepository.findById(candidate.getId()).isEmpty()){
            if(addPeriod){
                Period period = new Period();
                period.setStartDate(LocalDate.now());
                period.setTrialDays(30);
                period.setCandidate(candidate);
                candidate.setPeriod(period);
            }
            return dogCandidateRepository.save((DogCandidate) candidate);
        }
        throw new EntityExistsException();
    }

    @Override
    public Candidate updateCandidate(Candidate candidate){
        Optional<DogCandidate> candidateOptional = dogCandidateRepository.findById(candidate.getId());
        if(candidateOptional.isPresent()){
            DogCandidate candidateByOptional = candidateOptional.get();
            if(candidate.getSecondName() == null) {
                candidate.setSecondName(candidateByOptional.getSecondName());
            }
            if(candidate.getPeriod() == null){
                candidate.setPeriod(candidateByOptional.getPeriod());
            };
            if(candidate.getReports().size() < candidateByOptional.getReports().size() || candidate.getReports() == null){
                candidate.setReports(candidateByOptional.getReports());
            }
            return dogCandidateRepository.save((DogCandidate) candidate);
        }
        throw new NotFoundException(candidate + " Not found");
    }

    @Override
    public Candidate updateCandidate(Message msg, String candidateString){
        Matcher matcher = PATTERN_PHONE_NUMBER_AND_FULL_NAME.matcher(candidateString);
        if (matcher.matches()) {
            Candidate candidate = new DogCandidate();
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
            dogCandidateRepository.save((DogCandidate)candidate);
            return candidate;
        }
        throw new NotFoundException(candidateString + " Not found");
    }

    @Override
    public Candidate findCandidateById(Long id){
        Optional<DogCandidate> candidateOptional = dogCandidateRepository.findById(id);
        if(candidateOptional.isPresent()){
            DogCandidate dogCandidate = candidateOptional.get();
            return dogCandidate;
        }
        throw new NotFoundException(id + " candidate Not found!");
    }

    @Override
    public void removeCandidate(Long id){
        dogCandidateRepository.deleteById(id);
    }

    @Override
    public List<DogCandidate> findAll(){
        return dogCandidateRepository.findAll();
    }
}
