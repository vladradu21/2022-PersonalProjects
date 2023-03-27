package com.projectps.cinema.controller;

import com.projectps.cinema.entity.Movie;
import com.projectps.cinema.entity.Rating;
import com.projectps.cinema.entity.User;
import com.projectps.cinema.service.ActorService;
import com.projectps.cinema.service.MovieService;
import com.projectps.cinema.service.RatingService;
import com.projectps.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MovieService movieService;

    @Autowired
    ActorService actorService;

    @Autowired
    RatingService ratingService;

    @PostMapping("/addUser")
    public User addUser(User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/addUsers")
    public List<User> addUsers(List<User> users) {
        return userService.saveUsers(users);
    }

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/usersByName/{name}")
    public List<User> findUsersByName(String name) {
        return userService.getUsersByName(name);
    }

    @GetMapping("/usersByEmail/{email}")
    public List<User> findUsersByEmail(String email) {
        return userService.getUsersByEmail(email);
    }

    @PutMapping("/updateUser")
    public User updateUser(User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(int id) {
        userService.deleteUser(id);
    }

    @PostMapping("/adminAddMovie{userId}}")
    public Movie adminAddMovie(int userId, Movie movie) {
        User user = userService.getUserById(userId);
        if(user.isAdmin()) {
            return movieService.saveMovie(movie);
        }
        return null;
    }

    @DeleteMapping("/adminDeleteMovie/{id}")
    public void adminDeleteMovie(int userId, int id) {
        User user = userService.getUserById(userId);
        if(user.isAdmin()) {
            movieService.deleteMovie(id);
        }
    }

    @PutMapping("/adminUpdateMovie")
    public Movie adminUpdateMovie(int userId, Movie movie) {
        User user = userService.getUserById(userId);
        if(user.isAdmin()) {
            return movieService.updateMovie(movie);
        }
        return null;
    }

    @PostMapping("/adminAddActor{userId}}")
    public Movie adminAddActor(int userId, Movie movie) {
        User user = userService.getUserById(userId);
        if(user.isAdmin()) {
            return movieService.saveMovie(movie);
        }
        return null;
    }

    @DeleteMapping("/adminDeleteActor/{id}")
    public void adminDeleteActor(int userId, int id) {
        User user = userService.getUserById(userId);
        if(user.isAdmin()) {
            actorService.deleteActor(id);
        }
    }

    @PutMapping("/adminUpdateActor")
    public Movie adminUpdateActor(int userId, Movie movie) {
        User user = userService.getUserById(userId);
        if(user.isAdmin()) {
            return movieService.updateMovie(movie);
        }
        return null;
    }

    @PostMapping("/userAddRating")
    public Rating userAddRating(Rating rating) {
        return ratingService.saveRating(rating);
    }

    @DeleteMapping("/userDeleteRating/{id}")
    public void userDeleteRating(int id) {
        ratingService.deleteRating(id);
    }

    @PutMapping("/userUpdateRating")
    public Rating userUpdateRating(Rating rating) {
        return ratingService.updateRating(rating);
    }

}
