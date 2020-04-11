package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Set;

public class RadarImpl {

    private final Position currentPosition;
    private final PlanetMap planetMap;
    private final int size = 30;
    private final int startX;
    private final int startY;

    RadarImpl(MarsRoverImpl marsRover, PlanetMap planetmap) {
        currentPosition = marsRover.getCurrentPosition();
        planetMap = planetmap;
        startX = marsRover.getCurrentPosition().getX()-size/2;
        startY = marsRover.getCurrentPosition().getY()-size/2;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public PlanetMap getPlanetMap() {
        return planetMap;
    }

    /*public void displayRadar(){
        for(int y=0;y<size;y++){
            for(int x=0;x<size;x++){
                System.out.print(planetMap.getInfo(startX + x, startY + y));
            } System.out.println();
        }
    }*/

    public int checkLimit(int coord) {
        //System.out.print("Check limit : " + coord);
        if(coord > 50) coord = -49 + coord - 51;
        if(coord < -49) coord = 50 + coord + 50;
        //System.out.print(" | New coord : " + coord + "\n");
        return coord;
    }

    public Set<Position> obstaclePositions(PlanetMap planetMap) {
        Set<Position> positions = new HashSet<>();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                for (Position position : planetMap.obstaclePositions()) {
                    if (checkLimit(startX + x) == position.getX() && checkLimit(startY + y) == position.getY()) {
                        positions.add(Position.of(checkLimit(startX + x), checkLimit(startY + y), null));
                    }
                }
            }
        }
        System.out.println("Listes des obstacles : ");
        for (Position position : positions)
            System.out.println("X : " + position.getX() + " | Y : " + position.getY());
        return positions;
    }

    /*public Set<Position> playerPositions() {
        Set<Position> positions = new HashSet<>();
        for(int y=0; y<size; y++) { for(int x=0; x<size; x++) {
                if(planetMap.getInfo(checkLimit(startX + x), checkLimit(startY + y)) == 2) {
                    Position position = new Position.FixedPosition(startX + x, startY + y,null);
                    positions.add(position); } } }
        System.out.println("Listes des joueurs : ");
        for(Position position:positions) System.out.println("X : "+position.getX()+" | Y : " + position.getY());
        return positions;
    }*/

}
