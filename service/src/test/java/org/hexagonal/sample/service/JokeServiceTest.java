package org.hexagonal.sample.service;

import org.hexagonal.sample.domain.Joke;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class JokeServiceTest {

    @Mock
    private JokeSourcePort jokeSourcePort;

    private JokeService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new JokeService(jokeSourcePort);
    }

    @Test
    void getAll_whenInvoked_returnsExpectedJokeList() {
        when(jokeSourcePort.findAll()).thenReturn(List.of(new Joke("Funny joke", "unknown")));

        List<String> actualJokes = service.getAll();

        assertThat(actualJokes).containsExactly("Funny joke");
    }

    @Test
    void getRandom_whenSourceReturnEmptyOptional_returnEmptyString() {
        when(jokeSourcePort.findRandom()).thenReturn(Optional.empty());

        String actualRandomJoke = service.getRandom();

        assertThat(actualRandomJoke).isEmpty();
    }

    @Test
    void getRandom_whenSourceReturnsOptionalOfJoke_returnJokeText() {
        when(jokeSourcePort.findRandom()).thenReturn(Optional.of(new Joke("Funny joke", "unknown")));

        String actualRandomJoke = service.getRandom();

        assertThat(actualRandomJoke).isEqualTo("Funny joke");
    }
}