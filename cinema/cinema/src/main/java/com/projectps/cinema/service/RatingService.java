package com.projectps.cinema.service;

import com.projectps.cinema.entity.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {

    Rating saveRating(Rating rating);

    List<Rating> saveRatings(List<Rating> ratings);

    List<Rating> getRatings();

    List<Rating> getRatingsByScore(double score);

    Rating updateRating(Rating rating);

    void deleteRating(int id);

}