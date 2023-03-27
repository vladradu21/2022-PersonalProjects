package com.projectps.cinema.repository;

import com.projectps.cinema.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findByTitle(String title);
    List<Movie> findByGenresContaining(String genre);
    List<Movie> findByScoreGreaterThanEqual(double score);
}
