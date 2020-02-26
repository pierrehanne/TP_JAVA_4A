package com.esiea.tp4A.domain;

public class MarsRoverImpl implements MarsRover {

    private Position currentPosition;

    public MarsRoverImpl(int x, int y, Direction direction) {
        currentPosition = Position.of(x,y,direction);
    }

    @Override
    public Position move(String command) {

        if(command.equals("R")){
            currentPosition = Position.of(currentPosition.getX(), currentPosition.getY(), currentPosition.getDirection().right());
        }
        return currentPosition;
    }
}
