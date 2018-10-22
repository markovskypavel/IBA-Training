package by.iba.markovsky.festivalorganisation.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class Artist implements Serializable {

    private static final long serialVersionUID = -4225549571954368009L;

    private int id;

    private Activity activity;

    private String name;
    private String genre;

    public Artist() {
    }
    public Artist(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }
    public Artist(Artist artist) {
        this.name = artist.name;
        this.genre = artist.genre;
    }

    //Setters
    public void setActivity(Activity activity) {
        this.activity = activity;
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
    public Activity getActivity() {
        return activity;
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
                Objects.equals(genre, artist.genre);
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
