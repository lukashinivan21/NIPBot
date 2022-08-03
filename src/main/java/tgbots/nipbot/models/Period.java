package tgbots.nipbot.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "periods")
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_period")
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "trial_days")
    private Integer trialDays;

    @Column(name = "extra_days")
    private Integer extraDays;

    @OneToOne(mappedBy = "period")
    private Candidate candidate;

    public Period() {
    }

    private Period(Long id, LocalDate startDate, Integer trialDays, Integer extraDays) {
        this.id = id;
        this.startDate = startDate;
        this.trialDays = trialDays;
        this.extraDays = extraDays;
    }

    private Period create(Long id, LocalDate startDate, Integer trialDays, Integer extraDays){
        return new Period(id, startDate, trialDays, extraDays);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getTrialDays() {
        return trialDays;
    }

    public void setTrialDays(Integer trialDays) {
        this.trialDays = trialDays;
    }

    public Integer getExtraDays() {
        return extraDays;
    }

    public void setExtraDays(Integer extraDays) {
        this.extraDays = extraDays;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @Override
    public String toString() {
        return "Period{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", trialDays=" + trialDays +
                ", extraDays=" + extraDays +
                ", candidate=" + candidate +
                '}';
    }
}
