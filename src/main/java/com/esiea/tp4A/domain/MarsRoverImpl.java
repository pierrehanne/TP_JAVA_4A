package com.esiea.tp4A.domain;
import java.util.Set;

public class MarsRoverImpl implements MarsRover {

    private Position currentPosition;
    //private Direction currentDirection;
    private PlanetMapImpl planetMap; //was final before

    MarsRoverImpl(int x, int y, Direction direction, PlanetMapImpl planetmap) {
        this.planetMap = planetmap;
        initialize(Position.of(x, y, direction));
    }

    public Position getCurrentPosition(){
        return currentPosition;
    }
    /*public PlanetMapImpl getPlanetMap() { return planetMap; }*/

    private void setPlanetMap(PlanetMapImpl map){
        this.planetMap = map;
    }

    @Override
    public MarsRover initialize(Position position) {
        this.currentPosition = position;
        planetMap.MajMap(position.getX(), position.getY(), 2);
        return this;
    }

    public MarsRover updateMap(PlanetMapImpl map) {
        setPlanetMap(map);
        return this;
    }

    /*public MarsRover updateMapPreviousVersion(PlanetMap map) {
        int [][] Map = planetMap.getMap();
        Map[currentPosition.getY()+49][currentPosition.getY()+49] = 2;
        return this;
    }*/

    @Override
    public Position move (String command){
        for(int i = 0; i<command.length();i++) {
            Character action = command.charAt(i);
            boolean obstacle = this.obstacleDetection(action, planetMap, currentPosition);
            planetMap.MajMap(currentPosition.getX(), currentPosition.getY(), 0);
            if (action.equals('l')) currentPosition = Position.of(currentPosition.getX(), currentPosition.getY(), currentPosition.getDirection().left());
            if (action.equals('r')) currentPosition = Position.of(currentPosition.getX(), currentPosition.getY(), currentPosition.getDirection().right());
            if (action.equals('f') && !obstacle) currentPosition = currentPosition.forward1();
            if (action.equals('b') && !obstacle) currentPosition = currentPosition.backward1(); }
        return currentPosition; }
    //TEST AFFICHAGE FONCTION MOVE// System.out.println(i+" X : " + currentPosition.getX()+" Y : "+ currentPosition.getY());

    //Crée une carte de la planète et génère des obstacles
    public PlanetMapImpl createPlanetMap(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        planetMap.generateObstacles();
        return planetMap;
    }

    //Positionnement du rover à côté d'un obstacle dans le cadre des tests, possibilité de préciser la position de l'obstacle par rapport à celle du rover (obstacle devant ou derrière le Rover)
    public Position initPosNextToObstacle(String obstaclePosition, PlanetMapImpl planetMap){
        Set<Position> positions = planetMap.obstaclePositions(); boolean posFound = false; planetMap.displayMap();
        for(Position pos:positions) {
            if(!posFound && obstaclePosition.equals("NORTH") && planetMap.getInfo(pos.getX(), pos.getY()-1) == 0){ currentPosition = Position.of(pos.getX(), pos.getY()-1, Direction.NORTH); posFound = true; }
            if(!posFound && obstaclePosition.equals("SOUTH") && planetMap.getInfo(pos.getX(), pos.getY()+1) == 0){ currentPosition = Position.of(pos.getX(), pos.getY()+1, Direction.NORTH); posFound = true; }
        } System.out.println("Position choisie :" + currentPosition.getX() +" "+ currentPosition.getY());
        return currentPosition;
    }

    //Si on entre la commande pour avancer ou reculer, renvoie true si la case de destination contient un obstacle
    public boolean obstacleDetection(Character command, PlanetMapImpl planetMap, Position checkPosition) {
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
        return false;
    }

    //génère un nouveau missile dans la direction du Rover
    public LaserBeamImpl Shoot(PlanetMapImpl planetMap){
        int laserBeamX = 0, laserBeamY = 0;
        boolean obstacles = obstacleDetection('f', planetMap, currentPosition);
        if(currentPosition.getDirection() == Direction.SOUTH && !obstacles){ laserBeamX = currentPosition.getX(); laserBeamY = currentPosition.getY()-1;}
        if(currentPosition.getDirection() == Direction.NORTH && !obstacles){ laserBeamX = currentPosition.getX(); laserBeamY = currentPosition.getY()+1;}
        if(currentPosition.getDirection() == Direction.WEST && !obstacles){ laserBeamX = currentPosition.getX()-1; laserBeamY = currentPosition.getY();}
        if(currentPosition.getDirection() == Direction.EAST && !obstacles){ laserBeamX = currentPosition.getX()+1; laserBeamY = currentPosition.getY();}
        LaserBeamImpl laserBeam = new LaserBeamImpl(laserBeamX, laserBeamY, currentPosition.getDirection());
        return laserBeam;
    }
}
