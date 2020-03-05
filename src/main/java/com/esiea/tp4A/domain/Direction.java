package com.esiea.tp4A.domain;

public enum Direction {

    NORTH, EAST, SOUTH, WEST;

    public Direction left() {
        int leftOrdinal = ordinal() - 1;
        return leftOrdinal == -1 ? WEST  : Direction.values()[leftOrdinal];
    }

    public Direction right() {
        int rightOrdinal = ordinal() + 1;
        return rightOrdinal == 4 ? NORTH  : Direction.values()[rightOrdinal];
    }
}
