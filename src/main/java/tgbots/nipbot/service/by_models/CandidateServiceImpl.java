package tgbots.nipbot.service.by_models;

import org.springframework.stereotype.Service;
import tgbots.nipbot.models.Candidate;
import tgbots.nipbot.repositories.CandidateRepository;
import tgbots.nipbot.service.by_models.interfaces.CandidateService;

import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate addCandidate(Candidate candidate){
        return candidateRepository.save(candidate);
    }

    public Candidate updateCandidate(Candidate candidate){
        return candidateRepository.save(candidate);
    }

    public Candidate findCandidateById(Long id){
        Optional<Candidate> candidateOptional = candidateRepository.findById(id);
        return candidateOptional.orElse(null);
    }

    public void removeCandidate(Long id){
        candidateRepository.deleteById(id);
    }
}
