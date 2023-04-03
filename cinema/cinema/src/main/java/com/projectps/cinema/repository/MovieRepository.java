package com.projectps.cinema.repository;

import com.projectps.cinema.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByGenresContaining(String genre);

    List<Movie> findByScoreGreaterThanEqual(double score);

    List<Movie> findByYearGreaterThanEqual(int year);
}
