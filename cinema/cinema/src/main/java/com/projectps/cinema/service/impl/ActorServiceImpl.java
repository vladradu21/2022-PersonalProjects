package com.projectps.cinema.service.impl;

import com.projectps.cinema.entity.Actor;
import com.projectps.cinema.entity.Gender;
import com.projectps.cinema.repository.ActorRepository;
import com.projectps.cinema.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public List<Actor> saveActors(Iterable<Actor> actors) {
        return actorRepository.saveAll(actors);
    }

    @Override
    public List<Actor> getActors() {
        return actorRepository.findAll();
    }

    @Override
    public Actor getActorById(int id) {
        return actorRepository.findById(id).orElse(null);
    }

    @Override
    public Actor updateActor(Actor actor) {
        Actor existingActor = actorRepository.findById(actor.getId()).get();
        existingActor.setName(actor.getName());
        existingActor.setAge(actor.getAge());
        existingActor.setGender(actor.getGender());
        existingActor.setOriginCountry(actor.getOriginCountry());
        existingActor.setMovies(actor.getMovies());
        return actorRepository.save(existingActor);
    }

    @Override
    public void deleteActor(int id) {
        actorRepository.deleteById(id);
    }
}
