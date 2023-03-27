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
@Table  (name = "actor")
public class Actor {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int age;
    private Gender gender;
    private String originCountry;

    @ManyToMany(mappedBy = "actors", cascade = CascadeType.ALL)
    private List<Movie> movies;
}
