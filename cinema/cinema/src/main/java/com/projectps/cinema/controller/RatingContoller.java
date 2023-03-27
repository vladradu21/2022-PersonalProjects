package com.projectps.cinema.controller;

import com.projectps.cinema.entity.Rating;
import com.projectps.cinema.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public class RatingContoller {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/addRating")
    public void addRating(Rating rating) {
        ratingService.saveRating(rating);
    }

    @PostMapping("/addRatings")
    public void addRatings(List<Rating> ratings) {
        ratingService.saveRatings(ratings);
    }

    @GetMapping("/ratings")
    public List<Rating> findAllRatings() {
        return ratingService.getRatings();
    }

    @GetMapping("/ratingsByScore/{score}")
    public List<Rating> findRatingsByScore(double score) {
        return ratingService.getRatingsByScore(score);
    }

    @PutMapping("/updateRating")
    public Rating updateRating(Rating rating) {
        return ratingService.updateRating(rating);
    }

    @DeleteMapping("/deleteRating/{id}")
    public void deleteRating(int id) {
        ratingService.deleteRating(id);
    }


}
