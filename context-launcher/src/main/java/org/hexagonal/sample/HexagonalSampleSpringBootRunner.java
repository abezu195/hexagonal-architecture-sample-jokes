package org.hexagonal.sample;

import org.hexagonal.sample.service.JokeServicePort;
import org.hexagonal.sample.service.JokeSourcePort;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(includeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {JokeServicePort.class, JokeSourcePort.class})})
public class HexagonalSampleSpringBootRunner {

    public static void main(String[] args) {
        SpringApplication.run(HexagonalSampleSpringBootRunner.class, args);
    }
}
