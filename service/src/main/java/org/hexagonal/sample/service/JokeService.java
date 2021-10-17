package org.hexagonal.sample.service;

import lombok.RequiredArgsConstructor;
import org.hexagonal.sample.domain.Joke;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class JokeService implements JokeServicePort {

    private final JokeSourcePort jokeSource;

    @Override
    public List<String> getAll() {
        return jokeSource.findAll().stream().map(Joke::getText).collect(Collectors.toList());
    }

    @Override
    public String getRandom() {
        return jokeSource.findRandom().map(Joke::getText).orElse("");
    }
}
