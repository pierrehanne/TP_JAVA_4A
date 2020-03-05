package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class PlanetMapImplTest {

    @Test
    void init_map() {
        PlanetMapImpl planetMapImpl = new PlanetMapImpl();
        Assertions.assertThat(planetMapImpl).isEqualToComparingFieldByField(new PlanetMapImpl());
    }

    @Test
    void mapGeneration() {
        PlanetMapImpl planetMapImpl = new PlanetMapImpl();
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
        PlanetMapImpl planetMap = new PlanetMapImpl();
        planetMap.generateObstacles();
        Set<Position> positions;
        positions = planetMap.obstaclePositions();
        Assertions.assertThat(positions.size()).isEqualTo(150);
    }

    //Test si la liste des positions des obstacles sont bien à 1
    @Test
    void obstaclesPositions() {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        planetMap.generateObstacles();
        Set <Position> positions = planetMap.obstaclePositions();
        for(Position pos:positions) {
            Assertions.assertThat(planetMap.getInfo(pos.getX(), pos.getY())).isEqualTo(1);
        }
    }
}
