package by.iba.markovsky.festival.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XmlRootElement(name = "Artist")
@XmlType(propOrder = {"name","genre"})
@Entity
@Table(name = "Artist")
public class Artist implements Serializable {

    private static final long serialVersionUID = -4225549571954368009L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id", unique = true, updatable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "genre")
    private String genre;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, mappedBy = "artists")
/*    @Fetch(value = FetchMode.SUBSELECT)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Transient*/
    private Set<Activity> activities = new HashSet<>();

    public Artist() {
    }
    public Artist(int id) {
        this.id = id;
    }
    public Artist(int id, String name, String genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
    }
    public Artist(int id, String name, String genre, Set<Activity> activities) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.activities = activities;
    }
    public Artist(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }
    public Artist(String name, String genre, Set<Activity> activities) {
        this.name = name;
        this.genre = genre;
        this.activities = activities;
    }
    public Artist(Artist artist) {
        this.name = artist.name;
        this.genre = artist.genre;
        this.activities = artist.activities;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    //Getters
    @XmlTransient
    public int getId() {
        return id;
    }
    @XmlTransient
    public Set<Activity> getActivities() {
        return activities;
    }
    @XmlElement
    public String getName() {
        return name;
    }
    @XmlElement
    public String getGenre() {
        return genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return id == artist.id &&
                Objects.equals(name, artist.name) &&
                Objects.equals(genre, artist.genre) &&
                Objects.equals(activities, artist.activities);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, genre);
    }
    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

}
