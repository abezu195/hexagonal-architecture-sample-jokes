package org.hexagonal.sample.domain;

import lombok.Data;

@Data
public class Joke {

    public enum Type {
        FUNNY, DARK, DAD, UNKNOWN
    }

    private final String text;
    private final String author;

    private boolean funny = false;
    private Type type = Type.UNKNOWN;

    public void markAsFunny() {
        this.funny = true;
    }

    public void markJokeByType(Type type) {
        if (type == Type.UNKNOWN) {
            throw new IllegalArgumentException("UNKNOWN type can be only the default type. User cannot set it back again.");
        }
        this.type = type;
    }
}
