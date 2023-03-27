package com.projectps.cinema.service;

import com.projectps.cinema.entity.Actor;
import com.projectps.cinema.repository.ActorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class ActorServiceTest {

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private ActorService actorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveActor() {
        // Arrange
        Actor actor = new Actor();
        Mockito.when(actorRepository.save(actor)).thenReturn(actor);

        // Act
        Actor result = actorService.saveActor(actor);

        // Assert
        Assertions.assertEquals(actor, result);
    }

    @Test
    public void testGetActors() {
        // Arrange
        List<Actor> actors = Arrays.asList(new Actor(), new Actor());
        Mockito.when(actorRepository.findAll()).thenReturn(actors);

        // Act
        List<Actor> result = actorService.getActors();

        // Assert
        Assertions.assertEquals(actors, result);
    }

    @Test
    public void testGetActorById() {
        // Arrange
        Actor actor = new Actor();
        int id = 1;
        actor.setId(id);
        Mockito.when(actorRepository.findById(id)).thenReturn(Optional.of(actor));
        Mockito.when(actorRepository.save(actor)).thenReturn(actor);

        // Act
        Actor result = actorService.getActorById(id);

        // Assert
        Assertions.assertEquals(actor, result);
    }

    @Test
    public void testGetActorByGender() {
        // Arrange
        Actor actor = new Actor();
        Mockito.when(actorRepository.findByGender(actor.getGender())).thenReturn(actor);

        // Act
        Actor result = actorService.getActorByGender(actor.getGender());

        // Assert
        Assertions.assertEquals(actor, result);
    }

    @Test
    public void testGetActorByOriginCountry() {
        // Arrange
        Actor actor = new Actor();
        Mockito.when(actorRepository.findByOriginCountry(actor.getOriginCountry())).thenReturn(actor);

        // Act
        Actor result = actorService.getActorByOriginCountry(actor.getOriginCountry());

        // Assert
        Assertions.assertEquals(actor, result);
    }

    @Test
    public void testUpdateActor() {
        // Arrange
        Actor actor = new Actor();
        int id = 1;
        actor.setId(id);
        Mockito.when(actorRepository.findById(id)).thenReturn(Optional.of(actor));
        Mockito.when(actorRepository.save(actor)).thenReturn(actor);

        // Act
        Actor result = actorService.updateActor(actor);

        // Assert
        Assertions.assertEquals(actor, result);
    }

    @Test
    public void testDeleteActor() {
        // Arrange
        Actor actor = new Actor();
        int id = 1;
        actor.setId(id);
        Mockito.when(actorRepository.findById(id)).thenReturn(Optional.of(actor));

        // Act
        actorService.deleteActor(id);

        // Assert
        Mockito.verify(actorRepository, Mockito.times(1)).deleteById(id);
    }
}