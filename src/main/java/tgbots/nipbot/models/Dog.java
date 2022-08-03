package tgbots.nipbot.models;

import javax.persistence.*;

@Entity
@Table(name = "dogs")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_dog")
    private Long id;

    @Column(name = "name_dog")
    private String name;

    @Column(name = "age")
    private Double age;

    public Dog() {
    }

    private Dog(Long id, String name, Double age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Dog create(Long id, String name, Double age){
        return new Dog(id, name, age);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
