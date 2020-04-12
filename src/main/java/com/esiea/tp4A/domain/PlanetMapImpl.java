package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PlanetMapImpl implements PlanetMap {

    private final int [][] map;
    private final int[] sizeTab = {100};
    private final int size;
    private Set<Position> obstacles;

    public PlanetMapImpl(Set<Position> obstaclePositions) {
        Random rand = new Random();
        size = rand.nextInt(sizeTab.length);
        this.map = new int[sizeTab[size]][sizeTab[size]];
        if(obstaclePositions!=null) this.setObstacles(obstaclePositions);
    }

    public int[][] getMap() {
        return map;
    }

    public void generateObstacles() {
        int obstaclesLeft = (int) (sizeTab[size] * 15);
        obstacles =  new HashSet<>();
        System.out.println("Nombre de cases : "+sizeTab[size] * sizeTab[size] + " | Nombre d'obstacles : " + obstaclesLeft);
        while (obstaclesLeft > 0) {
            for (int i = 0; i < sizeTab[size]; i++) { for (int j = 0; j < sizeTab[size]; j++) {
                    if ((int)(Math.random()*100) == 0 && obstaclesLeft > 0 && map[i][j] == 0) {
                        map[i][j] = 1;
                        obstaclesLeft--;
                        obstacles.add(Position.of(j-49,i-49,Direction.NORTH)); }}}}}

    public int getInfo(int x, int y) {
        if(x==51) return map[y+49][0];
        if(y==51) return map[0][x+49];
        if(x==-50)  return map[y+49][99];
        if(y==-50)  return map[99][x+49];
        return map[y+49][x+49];
    }

    public void MajMap(int x, int y, int value) {
        if(x==51) map[y+49][0] = value;
        if(y==51) map[0][x+49] = value;
        if(x==-50)  map[y+49][99] = value;
        if(y==-50)  map[99][x+49] = value;
        if(x!=51 && y!=51 && x!=-50 && y!=-50) map[y+49][x+49] = value;
    }

    @Override
    public Set<Position> obstaclePositions() {
        return this.obstacles;
    }

    public void setObstacles(Set<Position> obstaclePositions){
        this.obstacles = obstaclePositions;
    }
}
