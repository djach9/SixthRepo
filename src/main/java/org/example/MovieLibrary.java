package org.example;

import java.util.ArrayList;
import java.util.List;


public class MovieLibrary {
    public List<Movie> movies;


    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public MovieLibrary(List<Movie> movies) {
        this.movies = movies;
    }

    public MovieLibrary() {
        movies = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> getMovies() {
        return movies;
    }
    @Override
    public String toString() {
        return "MovieLibrary{" +
                "movies=" + movies +
                '}';
    }
}
