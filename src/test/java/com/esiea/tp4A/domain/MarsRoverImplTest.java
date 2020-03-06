package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarsRoverImplTest {

    @Test
    void initial() {
        MarsRoverImpl marsRoverImpl = new MarsRoverImpl(0, 0, Direction.NORTH);
        Position position = marsRoverImpl.move("");
        Assertions.assertThat(position).isEqualTo(Position.of(0, 0, Direction.NORTH));
    }

    @Test
    void right_direction(){
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH);
        Position newPosition = marsRover.move("R");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.EAST));
    }

    @Test
    void allRightDirection() {
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH);
        for(int i = 0; i<4; i++) {
            marsRover = new MarsRoverImpl(0, 0,Direction.values()[i]);
            Position position = marsRover.move("R");
            if(i==3)
                Assertions.assertThat(position).isEqualTo(Position.of(0, 0, Direction.values()[0]));
            else
                Assertions.assertThat(position).isEqualTo(Position.of(0, 0, Direction.values()[i + 1]));
        }
    }

    @Test
    void allLeftDirection() {
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH);
        for(int i = 0; i<4; i++) {
            marsRover = new MarsRoverImpl(0, 0,Direction.values()[i]);
            Position position = marsRover.move("L");
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
        Position position = marsRover.move("B");
        Assertions.assertThat(position).isEqualTo(Position.of(0,-1,Direction.NORTH));
    }

    @Test
    void forward_direction()
    {
        MarsRover marsRover = new MarsRoverImpl(0,0,Direction.NORTH);
        Position position = marsRover.move("F");
        Assertions.assertThat(position).isEqualTo(Position.of(0,1,Direction.NORTH));
    }

    @Test
    void limitNorth() {
        MarsRover marsRover = new MarsRoverImpl(0,50,Direction.NORTH);
        Position position = marsRover.move("F");
        Assertions.assertThat(position).isEqualTo(Position.of(0,-49,Direction.NORTH));
    }

    @Test
    void limitSouth() {
        MarsRover marsRover = new MarsRoverImpl(0,-49,Direction.SOUTH);
        Position position = marsRover.move("F");
        Assertions.assertThat(position).isEqualTo(Position.of(0,50,Direction.SOUTH));
    }

    @Test
    void limitWest() {
        MarsRover marsRover = new MarsRoverImpl(50,0,Direction.WEST);
        Position position = marsRover.move("F");
        Assertions.assertThat(position).isEqualTo(Position.of(-49,0,Direction.WEST));
    }

    @Test
    void limitEast() {
        MarsRover marsRover = new MarsRoverImpl(-49,0,Direction.EAST);
        Position position = marsRover.move("F");
        Assertions.assertThat(position).isEqualTo(Position.of(50,0,Direction.EAST));
    }

    @Test
    void obstacleDetectionForward(){
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH);
        Position oldPosition = marsRover.initPosNextToObstacle("NORTH");
        marsRover.move("F");
        Assertions.assertThat(marsRover.getCurrentPosition()).isEqualTo(oldPosition);
    }

    @Test
    void obstacleDetectionBackward(){
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH);
        Position oldPosition = marsRover.initPosNextToObstacle("SOUTH");
        marsRover.move("B");
        Assertions.assertThat(marsRover.getCurrentPosition()).isEqualTo(oldPosition);
    }

    @Test
    void initPositionNextToObstacleNorth(){
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH);
        Position position = marsRover.initPosNextToObstacle("NORTH");
        Assertions.assertThat(marsRover.getPlanetMap().getInfo(position.getX(), position.getY()+1)==1);
    }

    @Test
    void initPositionNextToObstacleSouth(){
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH);
        Position position = marsRover.initPosNextToObstacle("SOUTH");
        Assertions.assertThat(marsRover.getPlanetMap().getInfo(position.getX(), position.getY()-1)==1);
    }

}
