package com.projectps.cinema.service;

import com.projectps.cinema.entity.Rating;
import com.projectps.cinema.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List<Rating> saveRatings(List<Rating> ratings) {
        return ratingRepository.saveAll(ratings);
    }

    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    public List<Rating> getRatingsByScore(double score) {
        return ratingRepository.findByScoreGreaterThanEqual(score);
    }

    public Rating updateRating(Rating rating) {
        Rating existingRating = ratingRepository.findById(rating.getId()).orElse(null);
        existingRating.setTitle(rating.getTitle());
        existingRating.setScore(rating.getScore());
        existingRating.setDescription(rating.getDescription());
        existingRating.setMovie(rating.getMovie());
        existingRating.setUser(rating.getUser());
        return ratingRepository.save(existingRating);
    }

    public void deleteRating(int id) {
        ratingRepository.deleteById(id);
    }
}
