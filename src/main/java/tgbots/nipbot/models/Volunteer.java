package tgbots.nipbot.models;

import javax.persistence.*;

@Entity
@Table(name = "volunteers")
public class Volunteer {

    @Id
    @Column(name = "id_volunteer")
    private Long id;

    @Column(name = "first_name_volunteer")
    private String firstName;

    @Column(name = "second_name_volunteer")
    private String secondName;

    @Column(name = "username_volunteer")
    private String username;

    @Column(name = "password")
    private String password;

    public Volunteer() {
    }

    private Volunteer(Long id, String firstName, String secondName, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.username = username;
        this.password = password;
    }

    private Volunteer create(Long id, String firstName, String secondName, String username, String password){
        return new Volunteer(id, firstName, secondName, username, password);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
