package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class PlanetMapImplTest {

    @Test
    void init_map() {
        PlanetMapImpl planetMapImpl = new PlanetMapImpl(null);
        Assertions.assertThat(planetMapImpl).isEqualToComparingFieldByField(new PlanetMapImpl(null));
    }

    @Test
    void mapGeneration() {
        PlanetMapImpl planetMapImpl = new PlanetMapImpl(null);
        int [][] map = planetMapImpl.getMap();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                Assertions.assertThat(map[i][j]).isEqualTo(0);
            }
        }
    }

    //Test si on a bien 150 obstacles qui ont été générés (15% de la map)
    @Test
    void generationObstacles() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        planetMap.generateObstacles();
        Set<Position> positions;
        positions = planetMap.obstaclePositions();
        Assertions.assertThat(positions.size()).isEqualTo(1500);
    }

    @Test
    void getInfo() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        int info = planetMap.getInfo(0,0);
        Assertions.assertThat(info).isEqualTo(0);
    }

    @Test
    void setMapSquare() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        planetMap.MajMap(0,0,1);
        Assertions.assertThat(planetMap.getInfo(0,0)).isEqualTo(1);
        planetMap.MajMap(51,0,1);
        Assertions.assertThat(planetMap.getInfo(51,0)).isEqualTo(1);
        planetMap.MajMap(0,51,1);
        Assertions.assertThat(planetMap.getInfo(0,51)).isEqualTo(1);
        planetMap.MajMap(-50,0,1);
        Assertions.assertThat(planetMap.getInfo(-50,0)).isEqualTo(1);
        planetMap.MajMap(0,-50,1);
        Assertions.assertThat(planetMap.getInfo(0,-50)).isEqualTo(1);
    }

    @Test
    void getMap() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        int [][] map = planetMap.getMap();
        int [][] mapTest = new int[100][100];
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                mapTest[i][j] = 0;
            }
        }
        Assertions.assertThat(map).isEqualTo(mapTest);
    }

}
