package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) throws IOException {
        /*
        // Stworzenie jsona, zostawiam na dowód :)
        Director director1 = new Director("Christopher", "Nolan");

        List<Actor> actors1 = new ArrayList<>();
        actors1.add(new Actor("Ellen", "Page"));
        actors1.add(new Actor("Leonardo", "DiCaprio"));

        Movie movie1 = new Movie("Inception", LocalDate.of(2010, 7, 8), director1, Genre.SCIENCE_FICTION, actors1);

        Director director2 = new Director("Francis Ford", "Coppola");

        List<Actor> actors2 = new ArrayList<>();
        actors2.add(new Actor("Marlon", "Brando"));
        actors2.add(new Actor("Al", "Pacino"));
        actors2.add(new Actor("James", "Caan"));

        Movie movie2 = new Movie("The Godfather", LocalDate.of(1972, 3, 15), director2, Genre.ACTION, actors2);

        Director director3 = new Director("James", " Cameron");
        List<Actor> actors3 = new ArrayList<>();
        actors3.add(new Actor("Leonardo", "DiCaprio"));
        actors3.add(new Actor("Kate", "Winslet"));
        actors3.add(new Actor("Kathy", "Bates"));

        Movie movie3 = new Movie("Titanic", LocalDate.of(1997, 12, 19), director3, Genre.DRAMA, actors3);


        Director director4 = new Director("Colin", "Trevorrow");
        List<Actor> actors4 = new ArrayList<>();
        actors4.add(new Actor("Chris", "Pratt"));
        actors4.add(new Actor("Bryce Dallas", "Howard"));
        actors4.add(new Actor("Irrfan", "Khan"));

        Movie movie4 = new Movie("Jurassic World", LocalDate.of(2015, 6, 10), director4, Genre.ACTION, actors4);


        MovieLibrary movieLibrary = new MovieLibrary();
        movieLibrary.addMovie(movie1);
        movieLibrary.addMovie(movie2);
        movieLibrary.addMovie(movie3);
        movieLibrary.addMovie(movie4);

        System.out.println(movieLibrary);

        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        File file = new File("movieLibrary.json");

        objectMapper.writeValue(file, movieLibrary); */


        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        String json = Files.readString(Paths.get("movieLibrary.json"));
        MovieLibrary movieLibrary = objectMapper.readValue(json, MovieLibrary.class);


        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Enter two dates to display the titles of movies released between these dates");
            System.out.println("2. Display all information about a random movie");
            System.out.println("3. Enter the name and surname of the actor to display the titles of the films he starred in");
            System.out.println("0. Close ");

            System.out.print("Select option: \n");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> findMoviesBetweenDates(movieLibrary);
                case 2 -> printRandomMovieInfo(movieLibrary);
                case 3 -> findMoviesWithActor(movieLibrary);
                case 0 -> System.out.println("End program");
                default -> System.out.println("Unknown option\n");
            }

            System.out.println();
        } while (choice != 0);

    }

    public static void printRandomMovieInfo(MovieLibrary movieLibrary) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(movieLibrary.getMovies().size());
        Movie randomMovie = movieLibrary.getMovies().get(randomIndex);

        System.out.printf("Title: %s%n", randomMovie.getTitle());
        System.out.printf("Release date: %s%n", randomMovie.getReleaseDate());
        System.out.printf("Director: %s %s%n", randomMovie.getDirector().getName(), randomMovie.getDirector().getSurname());
        System.out.printf("Genre: %s%n", randomMovie.getGenre());
        System.out.println("Actors:");
        for (Actor actor : randomMovie.getActors()) {
            System.out.printf("- %s %s%n", actor.getName(), actor.getSurname());
        }
    }


    public static void findMoviesWithActor(MovieLibrary movieLibrary) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the actor's name: ");
        String actorName = scanner.nextLine();

        List<Movie> matchingMovies = movieLibrary.getMovies().stream()
                .filter(movie -> movie.getActors().stream().anyMatch(actor -> actor.getName().equalsIgnoreCase(actorName)))
                .toList();

        if (matchingMovies.isEmpty()) {
            System.out.println("No movies found with this actor.\n");
        } else {
            System.out.println("Movies with actor " + actorName + ":");
            for (Movie movie : matchingMovies) {
                System.out.println("- " + movie.getTitle());
            }
        }
    }

    public static void findMoviesBetweenDates(MovieLibrary movieLibrary) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the start date (in the format YYYY-MM-DD): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Enter the end date (in the format YYYY-MM-DD): ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());

            if (endDate.isBefore(startDate)) {
                System.out.println("Error: The end date is earlier than the start date.\n");
                return;
            }

            List<String> moviesBetweenDates = movieLibrary.getMovies().stream()
                    .filter(movie -> movie.getReleaseDate().isAfter(startDate) && movie.getReleaseDate().isBefore(endDate))
                    .map(Movie::getTitle)
                    .toList();

            if (moviesBetweenDates.isEmpty()) {
                System.out.println("No movies released between these dates were found.\n");
            } else {
                System.out.println("Movies released between these dates:\n");
                moviesBetweenDates.forEach(title -> System.out.println("- " + title));
            }

        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format.\n");
        }
    }
}

