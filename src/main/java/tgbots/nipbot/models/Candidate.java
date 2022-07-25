package tgbots.nipbot.models;

import javax.persistence.*;

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @Column(name = "id-candidate")
    private Long id;

    @Column(name = "name-candidate")
    private String name;

    @Column(name = "username-candidate")
    private String username;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "candidate-dog",
            joinColumns = @JoinColumn(name = "id-candidate", referencedColumnName = "id-candidate"),
            inverseJoinColumns = @JoinColumn(name = "id_dog", referencedColumnName = "id-dog"))
    private Dog dog;*/

    public Candidate() {
    }

    private Candidate(Long id, String name, String username, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

    public Candidate create(Long id, String name, String username, String phoneNumber){
        return new Candidate(id, name, username, phoneNumber);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
