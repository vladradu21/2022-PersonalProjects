package com.projectps.cinema.service;

import com.projectps.cinema.entity.Rating;
import com.projectps.cinema.repository.RatingRepository;
import com.projectps.cinema.service.impl.RatingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingServiceImpl ratingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveRating() {
        // Arrange
        Rating rating = new Rating();
        Mockito.when(ratingRepository.save(rating)).thenReturn(rating);

        // Act
        Rating result = ratingService.saveRating(rating);

        // Assert
        Assertions.assertEquals(rating, result);
    }

    @Test
    public void testGetRatings() {
        // Arrange
        List<Rating> ratings = Arrays.asList(new Rating(), new Rating());
        Mockito.when(ratingRepository.findAll()).thenReturn(ratings);

        // Act
        List<Rating> result = ratingService.getRatings();

        // Assert
        Assertions.assertEquals(ratings, result);
    }

    @Test
    public void testGetRatingsByScore() {
        // Arrange
        List<Rating> ratings = Arrays.asList(new Rating(), new Rating());
        double score = 1.0;
        Mockito.when(ratingRepository.findByScoreGreaterThanEqual(score)).thenReturn(ratings);

        // Act
        List<Rating> result = ratingService.getRatingsByScore(score);

        // Assert
        Assertions.assertEquals(ratings, result);
    }

    @Test
    public void testUpdateRating() {
        // Arrange
        Rating rating = new Rating();
        int id = 1;
        rating.setId(id);
        Mockito.when(ratingRepository.findById(rating.getId())).thenReturn(Optional.of(rating));
        Mockito.when(ratingRepository.save(rating)).thenReturn(rating);

        // Act
        Rating result = ratingService.updateRating(rating);

        // Assert
        Assertions.assertEquals(rating, result);
    }

    @Test
    public void testDeleteRating() {
        // Arrange
        Rating rating = new Rating();
        int id = 1;
        rating.setId(id);
        Mockito.when(ratingRepository.findById(rating.getId())).thenReturn(Optional.of(rating));

        // Act
        ratingService.deleteRating(id);

        // Assert
        Mockito.verify(ratingRepository, Mockito.times(1)).deleteById(id);
    }

}