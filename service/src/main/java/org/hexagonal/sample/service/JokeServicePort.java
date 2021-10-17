package org.hexagonal.sample.service;

import java.util.List;

public interface JokeServicePort {
    List<String> getAll();

    String getRandom();
}
