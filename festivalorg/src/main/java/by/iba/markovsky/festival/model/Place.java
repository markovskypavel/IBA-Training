package by.iba.markovsky.festival.model;

import by.iba.markovsky.festival.constant.RegExConstant;

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

@XmlRootElement(name = "Place")
@XmlType(propOrder = {"address","capacity"})
@Entity
@Table(name = "Place")
public class Place implements Serializable {

    private static final long serialVersionUID = -4969750303078456088L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id", unique = true, updatable = false)
    private int id;

    @Pattern(regexp = RegExConstant.ADDRESS)
    @Column(name = "address", nullable = false)
    private String address;

    @Min(5)
    @Max(10000)
    @Column(name = "capacity", nullable = false)
    private int capacity;

    public Place() {
    }
    public Place(int id) {
        this.id = id;
    }
    public Place(int id, String address, int capacity) {
        this.id = id;
        this.address = address;
        this.capacity = capacity;
    }
    public Place(String address, int capacity) {
        this.address = address;
        this.capacity = capacity;
    }
    public Place(Place place){
        this.address = place.address;
        this.capacity = place.capacity;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    //Getters
    @XmlTransient
    public int getId() {
        return id;
    }
    @XmlElement
    public String getAddress() {
        return address;
    }
    @XmlElement
    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return id == place.id &&
                capacity == place.capacity &&
                Objects.equals(address, place.address);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, address, capacity);
    }
    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", capacity=" + capacity +
                '}';
    }

}
