package org.hexagonal.sample.mapper;

import org.hexagonal.sample.domain.Joke;
import org.hexagonal.sample.entity.JokeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JokeMapper {

    Joke map(JokeEntity joke);
}
