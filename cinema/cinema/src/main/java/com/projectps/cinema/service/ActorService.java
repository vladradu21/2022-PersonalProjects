package com.projectps.cinema.service;

import com.projectps.cinema.entity.Actor;
import com.projectps.cinema.entity.Gender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActorService {

    Actor saveActor(Actor actor);

    List<Actor> saveActors(Iterable<Actor> actors);

    List<Actor> getActors();

    Actor getActorById(int id);

    Actor updateActor(Actor actor);

    void deleteActor(int id);
}
