package com.projectps.cinema.service;

import com.projectps.cinema.entity.Actor;
import com.projectps.cinema.entity.Movie;
import com.projectps.cinema.entity.Rating;
import com.projectps.cinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> saveMovies(List<Movie> movies) {
        return movieRepository.saveAll(movies);
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie getMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenresContaining(genre);
    }

    public List<Movie> getMoviesByScore(double score) {
        return movieRepository.findByScoreGreaterThanEqual(score);
    }

    public Movie updateMovie(Movie movie) {
        Movie existingMovie = movieRepository.findById(movie.getId()).orElse(null);
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setGenres(movie.getGenres());
        existingMovie.setYear(movie.getYear());
        existingMovie.setRatings(movie.getRatings());
        existingMovie.setScore(movie.getScore());
        existingMovie.setActors(movie.getActors());
        return movieRepository.save(existingMovie);
    }

    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }

    public Movie addActorToMovie(int movieId, Actor actor) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        List<Actor> actors = movie.getActors();
        actors.add(actor);
        movie.setActors(actors);
        return movieRepository.save(movie);
    }

    public Movie addActorsToMovie(int movieId, List<Actor> actors) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        List<Actor> existingActors = movie.getActors();
        existingActors.addAll(actors);
        movie.setActors(existingActors);
        return movieRepository.save(movie);
    }

    public Movie addRatingToMovie(int movieId, Rating rating) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        List<Rating> ratings = movie.getRatings();
        ratings.add(rating);
        movie.setRatings(ratings);
        return movieRepository.save(movie);
    }
}
