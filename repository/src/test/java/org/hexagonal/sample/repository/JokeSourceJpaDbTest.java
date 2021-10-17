package org.hexagonal.sample.repository;

import org.hexagonal.sample.domain.Joke;
import org.hexagonal.sample.entity.JokeEntity;
import org.hexagonal.sample.mapper.JokeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class JokeSourceJpaDbTest {

    @Mock
    private JokeRepository jokeRepository;
    @Mock
    private JokeMapper jokeMapper;

    private JokeSourceJpaDb jokeSource;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        jokeSource = new JokeSourceJpaDb(jokeRepository, jokeMapper);
    }

    @Test
    void findAll_whenInvoked_returnsExpectedJokes() {
        JokeEntity jokeEntity = JokeEntity.builder("joke", "author").build();
        when(jokeRepository.findAll()).thenReturn(List.of(jokeEntity));

        List<Joke> actualJokes = jokeSource.findAll();

        assertThat(actualJokes).containsExactly(new Joke("joke", "author"));
    }

    @Test
    void findRandom_whenInvoked_returnsExpectedRandomJoke() {
        JokeEntity jokeEntity = JokeEntity.builder("joke", "author").build();
        when(jokeRepository.count()).thenReturn(5L);
        when(jokeRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(jokeEntity)));
        when(jokeMapper.map(jokeEntity)).thenReturn(new Joke("funny", "author"));

        Optional<Joke> randomJoke = jokeSource.findRandom();

        assertThat(randomJoke).contains(new Joke("funny", "author"));
    }

    @Test
    void save_whenInvoked_savesIntoRepoExpectedJokeEntity() {
        Joke joke = new Joke("Some funny joke!", "reddit-user");

        jokeSource.save(joke);

        ArgumentCaptor<JokeEntity> jokeEntityArgumentCaptor = ArgumentCaptor.forClass(JokeEntity.class);
        verify(jokeRepository).save(jokeEntityArgumentCaptor.capture());
        JokeEntity actualJoke = jokeEntityArgumentCaptor.getValue();
        JokeEntity expectedJoke = JokeEntity.builder("Some funny joke!", "reddit-user")
                .type(Joke.Type.UNKNOWN)
                .build();
        assertThat(expectedJoke).usingRecursiveComparison().isEqualTo(actualJoke);
    }
}