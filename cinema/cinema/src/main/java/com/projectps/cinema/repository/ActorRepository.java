package com.projectps.cinema.repository;

import com.projectps.cinema.entity.Actor;
import com.projectps.cinema.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
