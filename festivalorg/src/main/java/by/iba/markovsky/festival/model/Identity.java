package by.iba.markovsky.festival.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name = "Identity")
@XmlType(propOrder = {"name","surname","age"})
@Entity
@Table(name = "Identity")
public class Identity implements Serializable {

    private static final long serialVersionUID = -3354880957250012160L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identity_id", unique = true, updatable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "age")
    private int age;

    public Identity() {
    }
    public Identity(int id) {
        this.id = id;
    }
    public Identity(int id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
    public Identity(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
    public Identity(Identity identity) {
        this.name = identity.name;
        this.surname = identity.surname;
        this.age = identity.age;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setAge(int age) {
        this.age = age;
    }

    //Getters
    @XmlTransient
    public int getId() {
        return id;
    }
    @XmlElement
    public String getName() {
        return name;
    }
    @XmlElement
    public String getSurname() {
        return surname;
    }
    @XmlElement
    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identity identity = (Identity) o;
        return id == identity.id &&
                age == identity.age &&
                Objects.equals(name, identity.name) &&
                Objects.equals(surname, identity.surname);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age);
    }
    @Override
    public String toString() {
        return "Identity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }

}
