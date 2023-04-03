package com.projectps.cinema.service.impl;

import com.projectps.cinema.entity.Rating;
import com.projectps.cinema.repository.RatingRepository;
import com.projectps.cinema.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> saveRatings(List<Rating> ratings) {
        return ratingRepository.saveAll(ratings);
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingsByScore(double score) {
        return ratingRepository.findByScoreGreaterThanEqual(score);
    }

    @Override
    public Rating updateRating(Rating rating) {
        Rating existingRating = ratingRepository.findById(rating.getId()).orElse(null);
        existingRating.setTitle(rating.getTitle());
        existingRating.setScore(rating.getScore());
        existingRating.setDescription(rating.getDescription());
        /*existingRating.setMovie(rating.getMovie());
        existingRating.setUser(rating.getUser());*/
        return ratingRepository.save(existingRating);
    }

    @Override
    public void deleteRating(int id) {
        ratingRepository.deleteById(id);
    }
}
