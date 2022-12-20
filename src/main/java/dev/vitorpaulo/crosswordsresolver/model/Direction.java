package dev.vitorpaulo.crosswordsresolver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Direction {

    RIGHT(1, 0),
    LEFT(-1, 0),
    TOP(0, 1),
    DOWN(0, -1),
    TOP_RIGHT(1, 1),
    TOP_LEFT(-1, 1),
    DOWN_RIGHT(1, -1),
    DOWN_LEFT(-1, -1);

    private final Integer index;
    private final Integer line;
}
