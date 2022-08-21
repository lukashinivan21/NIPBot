package tgbots.nipbot.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cat_candidates")
public class CatCandidate extends Candidate{

    public CatCandidate() {
        super();
    }

    private CatCandidate(Long id, String firstName, String secondName, String username){
        super(id, firstName, secondName, username);
    }

    public static CatCandidate create(Long id, String firstName, String secondName, String username){
        return new CatCandidate(id, firstName , secondName, username);
    }
}
