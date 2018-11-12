package by.iba.markovsky.festival.model;

import by.iba.markovsky.festival.constant.RegExConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Objects;

@ApiModel(description="Identity")
@XmlRootElement(name = "Identity")
@XmlType(propOrder = {"id","name","surname","age"})
@Entity
@Table(name = "Identity")
public class Identity implements Serializable {

    private static final long serialVersionUID = -3354880957250012160L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identity_id", unique = true, updatable = false)
    private int id;

    @ApiModelProperty(value="Pavel")
    @Pattern(regexp = RegExConstant.NAME)
    @Column(name = "name", nullable = false)
    private String name;

    @ApiModelProperty(value="Markovsky")
    @Pattern(regexp = RegExConstant.SURNAME)
    @Column(name = "surname")
    private String surname;

    /*@Min(0) @Max(99)*/
    @Pattern(regexp = RegExConstant.AGE)
    @Column(name = "age")
    private String age;

    public Identity() {
    }
    public Identity(int id) {
        this.id = id;
    }
    public Identity(int id, String name, String surname, String age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
    public Identity(String name, String surname, String age) {
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
    public void setAge(String age) {
        this.age = age;
    }

    //Getters
    @XmlElement
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
    public String getAge() {
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
