package com.projectps.cinema.service.impl;

import com.projectps.cinema.entity.Movie;
import com.projectps.cinema.repository.MovieRepository;
import com.projectps.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> saveMovies(List<Movie> movies) {
        return movieRepository.saveAll(movies);
    }

    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenresContaining(genre);
    }

    @Override
    public List<Movie> getMoviesByScore(double score) {
        return movieRepository.findByScoreGreaterThanEqual(score);
    }

    @Override
    public List<Movie> getMoviesByYear(int year) {
        return movieRepository.findByYearGreaterThanEqual(year);
    }

    @Override
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

    @Override
    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }
}
