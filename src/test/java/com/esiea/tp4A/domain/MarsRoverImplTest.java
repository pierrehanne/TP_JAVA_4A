package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarsRoverImplTest {

    @Test
    void initial() {
        MarsRoverImpl marsRoverImpl = new MarsRoverImpl(0, 0, Direction.NORTH);
        PlanetMapImpl planetMap = new PlanetMapImpl();
        Position position = marsRoverImpl.move("", planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(0, 0, Direction.NORTH));
    }

    @Test
    void right_direction(){
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH);
        PlanetMapImpl planetMap = new PlanetMapImpl();
        Position newPosition = marsRover.move("R", planetMap);
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.EAST));
    }

    @Test
    void allRightDirection() {
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH);
        PlanetMapImpl planetMap = new PlanetMapImpl();
        for(int i = 0; i<4; i++) {
            marsRover = new MarsRoverImpl(0, 0,Direction.values()[i]);
            Position position = marsRover.move("R", planetMap);
            if(i==3)
                Assertions.assertThat(position).isEqualTo(Position.of(0, 0, Direction.values()[0]));
            else
                Assertions.assertThat(position).isEqualTo(Position.of(0, 0, Direction.values()[i + 1]));
        }
    }

    @Test
    void allLeftDirection() {
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH);
        PlanetMapImpl planetMap = new PlanetMapImpl();
        for(int i = 0; i<4; i++) {
            marsRover = new MarsRoverImpl(0, 0,Direction.values()[i]);
            Position position = marsRover.move("L", planetMap);
            if(i==0)
                Assertions.assertThat(position).isEqualTo(Position.of(0, 0, Direction.values()[3]));
            else
                Assertions.assertThat(position).isEqualTo(Position.of(0, 0, Direction.values()[i - 1]));
        }
    }

    @Test
    void backward_direction()
    {
        MarsRover marsRover = new MarsRoverImpl(0,0,Direction.NORTH);
        PlanetMapImpl planetMap = new PlanetMapImpl();
        Position position = marsRover.move("B", planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(0,-1,Direction.NORTH));
    }

    @Test
    void forward_direction()
    {
        MarsRover marsRover = new MarsRoverImpl(0,0,Direction.NORTH);
        PlanetMapImpl planetMap = new PlanetMapImpl();
        Position position = marsRover.move("F", planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(0,1,Direction.NORTH));
    }

    @Test
    void limitNorth() {
        MarsRover marsRover = new MarsRoverImpl(0,50,Direction.NORTH);
        PlanetMapImpl planetMap = new PlanetMapImpl();
        Position position = marsRover.move("F", planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(0,-49,Direction.NORTH));
    }

    @Test
    void limitSouth() {
        MarsRover marsRover = new MarsRoverImpl(0,-49,Direction.SOUTH);
        PlanetMapImpl planetMap = new PlanetMapImpl();
        Position position = marsRover.move("F", planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(0,50,Direction.SOUTH));
    }

    @Test
    void limitWest() {
        MarsRover marsRover = new MarsRoverImpl(50,0,Direction.WEST);
        PlanetMapImpl planetMap = new PlanetMapImpl();
        Position position = marsRover.move("F", planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(-49,0,Direction.WEST));
    }

    @Test
    void limitEast() {
        MarsRover marsRover = new MarsRoverImpl(-49,0,Direction.EAST);
        PlanetMapImpl planetMap = new PlanetMapImpl();
        Position position = marsRover.move("F", planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(50,0,Direction.EAST));
    }

    @Test
    void obstacleDetectionForward(){
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH);
        PlanetMapImpl planetMap = new PlanetMapImpl();
        planetMap.generateObstacles();
        Position oldPosition = marsRover.initPosNextToObstacle("NORTH", planetMap);
        marsRover.move("F", planetMap);
        Assertions.assertThat(marsRover.getCurrentPosition()).isEqualTo(oldPosition);
    }

    @Test
    void obstacleDetectionBackward(){
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH);
        PlanetMapImpl planetMap = new PlanetMapImpl();
        planetMap.generateObstacles();
        Position oldPosition = marsRover.initPosNextToObstacle("SOUTH", planetMap);
        marsRover.move("B", planetMap);
        Assertions.assertThat(marsRover.getCurrentPosition()).isEqualTo(oldPosition);
    }

    @Test
    void initPositionNextToObstacleNorth(){
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH);
        PlanetMapImpl planetMap = new PlanetMapImpl();
        planetMap.generateObstacles();
        Position position = marsRover.initPosNextToObstacle("NORTH", planetMap);
        Assertions.assertThat(planetMap.getInfo(position.getX(), position.getY()+1)==1);
    }

    @Test
    void initPositionNextToObstacleSouth(){
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH);
        PlanetMapImpl planetMap = new PlanetMapImpl();
        planetMap.generateObstacles();
        Position position = marsRover.initPosNextToObstacle("SOUTH", planetMap);
        Assertions.assertThat(planetMap.getInfo(position.getX(), position.getY()-1)==1);
    }

    @Test
    void playerDetectionNORTH(){
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH);
        MarsRoverImpl marsRoverPlayer = new MarsRoverImpl(0,1,Direction.NORTH);
        boolean playerDetection = marsRover.playerDetection("F", marsRoverPlayer, marsRover.getCurrentPosition());
        Assertions.assertThat(playerDetection);
    }

    @Test
    void playerDetectionSOUTH(){
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH);
        MarsRoverImpl marsRoverPlayer = new MarsRoverImpl(0,-1,Direction.NORTH);
        boolean playerDetection = marsRover.playerDetection("B", marsRoverPlayer, marsRover.getCurrentPosition());
        Assertions.assertThat(playerDetection);
    }

}
