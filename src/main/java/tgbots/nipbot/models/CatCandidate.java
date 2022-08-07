package tgbots.nipbot.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cat_candidates")
public class CatCandidate extends Candidate{

    public CatCandidate() {
        super();
    }
}
