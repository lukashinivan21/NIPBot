package tgbots.nipbot.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "candidates")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Candidate {

    @Id
    @Column(name = "id_candidate")
    private Long id;

    @Column(name = "first_name_candidate")
    private String firstName;

    @Column(name = "second_name_candidate")
    private String secondName;

    @Column(name = "username_candidate")
    private String username;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Report> reports = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Period period;

    public Candidate() {
    }

    protected Candidate(Long id, String firstName, String secondName, String username) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.username = username;
    }

    public void addReport(Report report){
        reports.add(report);
        report.setCandidate(this);
    }

    public void removeRemove(Report report){
        reports.remove(report);
        report.setCandidate(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reports=" + reports +
                ", period=" + period +
                '}';
    }
}
