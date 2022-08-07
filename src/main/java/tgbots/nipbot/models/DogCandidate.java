package tgbots.nipbot.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dog_candidates")
public class DogCandidate extends Candidate{

    public DogCandidate() {
        super();
    }


}
