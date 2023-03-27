package com.projectps.cinema.service;

import com.projectps.cinema.entity.Actor;
import com.projectps.cinema.entity.Gender;
import com.projectps.cinema.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public List<Actor> saveActors(Iterable<Actor> actors) {
        return actorRepository.saveAll(actors);
    }

    public List<Actor> getActors() {
        return actorRepository.findAll();
    }

    public Actor getActorById(int id) {
        return actorRepository.findById(id).orElse(null);
    }

    public Actor getActorByGender(Gender gender) {
        return actorRepository.findByGender(gender);
    }

    public Actor getActorByOriginCountry(String originCountry) {
        return actorRepository.findByOriginCountry(originCountry);
    }

    public Actor updateActor(Actor actor) {
        Actor existingActor = actorRepository.findById(actor.getId()).orElse(null);
        existingActor.setName(actor.getName());
        existingActor.setAge(actor.getAge());
        existingActor.setGender(actor.getGender());
        existingActor.setOriginCountry(actor.getOriginCountry());
        existingActor.setMovies(actor.getMovies());
        return actorRepository.save(existingActor);
    }

    public void deleteActor(int id) {
        actorRepository.deleteById(id);
    }

}
