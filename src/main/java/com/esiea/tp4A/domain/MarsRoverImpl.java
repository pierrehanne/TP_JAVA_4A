package com.esiea.tp4A.domain;


public class MarsRoverImpl implements MarsRover {

    public MarsRoverImpl(int x, int y, Direction direction) {

    }

    @Override
    public Position move(String command) {
        return Position.of(0,0,Direction.NORTH);
    }
}
