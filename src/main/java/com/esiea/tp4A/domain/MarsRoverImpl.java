package com.esiea.tp4A.domain;

import java.util.Set;

public class MarsRoverImpl implements MarsRover {

    private Position currentPosition;
    private Direction currentDirection;
    //private PlanetMapImpl planetMap;

    MarsRoverImpl(int x, int y, Direction direction) {
        currentPosition = Position.of(x, y, direction);
        //planetMap = createPlanetMap();
    }

    public Position getCurrentPosition(){
        return currentPosition;
    }

    /*public PlanetMapImpl getPlanetMap() {
        return planetMap;
    }*/

    @Override
    public Position move (String command, PlanetMapImpl planetMap){
        boolean obstacle = this.obstacleDetection(command, planetMap, currentPosition);
        //boolean obstacle = false;
        if ("L".equals(command)) currentPosition = Position.of(currentPosition.getX(), currentPosition.getY(), currentPosition.getDirection().left());
        if(command.equals("R")) currentPosition = Position.of(currentPosition.getX(), currentPosition.getY(), currentPosition.getDirection().right());
        if(command.equals("F") && !obstacle) currentPosition = currentPosition.forward1();
        if(command.equals("B") && !obstacle) currentPosition = currentPosition.backward1();
        return currentPosition; }

    //Crée une carte de la planète et génère des obstacles
    public PlanetMapImpl createPlanetMap(){
        PlanetMapImpl planetMap = new PlanetMapImpl();
        planetMap.generateObstacles();
        return planetMap;
    }

    //Choix aléatoire d'une position initiale pour le rover où il n'y a pas d'obstacles
   /* public void randPositionInit(){
        do {
            int x = (int)(Math.random()*100)-49;
            int y = (int)(Math.random()*100)-49;
            currentPosition = Position.of(x,y,Direction.NORTH);
        }while(planetMap.getInfo(this.currentPosition.getX(), this.currentPosition.getY()) == 1);
    }*/

    //Positionnement du rover à côté d'un obstacle dans le cadre des tests, possibilité de préciser la position de l'obstacle par rapport à celle du rover
    //(obstacle devant ou derrière le Rover)
    public Position initPosNextToObstacle(String obstaclePosition, PlanetMapImpl planetMap){
        Set<Position> positions = planetMap.obstaclePositions();
        boolean posFound = false;
        planetMap.displayMap();
        for(Position pos:positions) {
            //System.out.println(pos.getX()+""+pos.getY());
            //System.out.println(planetMap.getInfo(pos.getX(), pos.getY()));
            if(!posFound && obstaclePosition.equals("NORTH") && planetMap.getInfo(pos.getX(), pos.getY()-1) == 0){
                currentPosition = Position.of(pos.getX(), pos.getY()-1, Direction.NORTH);
                posFound = true;
            }
            if(!posFound && obstaclePosition.equals("SOUTH") && planetMap.getInfo(pos.getX(), pos.getY()+1) == 0){
                currentPosition = Position.of(pos.getX(), pos.getY()+1, Direction.NORTH);
                posFound = true;
            }
        }
        System.out.println("Position choisie :" + currentPosition.getX() +" "+ currentPosition.getY());
        return currentPosition;
    }

    //Si on entre la commande pour avancer ou reculer, renvoie true si la case de destination contient un obstacle
    public boolean obstacleDetection(String command, PlanetMapImpl planetMap, Position checkPosition) {
        if(command.equals("F")) checkPosition = checkPosition.forward1();
        if(command.equals("B")) checkPosition = checkPosition.backward1();
        if(planetMap.getInfo(checkPosition.getX(), checkPosition.getY())==1) return true;
        /*
        if ((command.equals("F") && planetMap.getInfo(currentPosition.getX(), currentPosition.getY()+1)==1) ||
            (command.equals("B") && planetMap.getInfo(currentPosition.getX(), currentPosition.getY()-1)==1)) {obstacle=true;}*/
        return false;
    }

}
