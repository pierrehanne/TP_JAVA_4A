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
                    if ((int)(Math.random()*100) == 0 && obstaclesLeft > 0 && map[i][j] == 0) {
                        map[i][j] = 1;
                        obstaclesLeft--;
                    }
                }
            }
        }
    }

    //ajoute la valeur passée en paramètres à la case aux coordonnées spécifiées
    public void setMapSquare(int x, int y, int value){
        if(x==51) map[y+49][0] = value;
        if(y==51) map[0][x+49] = value;
        if(x==-50) map[y+49][99] = value;
        if(y==-50) map[99][x+49] = value;
        map[y+49][x+49] = value;
    }

    //renvoie le contenu d'une case de la map
    //prendre en compte le cas où on demande une case en dehors du tableau
    //map circulaire, revenir de l'autre côté en transformant les coordonnées entrées
    //-50 => 50
    //-151 => -1
    public int getInfo(int x, int y) {
        //********** rechercher la bonne formule **********//
        //if(x<-49){
            //int new_x = 50-(-x-49); //? pistes de recherche
            //int new_x = -(50-x%50); //? modulo ?
        //}
        if(x==51) return map[y+49][0];
        if(y==51) return map[0][x+49];
        if(x==-50)  return map[y+49][99];
        if(y==-50)  return map[99][x+49];
        return map[y+49][x+49];
    }

    public void displayMap(){
        int x, y;
        for(y=-49;y<=50;y++){
            for(x=-49;x<=50;x++){
                System.out.print(getInfo(x, y));
            }
            System.out.println("");
        }
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
        for(Position position:positions)
            System.out.println("X : "+position.getX()+" | Y : " + position.getY());
        return positions;
    }
}
