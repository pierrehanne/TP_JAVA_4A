package com.esiea.tp4A.domain;

public class MarsRoverImpl implements MarsRover {

    private Position currentPosition;
    private Direction currentDirection;

    MarsRoverImpl(int x, int y, Direction direction) {
        currentPosition = Position.of(x, y, direction);
    }

    @Override
    public Position move (String command){

        if ("L".equals(command)) {
            currentPosition = Position.of(currentPosition.getX(), currentPosition.getY(), currentPosition.getDirection().left());
        }
        if(command.equals("R")){
            currentPosition = Position.of(currentPosition.getX(), currentPosition.getY(), currentPosition.getDirection().right());
        }
        if(command.equals("F")) {
            currentDirection = currentPosition.getDirection();
            switch (currentDirection) {
                case NORTH:
                    currentPosition = Position.of(currentPosition.getX(), currentPosition.getY() + 1, currentPosition.getDirection());
                    break;
                case WEST:
                    currentPosition = Position.of(currentPosition.getX() - 1, currentPosition.getY(), currentPosition.getDirection());
                    break;
                case SOUTH:
                    currentPosition = Position.of(currentPosition.getX(), currentPosition.getY() - 1, currentPosition.getDirection());
                    break;
                case EAST:
                    currentPosition = Position.of(currentPosition.getX() + 1, currentPosition.getY(), currentPosition.getDirection());
                    break;
            }
        }
        if(command.equals("B")) {
            currentDirection = currentPosition.getDirection();
            switch (currentDirection) {
                case NORTH:
                    currentPosition = Position.of(currentPosition.getX(), currentPosition.getY() - 1, currentPosition.getDirection());
                    break;
                case WEST:
                    currentPosition = Position.of(currentPosition.getX() + 1, currentPosition.getY(), currentPosition.getDirection());
                    break;
                case SOUTH:
                    currentPosition = Position.of(currentPosition.getX(), currentPosition.getY() + 1, currentPosition.getDirection());
                    break;
                case EAST:
                    currentPosition = Position.of(currentPosition.getX() - 1, currentPosition.getY(), currentPosition.getDirection());
                    break;
            }
        }
        return currentPosition;
    }

}
