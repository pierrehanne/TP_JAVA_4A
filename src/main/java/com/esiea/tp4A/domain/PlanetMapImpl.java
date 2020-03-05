package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PlanetMapImpl implements PlanetMap {

    private int [][] map;

    public PlanetMapImpl() {
        this.map = new int[100][100];
    }

    public int[][] getMap() {
        return map;
    }

    public void generateObstacles() {
        int obstaclesLeft = 150;
        while (obstaclesLeft > 0) {
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    if ((int)(Math.random()*10) == 0 && obstaclesLeft > 0 && map[i][j] == 0) {
                        map[i][j] = 1;
                        obstaclesLeft--;
                    }
                }
            }
        }
    }

    public int getInfo(int x, int y) {
        return map[y+49][x+49];
    }

    @Override
    public Set<Position> obstaclePositions() {
        Set<Position> positions = new HashSet<Position>();
        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                if(map[i][j] == 1) {
                    Position position = new Position.FixedPosition(j-49,i-49,null);
                    positions.add(position);
                }
            }
        }
        return positions;
    }
}
