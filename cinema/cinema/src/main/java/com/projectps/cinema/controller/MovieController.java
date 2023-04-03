package com.projectps.cinema.controller;

import com.projectps.cinema.entity.Movie;
import com.projectps.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
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

    @GetMapping("/allMovies")
    public List<Movie> findAllMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/byId/{id}")
    public Movie findMovieById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/byGenre/{genre}")
    public List<Movie> findMoviesByGenre(@PathVariable String genre) {
        return movieService.getMoviesByGenre(genre);
    }

    @GetMapping("/byYear/{year}")
    public List<Movie> findMoviesByYear(@PathVariable int year) {
        return movieService.getMoviesByYear(year);
    }

    @GetMapping("/byScore/{score}")
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
}
