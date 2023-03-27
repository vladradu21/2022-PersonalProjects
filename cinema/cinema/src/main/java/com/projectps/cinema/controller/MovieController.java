package com.projectps.cinema.controller;

import com.projectps.cinema.entity.Actor;
import com.projectps.cinema.entity.Movie;
import com.projectps.cinema.entity.Rating;
import com.projectps.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @PostMapping("/addMovies")
    public List<Movie> addMovies(@RequestBody List<Movie> movies) {
        return movieService.saveMovies(movies);
    }

    @GetMapping("/movies")
    public List<Movie> findAllMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/movieById/{id}")
    public Movie findMovieById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/movieByTitle/{title}")
    public Movie findMovieByTitle(@PathVariable String title) {
        return movieService.getMovieByTitle(title);
    }

    @GetMapping("/moviesByGenre/{genre}")
    public List<Movie> findMoviesByGenre(@PathVariable String genre) {
        return movieService.getMoviesByGenre(genre);
    }

    @GetMapping("/moviesByScore/{score}")
    public List<Movie> findMoviesByScore(@PathVariable double score) {
        return movieService.getMoviesByScore(score);
    }

    @PutMapping("/updateMovie")
    public Movie updateMovie(@RequestBody Movie movie) {
        return movieService.updateMovie(movie);
    }

    @DeleteMapping("/deleteMovie/{id}")
    public void deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
    }

    @PostMapping("/addActorToMovie/{movieId}")
    public Movie addActorToMovie(@PathVariable int movieId, @RequestBody Actor actor) {
        return movieService.addActorToMovie(movieId, actor);
    }

    @PostMapping("/addActorsToMovie/{movieId}")
    public Movie addActorsToMovie(@PathVariable int movieId, @RequestBody List<Actor> actors) {
        return movieService.addActorsToMovie(movieId, actors);
    }

    @PostMapping("/addRatingToMovie/{movieId}")
    public Movie addRatingToMovie(@PathVariable int movieId, @RequestBody Rating rating) {
        return movieService.addRatingToMovie(movieId, rating);
    }
}
