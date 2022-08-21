package tgbots.nipbot.models;

import javax.persistence.*;

@Entity
@Table(name = "report_cat")
public class CatReport extends Report {


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cat_candidate_id_candidate")
    private CatCandidate catCandidate;

    public CatCandidate getCatCandidate() {
        return catCandidate;
    }

    public void setCatCandidate(CatCandidate catCandidate) {
        this.catCandidate = catCandidate;
    }

    public CatReport() {
        super();
    }
}
