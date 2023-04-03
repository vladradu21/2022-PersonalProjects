package com.projectps.cinema.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "actor")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Actor {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private int age;

    private Gender gender;

    private String originCountry;

    @ManyToMany(mappedBy = "actors", fetch = FetchType.LAZY)

    @JsonBackReference("movie-actors")
    private List<Movie> movies;
}
