package tgbots.nipbot.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dog_candidates")
public class DogCandidate extends Candidate{

    public DogCandidate() {
        super();
    }

    private DogCandidate(Long id, String firstName, String secondName, String username) {
        super(id, firstName, secondName, username);
    }

    public static DogCandidate create(Long id, String firstName, String secondName, String username){
        return new DogCandidate(id, firstName , secondName, username);
    }
}
