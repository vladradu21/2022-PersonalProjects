package com.projectps.cinema.service;

import com.projectps.cinema.entity.Movie;
import com.projectps.cinema.repository.MovieRepository;
import com.projectps.cinema.service.impl.MovieServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testSaveMovie() {
        //Arrange
        Movie movie = new Movie();
        Mockito.when(movieRepository.save(movie)).thenReturn(movie);

        //Act
        Movie result = movieService.saveMovie(movie);

        //Assert
        Assertions.assertEquals(movie, result);

    }

    @Test
    public void testGetMovies() {
        // Arrange
        List<Movie> movies = Arrays.asList(new Movie(), new Movie());
        Mockito.when(movieRepository.findAll()).thenReturn(movies);

        // Act
        List<Movie> result = movieService.getMovies();

        // Assert
        Assertions.assertEquals(movies, result);
    }

    @Test
    public void testGetMovieById() {
        // Arrange
        Movie movie = new Movie();
        int id = 1;
        movie.setId(id);
        Mockito.when(movieRepository.findById(id)).thenReturn(Optional.of(movie));

        // Act
        Movie result = movieService.getMovieById(id);

        // Assert
        Assertions.assertEquals(movie, result);
    }


    @Test
    public void testGetMoviesByGenre() {
        // Arrange
        List<Movie> movies = Arrays.asList(new Movie(), new Movie());
        String genre = "Action";
        Mockito.when(movieRepository.findByGenresContaining(genre)).thenReturn(movies);

        // Act
        List<Movie> result = movieService.getMoviesByGenre(genre);

        // Assert
        Assertions.assertEquals(movies, result);
    }

    @Test
    public void testGetMoviesByScore() {
        // Arrange
        List<Movie> movies = Arrays.asList(new Movie(), new Movie());
        double score = 7.0;
        Mockito.when(movieRepository.findByScoreGreaterThanEqual(score)).thenReturn(movies);

        // Act
        List<Movie> result = movieService.getMoviesByScore(score);

        // Assert
        Assertions.assertEquals(movies, result);
    }

    @Test
    public void testUpdateMovie() {
        // Arrange
        Movie movie = new Movie();
        int id = 1;
        movie.setId(id);
        Mockito.when(movieRepository.findById(id)).thenReturn(Optional.of(movie));
        Mockito.when(movieRepository.save(movie)).thenReturn(movie);

        // Act
        Movie result = movieService.updateMovie(movie);

        // Assert
        Assertions.assertEquals(movie, result);
    }

    @Test
    public void testDeleteMovie() {
        // Arrange
        Movie movie = new Movie();
        int id = 1;
        movie.setId(id);
        Mockito.when(movieRepository.findById(id)).thenReturn(Optional.of(movie));

        // Act
        movieService.deleteMovie(id);

        // Assert
        Mockito.verify(movieRepository, Mockito.times(1)).deleteById(id);
    }
}