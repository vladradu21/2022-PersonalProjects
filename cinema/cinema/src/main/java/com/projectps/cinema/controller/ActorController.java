package com.projectps.cinema.controller;

import com.projectps.cinema.entity.Actor;
import com.projectps.cinema.entity.Gender;
import com.projectps.cinema.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping("/addActor")
    public Actor addActor(@RequestBody Actor actor) {
        return actorService.saveActor(actor);
    }

    @PostMapping("/addActors")
    public List<Actor> addActors(@RequestBody List<Actor> actors) {
        return actorService.saveActors(actors);
    }

    @GetMapping("/allActors")
    public List<Actor> findAllActors() {
        return actorService.getActors();
    }

    @GetMapping("/byId/{id}")
    public Actor findActorById(@PathVariable int id) {
        return actorService.getActorById(id);
    }

    @PutMapping("/updateActor")
    public Actor updateActor(@RequestBody Actor actor) {
        return actorService.updateActor(actor);
    }

    @DeleteMapping("/deleteActor/{id}")
    public void deleteActor(@PathVariable int id) {
        actorService.deleteActor(id);
    }
}
