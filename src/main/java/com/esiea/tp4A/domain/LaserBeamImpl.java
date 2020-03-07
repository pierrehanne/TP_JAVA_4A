package com.esiea.tp4A.domain;

public class LaserBeamImpl {

    private Position currentPosition;
    private Direction currentDirection;
    private boolean destroyed;

    LaserBeamImpl(int x, int y, Direction direction) {
        currentPosition = Position.of(x, y, direction);
        destroyed = false;
    }

    public Position getCurrentPosition(){
        return currentPosition;
    }

    public Position move (PlanetMapImpl planetMap){
        if(!destroyed){
            if(currentDirection == Direction.SOUTH) currentPosition = Position.of(currentPosition.getX(), currentPosition.getY()-1, currentDirection);
            if(currentDirection == Direction.WEST) currentPosition = Position.of(currentPosition.getX()-1, currentPosition.getY(), currentDirection);
            if(currentDirection == Direction.NORTH) currentPosition = Position.of(currentPosition.getX(), currentPosition.getY()+1, currentDirection);
            if(currentDirection == Direction.EAST) currentPosition = Position.of(currentPosition.getX()+1, currentPosition.getY(), currentDirection);
        }
        obstacleCollisionCheck(planetMap);
        if(destroyed){ return null; }
        else{ return currentPosition;}
    }

    //si le laser rencontre un obstacle, ces deux éléments sont détruits
    public void obstacleCollisionCheck(PlanetMapImpl planetMap){
        if(planetMap.getInfo(currentPosition.getX(), currentPosition.getY())==1){
            planetMap.setMapSquare(currentPosition.getX(), currentPosition.getY(), 0);
            destroyed = true;
        }
    }

}
