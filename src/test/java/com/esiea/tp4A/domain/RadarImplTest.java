package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RadarImplTest {

    @Test
    void InitialiseRadarPosition() {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        RadarImpl radar = new RadarImpl(Position.of(0,0,Direction.NORTH), planetMap);
        Assertions.assertThat(radar.getCurrentPosition()).isEqualTo(Position.of(0,0,Direction.NORTH));
    }

    @Test
    void InitialiseRadarMap() {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        RadarImpl radar = new RadarImpl(Position.of(0,0,Direction.NORTH), planetMap);
        Assertions.assertThat(radar.getPlanetMap()).isEqualTo(planetMap);
    }

    @Test
    void DisplayRadarFromCenter() {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        planetMap.generateObstacles();
        MarsRoverImpl marsRover = new MarsRoverImpl(1,1,Direction.NORTH, planetMap);
        RadarImpl radar = new RadarImpl(Position.of(0,0,Direction.NORTH), planetMap);
        radar.displayRadar();
        Assertions.assertThat(radar.getPlanetMap()).isEqualTo(planetMap);
    }

    @Test
    void obstaclePositions() {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        planetMap.generateObstacles();
        MarsRoverImpl marsRover = new MarsRoverImpl(1,1,Direction.NORTH, planetMap);
        RadarImpl radar = new RadarImpl(Position.of(0,0,Direction.NORTH), planetMap);
        Set<Position> positions = radar.obstaclePositions();
        for(Position position : positions) {
            Assertions.assertThat(position.getY()).isLessThan(15);
            Assertions.assertThat(position.getX()).isLessThan(15);
            Assertions.assertThat(position.getY()).isGreaterThan(-16);
            Assertions.assertThat(position.getX()).isGreaterThan(-16);
        }
    }

    @Test
    void playerPositions() {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        planetMap.generateObstacles();
        MarsRoverImpl marsRover = new MarsRoverImpl(-48,0,Direction.NORTH, planetMap);
        RadarImpl radar = new RadarImpl(Position.of(45,0,Direction.NORTH), planetMap);
        Set<Position> positions = radar.playerPositions();
        Assertions.assertThat(positions.size()).isEqualTo(1);
    }

    @Test
    void playerPositionsLimitsXPositiveToNegative() {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        planetMap.generateObstacles();
        MarsRoverImpl marsRover = new MarsRoverImpl(-48,0,Direction.NORTH, planetMap);
        RadarImpl radar = new RadarImpl(Position.of(45,0,Direction.NORTH), planetMap);
        Set<Position> positions = radar.playerPositions();
        Assertions.assertThat(positions.size()).isEqualTo(1);
    }

    @Test
    void playerPositionsLimitsYPositiveToNegative() {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        planetMap.generateObstacles();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,-47,Direction.NORTH, planetMap);
        RadarImpl radar = new RadarImpl(Position.of(0,49,Direction.NORTH), planetMap);
        Set<Position> positions = radar.playerPositions();
        Assertions.assertThat(positions.size()).isEqualTo(1);
    }

    @Test
    void playerPositionsLimitsXNegativeToPositive() {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        planetMap.generateObstacles();
        MarsRoverImpl marsRover = new MarsRoverImpl(49,0,Direction.NORTH, planetMap);
        RadarImpl radar = new RadarImpl(Position.of(-48,0,Direction.NORTH), planetMap);
        Set<Position> positions = radar.playerPositions();
        Assertions.assertThat(positions.size()).isEqualTo(1);
    }

    @Test
    void playerPositionsLimitsYNegativeToPositive() {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        planetMap.generateObstacles();
        MarsRoverImpl marsRover = new MarsRoverImpl(48,0,Direction.NORTH, planetMap);
        RadarImpl radar = new RadarImpl(Position.of(-47,0,Direction.NORTH), planetMap);
        Set<Position> positions = radar.playerPositions();
        Assertions.assertThat(positions.size()).isEqualTo(1);
    }
}
