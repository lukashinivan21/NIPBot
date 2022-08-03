package tgbots.nipbot.service.by_models.interfaces;

import tgbots.nipbot.models.Candidate;

public interface CandidateService {

    public Candidate addCandidate(Candidate candidate);

    public Candidate updateCandidate(Candidate candidate);

    public Candidate findCandidateById(Long id);

    public void removeCandidate(Long id);
}
