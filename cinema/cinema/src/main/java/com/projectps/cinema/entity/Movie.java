package com.projectps.cinema.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie")

public class Movie {

    @Id
    @GeneratedValue
    private int id;
    private String title;
    @ElementCollection
    private List<String> genres;
    private int year;

    @OneToMany(targetEntity = Rating.class, cascade = CascadeType.ALL)
    private List<Rating> ratings;
    private double score;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Actor> actors;
}
