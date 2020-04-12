package com.esiea.tp4A.domain;

public class MarsRoverImpl implements MarsRover {

    private Position currentPosition;
    private PlanetMap planetMap;
    private int laserRange;
    private boolean isAlive;

    public boolean isAlive() {
        return isAlive;
    }

    public MarsRoverImpl(int x, int y, Direction direction, PlanetMapImpl planetmap) {
        this.planetMap = planetmap;
        initialize(Position.of(x, y, direction));
        configureLaserRange(30);
        isAlive = true;
    }
    public int getLaserRange() { return laserRange; }
    public Position getCurrentPosition(){ return currentPosition; }
    public PlanetMap getPlanetMap() { return planetMap; }

    private void setPlanetMap(PlanetMap map){
        this.planetMap = map;
    }
    private void setLaserRange(int laserRange) { this.laserRange = laserRange; }

    @Override
    public MarsRover initialize(Position position) {
        this.currentPosition = position;
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
            boolean obstacle = this.obstaclesDetection(action, currentPosition);//obstacleDetection(action, planetMap, currentPosition);
            executeAction(action, obstacle); }
        return currentPosition;
    }

    public boolean obstaclesDetection(Character command, Position checkPosition) {
        if(command.equals('f')) checkPosition = checkPosition.forward1();
        if(command.equals('b')) checkPosition = checkPosition.backward1();
        if(planetMap.obstaclePositions() != null) {
            for (Position position : planetMap.obstaclePositions()) {
                if (position.getX() == checkPosition.getX() && position.getY() == checkPosition.getY())
                    return true; } }
        return false;
    }

    public void executeAction(Character action, boolean obstacle){
        if (action.equals('l')) currentPosition = Position.of(currentPosition.getX(), currentPosition.getY(), currentPosition.getDirection().left());
        if (action.equals('r')) currentPosition = Position.of(currentPosition.getX(), currentPosition.getY(), currentPosition.getDirection().right());
        if (action.equals('f') && !obstacle) currentPosition = currentPosition.forward1();
        if (action.equals('b') && !obstacle) currentPosition = currentPosition.backward1();
        if (action.equals('s')) {
            LaserBeamImpl laserBeam = this.Shoot(planetMap);
            laserBeam.moveUntilCrash(planetMap); }
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
        LaserBeamImpl laserBeam = initLaserBeam();
        laserBeam.obstaclesDetection(planetMap);
        return laserBeam;
    }

    public LaserBeamImpl initLaserBeam(){
        LaserBeamImpl laserBeam = new LaserBeamImpl(Position.of(this.getCurrentPosition().getX(), this.getCurrentPosition().getY(), this.getCurrentPosition().getDirection()), laserRange);
        return laserBeam;
    }
}
