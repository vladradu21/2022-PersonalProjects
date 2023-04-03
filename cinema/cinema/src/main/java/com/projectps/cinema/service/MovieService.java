package com.projectps.cinema.service;

import com.projectps.cinema.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {

    Movie saveMovie(Movie movie);

    List<Movie> saveMovies(List<Movie> movies);

    List<Movie> getMovies();

    Movie getMovieById(int id);

    List<Movie> getMoviesByGenre(String genre);

    List<Movie> getMoviesByScore(double score);

    List<Movie> getMoviesByYear(int year);

    Movie updateMovie(Movie movie);

    void deleteMovie(int id);


}
