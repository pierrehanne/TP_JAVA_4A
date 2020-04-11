package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RadarImplTest {

    @Test
    void InitialiseRadarPosition() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        RadarImpl radar = new RadarImpl(marsRover, planetMap);
        Assertions.assertThat(radar.getCurrentPosition()).isEqualTo(Position.of(0,0,Direction.NORTH));
    }

    @Test
    void InitialiseRadarMap() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        RadarImpl radar = new RadarImpl(marsRover, planetMap);
        Assertions.assertThat(radar.getPlanetMap()).isEqualTo(planetMap);
    }

    /*@Test
    void DisplayRadarFromCenter() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        planetMap.generateObstacles();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        RadarImpl radar = new RadarImpl(marsRover, planetMap);
        radar.displayRadar();
        Assertions.assertThat(radar.getPlanetMap()).isEqualTo(planetMap);
    }*/

    @Test
    void obstaclePositions() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        planetMap.generateObstacles();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        RadarImpl radar = new RadarImpl(marsRover, planetMap);
        Set<Position> positions = radar.obstaclePositions(planetMap);
        for(Position position : positions) {
            Assertions.assertThat(position.getY()).isLessThan(15);
            Assertions.assertThat(position.getX()).isLessThan(15);
            Assertions.assertThat(position.getY()).isGreaterThan(-16);
            Assertions.assertThat(position.getX()).isGreaterThan(-16);
        }
    }

    @Test
    void obstaclesPositions() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(-48,0,Direction.NORTH, planetMap);
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(45, 0, Direction.NORTH));
        marsRover.updateMap(new PlanetMapImpl(obstaclePositions));
        RadarImpl radar = new RadarImpl(marsRover, marsRover.getPlanetMap());
        Set<Position> positions = radar.obstaclePositions(marsRover.getPlanetMap());
        Assertions.assertThat(positions.size()).isEqualTo(1);
    }

    @Test
    void obstaclesPositionsLimitsXPositiveToNegative() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        Set<Position> obstaclePositions = new HashSet<>();
        MarsRoverImpl marsRover = new MarsRoverImpl(45,0,Direction.NORTH, planetMap);
        obstaclePositions.add(Position.of(-48, 0, Direction.NORTH));
        marsRover.updateMap(new PlanetMapImpl(obstaclePositions));
        RadarImpl radar = new RadarImpl(marsRover, marsRover.getPlanetMap());
        Set<Position> positions = radar.obstaclePositions(marsRover.getPlanetMap());
        Assertions.assertThat(positions.size()).isEqualTo(1);
    }

    @Test
    void obstaclesPositionsLimitsYPositiveToNegative() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        planetMap.generateObstacles();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,49,Direction.NORTH, planetMap);
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(0, -47, Direction.NORTH));
        marsRover.updateMap(new PlanetMapImpl(obstaclePositions));
        RadarImpl radar = new RadarImpl(marsRover, planetMap);
        Set<Position> positions = radar.obstaclePositions(marsRover.getPlanetMap());
        Assertions.assertThat(positions.size()).isEqualTo(1);
    }

    @Test
    void obstaclesPositionsLimitsXNegativeToPositive() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        //planetMap.generateObstacles();
        MarsRoverImpl marsRover = new MarsRoverImpl(-48,0,Direction.NORTH, planetMap);
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(49, 0, Direction.NORTH));
        marsRover.updateMap(new PlanetMapImpl(obstaclePositions));
        RadarImpl radar = new RadarImpl(marsRover, planetMap);
        Set<Position> positions = radar.obstaclePositions(marsRover.getPlanetMap());
        Assertions.assertThat(positions.size()).isEqualTo(1);
    }

    @Test
    void obstaclesPositionsLimitsYNegativeToPositive() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        planetMap.generateObstacles();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,-47,Direction.NORTH, planetMap);
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(0, 48, Direction.NORTH));
        marsRover.updateMap(new PlanetMapImpl(obstaclePositions));
        RadarImpl radar = new RadarImpl(marsRover, planetMap);
        Set<Position> positions = radar.obstaclePositions(marsRover.getPlanetMap());
        Assertions.assertThat(positions.size()).isEqualTo(1);
    }
}

