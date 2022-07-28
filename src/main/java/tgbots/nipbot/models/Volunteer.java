package tgbots.nipbot.models;

import liquibase.pro.packaged.V;

import javax.persistence.*;

@Entity
@Table(name = "volunteers")
public class Volunteer {

    @Id
    @Column(name = "id_volunteer")
    private Long id;

    @Column(name = "name_volunteer")
    private String name;

    @Column(name = "username_volunteer")
    private String username;

    @Column(name = "password")
    private String password;

    /*@OneToMany(mappedBy = "volunteer")
    private List<Report> reports;*/

    public Volunteer() {
    }

    private Volunteer(Long id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    private Volunteer create(Long id, String name, String username, String password){
        return new Volunteer(id, name, username, password);
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

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
