package tgbots.nipbot.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id-report")
    private Long id;

    @Column(name = "id-candidate")
    private Long idCandidate;

    @Column(name = "path-image")
    private String pathImage;

    @Column(name = "diet")
    private String diet;

    @Column(name = "general-health")
    private String generalHealth;

    @Column(name = "date-time")
    private LocalDateTime dateTime;

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

    public LocalDateTime getDateTime() {
        return dateTime;
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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", idCandidate=" + idCandidate +
                ", pathImage='" + pathImage + '\'' +
                ", diet='" + diet + '\'' +
                ", generalHealth='" + generalHealth + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
