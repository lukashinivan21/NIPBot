package tgbots.nipbot.models;

import javax.persistence.*;

@Entity
@Table(name = "report_dog")
public class DogReport extends Report{

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dog_candidate_id_candidate")
    private DogCandidate dogCandidate;

    public DogCandidate getDogCandidate() {
        return dogCandidate;
    }

    public void setDogCandidate(DogCandidate dogCandidate) {
        this.dogCandidate = dogCandidate;
    }

    public DogReport() {
        super();
    }
}
