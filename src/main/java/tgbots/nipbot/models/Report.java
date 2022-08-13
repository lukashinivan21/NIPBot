package tgbots.nipbot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_report")
    private Long id;

    @Column(name = "path_image")
    private String pathImage;

    @Column(name = "diet")
    private String diet;

    @Column(name = "general_health")
    private String generalHealth;

    @Column(name = "date")
    private LocalDate date;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Candidate candidate;

    public Report() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getGeneralHealth() {
        return generalHealth;
    }

    public void setGeneralHealth(String generalHealth) {
        this.generalHealth = generalHealth;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return id.equals(report.id) && Objects.equals(pathImage, report.pathImage) && Objects.equals(diet, report.diet) && Objects.equals(generalHealth, report.generalHealth) && Objects.equals(date, report.date) && Objects.equals(candidate, report.candidate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pathImage, diet, generalHealth, date, candidate);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", pathImage='" + pathImage + '\'' +
                ", diet='" + diet + '\'' +
                ", generalHealth='" + generalHealth + '\'' +
                ", date=" + date +
                '}';
    }

}
