package tgbots.nipbot.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_report")
    private Long id;

    @Column(name = "id_candidate")
    private Long idCandidate;

    @Column(name = "path_image")
    private String pathImage;

    @Column(name = "diet")
    private String diet;

    @Column(name = "general_health")
    private String generalHealth;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    /*@ManyToOne
    @JoinColumn(name = "id-volunteer")
    private Volunteer volunteer;*/

    public Report() {
    }

    public Long getId() {
        return id;
    }

    public Long getIdCandidate() {
        return idCandidate;
    }

    public String getPathImage() {
        return pathImage;
    }

    public String getDiet() {
        return diet;
    }

    public String getGeneralHealth() {
        return generalHealth;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdCandidate(Long idCandidate) {
        this.idCandidate = idCandidate;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public void setGeneralHealth(String generalHealth) {
        this.generalHealth = generalHealth;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", idCandidate=" + idCandidate +
                ", pathImage='" + pathImage + '\'' +
                ", diet='" + diet + '\'' +
                ", generalHealth='" + generalHealth + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
