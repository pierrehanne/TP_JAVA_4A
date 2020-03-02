package com.esiea.tp4A.domain;

public enum Direction {

    NORTH, EAST, SOUTH, WEST;

    public Direction left() {
        int leftOrdinal = ordinal() - 1;
        return leftOrdinal == -1 ? WEST  : Direction.values()[leftOrdinal];
        /*final Direction newDirection;
        if(leftOrdinal == -1) {
            newDirection = WEST;
        } else {
            newDirection = Direction.values()[leftOrdinal];
        }
        return newDirection;*/
    }

    public Direction right() {
        int rightOrdinal = ordinal() + 1;

        final Direction newDirection;
        if(rightOrdinal == 4) {
            newDirection = NORTH;
        } else {
            newDirection = Direction.values()[rightOrdinal];
        }
        return newDirection;
    }
    public Direction forward() {
        int forwardOrdinal = ordinal() + 1;

        final Direction newDirection;
        if(forwardOrdinal == 1) {
            newDirection = NORTH;
        } else {
            newDirection = Direction.values()[forwardOrdinal];
        }
        return newDirection;
    }
    public Direction south() {
        int southOrdinal = ordinal() + 1;

        final Direction newDirection;
        if(southOrdinal == 1) {
            newDirection = NORTH;
        } else {
            newDirection = Direction.values()[southOrdinal];
        }
        return newDirection;
    }


}
