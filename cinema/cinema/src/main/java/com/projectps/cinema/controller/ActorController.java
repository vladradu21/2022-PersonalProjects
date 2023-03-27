package com.projectps.cinema.controller;

import com.projectps.cinema.entity.Actor;
import com.projectps.cinema.entity.Gender;
import com.projectps.cinema.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping("/addActor")
    public Actor addActor(Actor actor) {
        return actorService.saveActor(actor);
    }

    @PostMapping("/addActors")
    public List<Actor> addActors(List<Actor> actors) {
        return actorService.saveActors(actors);
    }

    @GetMapping("/actors")
    public List<Actor> findAllActors() {
        return actorService.getActors();
    }

    @GetMapping("/actorById/{id}")
    public Actor findActorById(int id) {
        return actorService.getActorById(id);
    }

    @GetMapping("/actorByGender/{gender}")
    public Actor findActorByGender(Gender gender) {
        return actorService.getActorByGender(gender);
    }

    @GetMapping("/actorByOriginCountry/{originCountry}")
    public Actor findActorByOriginCountry(String originCountry) {
        return actorService.getActorByOriginCountry(originCountry);
    }

    @PutMapping("/updateActor")
    public Actor updateActor(Actor actor) {
        return actorService.updateActor(actor);
    }

    @DeleteMapping("/deleteActor/{id}")
    public void deleteActor(int id) {
        actorService.deleteActor(id);
    }
}
