package com.esiea.tp4A.domain;

public enum Direction {

    NORTH, EAST, SOUTH, WEST;

    public Direction left() {
        int leftOrdinal = ordinal() - 1;

        final Direction newDirection;
        if(leftOrdinal == -1) {
            newDirection = WEST;
        } else {
            newDirection = Direction.values()[leftOrdinal];
        }
        return newDirection;
    }
}
