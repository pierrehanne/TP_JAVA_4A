package com.esiea.tp4A.domain;

public class MarsRoverImpl implements MarsRover {

    private Position currentPosition;
    private Direction currentDirection;

    MarsRoverImpl(int x, int y, Direction direction) {
        currentPosition = Position.of(x, y, direction);
    }

    @Override
    public Position move (String command){
        if ("L".equals(command)) currentPosition = Position.of(currentPosition.getX(), currentPosition.getY(), currentPosition.getDirection().left());
        if(command.equals("R")) currentPosition = Position.of(currentPosition.getX(), currentPosition.getY(), currentPosition.getDirection().right());
        if(command.equals("F")) currentPosition = currentPosition.forward1();
        if(command.equals("B")) currentPosition = currentPosition.backward1();
        return currentPosition;
    }

}
