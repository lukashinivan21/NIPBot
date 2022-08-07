package tgbots.nipbot.service.by_models.interfaces;

import com.pengrad.telegrambot.model.Message;
import tgbots.nipbot.models.Candidate;

import java.util.List;

public interface CandidateService {

    public Candidate saveCandidate(Candidate candidate, boolean addPeriod);

    public Candidate updateCandidate(Candidate candidate);

    public Candidate updateCandidate(Message msg, String candidateString);

    public Candidate findCandidateById(Long id);

    public void removeCandidate(Long id);

    public List<Candidate> findAll();
}
