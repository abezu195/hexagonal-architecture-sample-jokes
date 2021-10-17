package org.hexagonal.sample.service;

import org.hexagonal.sample.domain.Joke;

import java.util.List;
import java.util.Optional;

public interface JokeSourcePort {

    List<Joke> findAll();

    Optional<Joke> findRandom();

    void save(Joke joke);
}
