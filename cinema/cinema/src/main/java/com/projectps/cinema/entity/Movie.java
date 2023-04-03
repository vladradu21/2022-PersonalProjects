package com.projectps.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movie")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    @ElementCollection
    private Set<String> genres;

    private int year;

    @OneToMany(targetEntity = Rating.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "movierating_fk", referencedColumnName = "id")
    private List<Rating> ratings;

    private double score;

    //@JsonManagedReference("movie-actors")
    @JsonIgnoreProperties("movies")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "movie_actor",
            joinColumns = {
                    @JoinColumn(name = "movie_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "actor_id", referencedColumnName = "id")
            })
    private List<Actor> actors;
}
