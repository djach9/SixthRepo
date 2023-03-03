package org.example;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class Movie {
    String title;
    LocalDate releaseDate;

    Director director;

    Genre genre;
    List<Actor> actors;
    public Movie(String title, LocalDate releaseDate, Director director, Genre genre, List<Actor> actors) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.director = director;
        this.genre = genre;
        this.actors = actors;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }


    public LocalDate getReleaseDate() {
        return releaseDate;
    }


    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Movie() {
    }
    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", director=" + director +
                ", genre=" + genre +
                ", actors=" + actors +
                '}';
    }


}
