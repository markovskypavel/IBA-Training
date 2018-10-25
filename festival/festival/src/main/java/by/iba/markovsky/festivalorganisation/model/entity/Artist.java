package by.iba.markovsky.festivalorganisation.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "artists")
    @Fetch(value = FetchMode.SUBSELECT)
/*    @LazyCollection(LazyCollectionOption.TRUE)*/
    @Transient
    private List<Activity> activities = new ArrayList<>();

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
    public Artist(int id, String name, String genre, List<Activity> activities) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.activities = activities;
    }
    public Artist(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }
    public Artist(String name, String genre, List<Activity> activities) {
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
    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    //Getters
    public int getId() {
        return id;
    }
    public List<Activity> getActivities() {
        return activities;
    }
    public String getName() {
        return name;
    }
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
        return Objects.hash(id, name, genre, activities);
    }
    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", activities=" + activities +
                '}';
    }

}
