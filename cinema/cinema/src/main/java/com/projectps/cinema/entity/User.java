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
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private int id;
    private boolean isAdmin;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;
}
