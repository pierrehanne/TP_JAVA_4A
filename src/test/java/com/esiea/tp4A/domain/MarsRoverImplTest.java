package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarsRoverImplTest {

    @Test
    void initial() {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRoverImpl marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, planetMap);
        Position position = marsRover.move("");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(0, 0, Direction.NORTH));
    }

    @Test
    void right_direction(){
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, planetMap);
        Position newPosition = marsRover.move("R");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.EAST));
    }

    @Test
    void allRightDirection() {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, planetMap);
        for(int i = 0; i<4; i++) {
            marsRover = new MarsRoverImpl(0, 0,Direction.values()[i], planetMap);
            Position position = marsRover.move("R");
            marsRover.updateMap(planetMap);
            if(i==3)
                Assertions.assertThat(position).isEqualTo(Position.of(0, 0, Direction.values()[0]));
            else
                Assertions.assertThat(position).isEqualTo(Position.of(0, 0, Direction.values()[i + 1]));
        }
    }

    @Test
    void allLeftDirection() {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, planetMap);
        for(int i = 0; i<4; i++) {
            marsRover = new MarsRoverImpl(0, 0,Direction.values()[i], planetMap);
            Position position = marsRover.move("L");
            marsRover.updateMap(planetMap);
            if(i==0)
                Assertions.assertThat(position).isEqualTo(Position.of(0, 0, Direction.values()[3]));
            else
                Assertions.assertThat(position).isEqualTo(Position.of(0, 0, Direction.values()[i - 1]));
        }
    }

    @Test
    void backward_direction()
    {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRover marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        Position position = marsRover.move("B");
        marsRover.updateMap(planetMap);
        marsRover.updateMap(planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(0,-1,Direction.NORTH));
    }

    @Test
    void forward_direction()
    {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRover marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        Position position = marsRover.move("F");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(0,1,Direction.NORTH));
    }

    @Test
    void limitNorth() {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRover marsRover = new MarsRoverImpl(0,50,Direction.NORTH, planetMap);
        Position position = marsRover.move("F");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(0,-49,Direction.NORTH));
    }

    @Test
    void limitSouth() {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRover marsRover = new MarsRoverImpl(0,-49,Direction.SOUTH, planetMap);
        Position position = marsRover.move("F");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(0,50,Direction.SOUTH));
    }

    @Test
    void limitWest() {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRover marsRover = new MarsRoverImpl(50,0,Direction.WEST, planetMap);
        Position position = marsRover.move("F");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(-49,0,Direction.WEST));
    }

    @Test
    void limitEast() {
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRover marsRover = new MarsRoverImpl(-49,0,Direction.EAST, planetMap);
        Position position = marsRover.move("F");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(50,0,Direction.EAST));
    }

    @Test
    void obstacleDetectionForward(){
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        planetMap.generateObstacles();
        Position oldPosition = marsRover.initPosNextToObstacle("NORTH", planetMap);
        marsRover.move("F");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(marsRover.getCurrentPosition()).isEqualTo(oldPosition);
    }

    @Test
    void obstacleDetectionBackward(){
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        planetMap.generateObstacles();
        Position oldPosition = marsRover.initPosNextToObstacle("SOUTH", planetMap);
        marsRover.move("B");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(marsRover.getCurrentPosition()).isEqualTo(oldPosition);
    }

    @Test
    void initPositionNextToObstacleNorth(){
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        planetMap.generateObstacles();
        Position position = marsRover.initPosNextToObstacle("NORTH", planetMap);
        Assertions.assertThat(planetMap.getInfo(position.getX(), position.getY()+1)==1);
    }

    @Test
    void initPositionNextToObstacleSouth(){
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        planetMap.generateObstacles();
        Position position = marsRover.initPosNextToObstacle("SOUTH", planetMap);
        Assertions.assertThat(planetMap.getInfo(position.getX(), position.getY()-1)==1);
    }

    @Test
    void playerDetectionNORTH(){
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        MarsRoverImpl marsRoverPlayer = new MarsRoverImpl(0,1,Direction.NORTH, planetMap);
        boolean playerDetection = marsRover.playerDetection("F", marsRoverPlayer, marsRover.getCurrentPosition());
        Assertions.assertThat(playerDetection);
    }

    @Test
    void playerDetectionSOUTH(){
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        MarsRoverImpl marsRoverPlayer = new MarsRoverImpl(0,-1,Direction.NORTH, planetMap);
        boolean playerDetection = marsRover.playerDetection("B", marsRoverPlayer, marsRover.getCurrentPosition());
        Assertions.assertThat(playerDetection);
    }

}
