package tgbots.nipbot.service.by_models.interfaces;

import com.pengrad.telegrambot.model.Message;
import tgbots.nipbot.constants.Shelter;
import tgbots.nipbot.models.Candidate;
import tgbots.nipbot.models.DogCandidate;

import java.util.List;

public interface CandidateService {

    public Candidate saveCandidate(Candidate candidate, boolean addPeriod, Shelter shelter);

    public Candidate updateCandidate(Candidate candidate, Shelter shelter);

    public Candidate updateCandidate(Message msg, String candidateString, Shelter shelter);

    public Candidate findCandidateById(Long id, Shelter shelter);

    public void removeCandidate(Long id, Shelter shelter);

    public List<DogCandidate> findAll(Shelter shelter);
}
