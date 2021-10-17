package org.hexagonal.sample.consoleui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hexagonal.sample.service.JokeServicePort;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
class ConsoleRunnerImpl implements CommandLineRunner {

    private final JokeServicePort jokeService;

    @Override
    public void run(String... args) {
        jokeService.getAll().forEach(log::info);
        log.info("Random daily joke " + jokeService.getRandom());
    }
}
