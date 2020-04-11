package com.esiea.tp4A.domain;
import java.util.Set;

public class MarsRoverImpl implements MarsRover {

    private Position currentPosition;
    //private Direction currentDirection;
    private PlanetMap planetMap; //was final before
    private int laserRange;

    MarsRoverImpl(int x, int y, Direction direction, PlanetMapImpl planetmap) {
        this.planetMap = planetmap;
        initialize(Position.of(x, y, direction));
        configureLaserRange(30);
    }

    public Position getCurrentPosition(){
        return currentPosition;
    }
    /*public PlanetMapImpl getPlanetMap() { return planetMap; }*/

    private void setPlanetMap(PlanetMap map){
        this.planetMap = map;
    }
    private void setLaserRange(int laserRange) { this.laserRange = laserRange; }

    @Override
    public MarsRover initialize(Position position) {
        this.currentPosition = position;
        planetMap.MajMap(position.getX(), position.getY(), 2);
        return this;
    }

    @Override
    public MarsRover updateMap(PlanetMap map) {
        this.setPlanetMap(map);
        return this;
    }

    @Override
    public MarsRover configureLaserRange(int range) {
        this.setLaserRange(range);
        return this;
    }

    @Override
    public Position move (String command){
        for(int i = 0; i<command.length();i++) {
            Character action = command.charAt(i);
            boolean obstacle = this.obstacleDetection(action, planetMap, currentPosition);
            planetMap.MajMap(currentPosition.getX(), currentPosition.getY(), 0);
            executeAction(action, obstacle); }
        return currentPosition;
    }

    public void executeAction(Character action, boolean obstacle){
        if (action.equals('l')) currentPosition = Position.of(currentPosition.getX(), currentPosition.getY(), currentPosition.getDirection().left());
        if (action.equals('r')) currentPosition = Position.of(currentPosition.getX(), currentPosition.getY(), currentPosition.getDirection().right());
        if (action.equals('f') && !obstacle) currentPosition = currentPosition.forward1();
        if (action.equals('b') && !obstacle) currentPosition = currentPosition.backward1();
        if (action.equals('s')) {
            LaserBeamImpl laserBeam = this.Shoot(planetMap);
            laserBeam.moveUntilCrash(planetMap);
        }
    }

    //Si on entre la commande pour avancer ou reculer, renvoie true si la case de destination contient un obstacle
    public boolean obstacleDetection(Character command, PlanetMap planetMap, Position checkPosition) {
        if(command.equals('f')) checkPosition = checkPosition.forward1();
        if(command.equals('b')) checkPosition = checkPosition.backward1();
        if(planetMap.getInfo(checkPosition.getX(), checkPosition.getY())==1) return true;
        return false;
    }

    //Si on entre la commande pour avancer ou reculer, renvoie true si un joueur est placé sur la case de destination
    public boolean playerDetection(String command, MarsRoverImpl marsRoverPlayer, Position checkPosition) {
        if(command.equals("f")) checkPosition = checkPosition.forward1();
        if(command.equals("b")) checkPosition = checkPosition.backward1();
        if(checkPosition.getX() == marsRoverPlayer.currentPosition.getX() && checkPosition.getY() == marsRoverPlayer.currentPosition.getY()) return true;
        else{ return false; }
    }

    //génère un nouveau missile dans la direction du Rover
    public LaserBeamImpl Shoot(PlanetMap planetMap){
        //boolean obstacles = obstacleDetection('f', planetMap, currentPosition);
        LaserBeamImpl laserBeam = initLaserBeam();
        laserBeam.obstacleCollisionCheck(planetMap);
        return laserBeam;
    }

    public LaserBeamImpl initLaserBeam(){
        Position position = Position.of(0,0, Direction.NORTH);
        if(currentPosition.getDirection() == Direction.SOUTH){ position = Position.of(currentPosition.getX(), currentPosition.getY()-1, currentPosition.getDirection());}
        if(currentPosition.getDirection() == Direction.NORTH){ position = Position.of(currentPosition.getX(), currentPosition.getY()+1, currentPosition.getDirection());}
        if(currentPosition.getDirection() == Direction.WEST){ position = Position.of(currentPosition.getX()-1, currentPosition.getY(), currentPosition.getDirection());}
        if(currentPosition.getDirection() == Direction.EAST){ position = Position.of(currentPosition.getX()+1, currentPosition.getY(), currentPosition.getDirection());}
        LaserBeamImpl laserBeam = new LaserBeamImpl(position, laserRange);
        return laserBeam;
    }
}
