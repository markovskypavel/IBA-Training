package by.iba.markovsky.festivalorganisation.domain.entity;

import java.util.Objects;

public class Artist {

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
    public void setName(String name) {
        this.name = name;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    //Getters
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
        return Objects.equals(name, artist.name) &&
                Objects.equals(genre, artist.genre);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, genre);
    }
    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

}
