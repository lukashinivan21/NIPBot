package tgbots.nipbot.service.by_models;

import com.pengrad.telegrambot.model.Message;
import org.springframework.stereotype.Service;
import tgbots.nipbot.models.Candidate;
import tgbots.nipbot.repositories.CandidateRepository;
import tgbots.nipbot.service.by_models.interfaces.CandidateService;

import java.util.Optional;
import java.util.regex.Matcher;

import static tgbots.nipbot.service.Validation.PATTERN_PHONE_NUMBER_AND_FULL_NAME;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate saveCandidate(Candidate candidate){
        return candidateRepository.save(candidate);
    }

    public Candidate updateCandidate(Candidate candidate){
        return candidateRepository.save(candidate);
    }

    public Candidate updateCandidate(Message msg, String candidateString){
        Matcher matcher = PATTERN_PHONE_NUMBER_AND_FULL_NAME.matcher(candidateString);
        if (matcher.matches()) {
            Candidate candidate = new Candidate();
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
            candidateRepository.save(candidate);
            return candidate;
        }
        throw new NullPointerException();
    }

    public Candidate findCandidateById(Long id){
        Optional<Candidate> candidateOptional = candidateRepository.findById(id);
        return candidateOptional.orElse(null);
    }

    public void removeCandidate(Long id){
        candidateRepository.deleteById(id);
    }
}
