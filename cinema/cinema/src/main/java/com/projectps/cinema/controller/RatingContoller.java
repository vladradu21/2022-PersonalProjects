package com.projectps.cinema.controller;

import com.projectps.cinema.entity.Rating;
import com.projectps.cinema.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingContoller {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/addRating")
    public void addRating(@RequestBody Rating rating) {
        ratingService.saveRating(rating);
    }

    @PostMapping("/addRatings")
    public void addRatings(@RequestBody List<Rating> ratings) {
        ratingService.saveRatings(ratings);
    }

    @GetMapping("/allRatings")
    public List<Rating> findAllRatings() {
        return ratingService.getRatings();
    }

    @GetMapping("/byScore/{score}")
    public List<Rating> findRatingsByScore(@PathVariable double score) {
        return ratingService.getRatingsByScore(score);
    }

    @PutMapping("/updateRating")
    public Rating updateRating(@RequestBody Rating rating) {
        return ratingService.updateRating(rating);
    }

    @DeleteMapping("/deleteRating/{id}")
    public void deleteRating(@PathVariable int id) {
        ratingService.deleteRating(id);
    }


}
