package com.esiea.tp4A.domain;

public class LaserBeamImpl {

    private Position currentPosition;
    private final Direction currentDirection;
    private boolean destroyed;
    private final int range;
    private int distanceTravelled;

    LaserBeamImpl(Position position, int range) {
        currentPosition = position;
        currentDirection = position.getDirection();
        destroyed = false;
        distanceTravelled=0;
        this.range = range;
    }

    public Position getCurrentPosition(){
        return currentPosition;
    }
    public Boolean getDestroyed() { return destroyed; }
    public int getRange() { return range; }

    public void setDistanceTravelled(int distanceTravelled) { this.distanceTravelled = distanceTravelled; }
    public void setDestroyed(boolean destroyed){ this.destroyed = destroyed; }

    public Position move (PlanetMap planetMap){
        distanceTravelled++;
        rangeCheck();
        if(!destroyed){
            currentPosition = currentPosition.forward1();
            System.out.println("Position map:" + this.getCurrentPosition().getX() + "," + this.getCurrentPosition().getY());
            obstaclesDetection(planetMap);
            return currentPosition; }
        else{ return null; }
    }

    public void moveUntilCrash(PlanetMap planetMap){
        while(!destroyed){ move(planetMap); }
    }

    public void obstaclesDetection(PlanetMap planetMap) {
        if(planetMap.obstaclePositions() != null) {
            for (Position position : planetMap.obstaclePositions()) {
                if (position.getX() == currentPosition.getX() && position.getY() == currentPosition.getY()) {
                    planetMap.obstaclePositions().remove(position);
                    destroyed = true;
                    System.out.println("Destroy : " + destroyed); } }
            for (Position position : planetMap.obstaclePositions()) { System.out.println("OBSTACLES : X : " + position.getX() + " Y : " + position.getY()); } }
    }

    //vérifier que le laser n'a pas atteint sa portée maximale
    public void rangeCheck(){
        if(distanceTravelled>range){
            destroyed = true;
        }
    }

}
