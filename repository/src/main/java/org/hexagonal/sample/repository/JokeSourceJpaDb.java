package org.hexagonal.sample.repository;

import org.hexagonal.sample.domain.Joke;
import org.hexagonal.sample.entity.JokeEntity;
import org.hexagonal.sample.mapper.JokeMapper;
import org.hexagonal.sample.service.JokeSourcePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Component
class JokeSourceJpaDb implements JokeSourcePort {

    private final JokeRepository jokeRepository;
    private final JokeMapper jokeMapper;
    private final Random random;

    JokeSourceJpaDb(JokeRepository jokeRepository, JokeMapper jokeMapper) {
        this.jokeRepository = jokeRepository;
        this.jokeMapper = jokeMapper;
        this.random = new Random();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Joke> findAll() {
        return jokeRepository.findAll().stream()
                .map(joke -> new Joke(joke.getText(), joke.getAuthor()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Joke> findRandom() {
        int jokeCount = (int) jokeRepository.count();
        int randomJokePage = random.nextInt(jokeCount);
        return jokeRepository.findAll(PageRequest.of(randomJokePage, 1)).stream()
                .map(jokeMapper::map)
                .findAny();
    }

    @Override
    @Transactional
    public void save(Joke joke) {
        jokeRepository.save(JokeEntity.builder(joke.getText(), joke.getAuthor())
                                    .funny(joke.isFunny())
                                    .type(joke.getType())
                                    .build());
    }
}
