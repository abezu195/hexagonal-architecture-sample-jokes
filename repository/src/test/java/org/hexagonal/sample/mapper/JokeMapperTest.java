package org.hexagonal.sample.mapper;

import org.assertj.core.api.Assertions;
import org.hexagonal.sample.domain.Joke;
import org.hexagonal.sample.entity.JokeEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JokeMapperTest {

    private final JokeMapperImpl mapper = new JokeMapperImpl();

    @Test
    void map_whenValueIsNull_returnsNull() {
        assertThat(mapper.map(null)).isNull();
    }

    @Test
    void map_whenValueIsNotNull_returnsExpectedEntity() {
        JokeEntity entity = JokeEntity.builder("Some random joke", "author")
                .funny(true).type(Joke.Type.DAD)
                .build();

        Joke actualJoke = mapper.map(entity);

        Joke expected = new Joke("Some random joke", "author");
        expected.setFunny(true);
        expected.setType(Joke.Type.DAD);
        Assertions.assertThat(actualJoke).usingRecursiveComparison().isEqualTo(expected);
    }
}