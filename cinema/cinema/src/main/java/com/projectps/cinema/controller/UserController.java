package com.projectps.cinema.controller;

import com.projectps.cinema.entity.Actor;
import com.projectps.cinema.entity.Movie;
import com.projectps.cinema.entity.Rating;
import com.projectps.cinema.entity.User;
import com.projectps.cinema.service.ActorService;
import com.projectps.cinema.service.MovieService;
import com.projectps.cinema.service.RatingService;
import com.projectps.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/addUsers")
    public List<User> addUsers(@RequestBody List<User> users) {
        return userService.saveUsers(users);
    }

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return userService.getUsers();
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @PostMapping("/adminAddMovie/{userId}")
    public Movie adminAddMovie(@PathVariable int userId, @RequestBody Movie movie) {
        User user = userService.getUserById(userId);
        if(user.isAdmin()) {
            return movieService.saveMovie(movie);
        }
        return null;
    }

    @DeleteMapping("/adminDeleteMovie/{id}")
    public void adminDeleteMovie(@PathVariable int userId, @PathVariable int id) {
        User user = userService.getUserById(userId);
        if(user.isAdmin()) {
            movieService.deleteMovie(id);
        }
    }

    @PutMapping("/adminUpdateMovie")
    public Movie adminUpdateMovie(@PathVariable int userId, @RequestBody Movie movie) {
        User user = userService.getUserById(userId);
        if(user.isAdmin()) {
            return movieService.updateMovie(movie);
        }
        return null;
    }

    @PostMapping("/adminAddActor/{userId}")
    public Actor adminAddActor(@PathVariable int userId, @RequestBody Actor actor) {
        User user = userService.getUserById(userId);
        if(user.isAdmin()) {
            return actorService.saveActor(actor);
        }
        return null;
    }

    @DeleteMapping("/adminDeleteActor/{id}")
    public void adminDeleteActor(@PathVariable int userId,@RequestBody int id) {
        User user = userService.getUserById(userId);
        if(user.isAdmin()) {
            actorService.deleteActor(id);
        }
    }

    @PutMapping("/adminUpdateActor")
    public Actor adminUpdateActor(@PathVariable int userId,@RequestBody Actor actor) {
        User user = userService.getUserById(userId);
        if(user.isAdmin()) {
            return actorService.updateActor(actor);
        }
        return null;
    }

    @PostMapping("/userAddRating")
    public Rating userAddRating(@RequestBody Rating rating) {
        return ratingService.saveRating(rating);
    }

    @DeleteMapping("/userDeleteRating/{id}")
    public void userDeleteRating(@PathVariable int id) {
        ratingService.deleteRating(id);
    }

    @PutMapping("/userUpdateRating")
    public Rating userUpdateRating(@RequestBody Rating rating) {
        return ratingService.updateRating(rating);
    }

}
