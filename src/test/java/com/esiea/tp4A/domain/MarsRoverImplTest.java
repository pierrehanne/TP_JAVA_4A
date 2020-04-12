package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static com.esiea.tp4A.domain.Direction.EAST;

class MarsRoverImplTest {

    @Test
    void initial() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, planetMap);
        Position position = marsRover.move("");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(0, 0, Direction.NORTH));
    }

    @Test
    void isAlive() {
        MarsRoverImpl marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, new PlanetMapImpl(null));
        Assertions.assertThat(marsRover.isAlive()).isEqualTo(true);
    }

    @Test
    void right_direction(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, planetMap);
        Position newPosition = marsRover.move("r");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, EAST));
    }

    @Test
    void allRightDirection() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, planetMap);
        for(int i = 0; i<4; i++) {
            marsRover = new MarsRoverImpl(0, 0,Direction.values()[i], planetMap);
            Position position = marsRover.move("r");
            marsRover.updateMap(planetMap);
            if(i==3)
                Assertions.assertThat(position).isEqualTo(Position.of(0, 0, Direction.values()[0]));
            else
                Assertions.assertThat(position).isEqualTo(Position.of(0, 0, Direction.values()[i + 1]));
        }
    }

    @Test
    void allLeftDirection() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, planetMap);
        for(int i = 0; i<4; i++) {
            marsRover = new MarsRoverImpl(0, 0,Direction.values()[i], planetMap);
            Position position = marsRover.move("l");
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
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRover marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        Position position = marsRover.move("b");
        marsRover.updateMap(planetMap);
        marsRover.updateMap(planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(0,-1,Direction.NORTH));
    }

    @Test
    void forward_direction()
    {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRover marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        Position position = marsRover.move("f");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(0,1,Direction.NORTH));
    }

    @Test
    void unknown_command_should_be_ignored() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRover marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        Position position = marsRover.move("af");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(0,1,Direction.NORTH));
    }

    @Test
    void complex_moves_from_different_origin() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRover marsRover = new MarsRoverImpl(5,-3,Direction.WEST, planetMap);
        Position position = marsRover.move("lbblffr");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(7,-1,Direction.SOUTH));
    }

    @Test
    void multiple_commands() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRover marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        Position position = marsRover.move("lbblffr");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(2,-2,Direction.WEST));
    }

    @Test
    void limitNorth() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRover marsRover = new MarsRoverImpl(0,50,Direction.NORTH, planetMap);
        Position position = marsRover.move("f");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(0,-49,Direction.NORTH));
    }

    @Test
    void limitSouth() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRover marsRover = new MarsRoverImpl(0,-49,Direction.SOUTH, planetMap);
        Position position = marsRover.move("f");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(0,50,Direction.SOUTH));
    }

    @Test
    void limitWest() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRover marsRover = new MarsRoverImpl(-49,0,Direction.WEST, planetMap);
        Position position = marsRover.move("f");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(50,0,Direction.WEST));
    }

    @Test
    void limitEast() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRover marsRover = new MarsRoverImpl(50,0, EAST, planetMap);
        Position position = marsRover.move("f");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(position).isEqualTo(Position.of(-49,0, EAST));
    }

    @Test
    void obstacleDetectionForward(){
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(0, 1, Direction.NORTH));
        PlanetMapImpl planetMap = new PlanetMapImpl(obstaclePositions);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        Position oldPosition = marsRover.getCurrentPosition();
        marsRover.move("f");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(marsRover.getCurrentPosition()).isEqualTo(oldPosition);
    }

    @Test
    void obstacleDetectionBackward(){
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(0, -1, Direction.NORTH));
        PlanetMapImpl planetMap = new PlanetMapImpl(obstaclePositions);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        Position oldPosition = marsRover.getCurrentPosition();
        marsRover.move("b");
        marsRover.updateMap(planetMap);
        Assertions.assertThat(marsRover.getCurrentPosition()).isEqualTo(oldPosition);
    }

    @Test
    void moves_are_ignored_in_case_of_collision(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.SOUTH, planetMap);
        //Placement des obstacles tout autour du Rover
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(0, 1, Direction.NORTH));
        obstaclePositions.add(Position.of(0, -1, Direction.NORTH));
        obstaclePositions.add(Position.of(1, 0, Direction.NORTH));
        obstaclePositions.add(Position.of(-1, 0, Direction.NORTH));
        marsRover.updateMap(new PlanetMapImpl(obstaclePositions));
        Position expectedPos = Position.of(0,0, EAST);
        Position position = marsRover.move("bllfrb");
        Assertions.assertThat(marsRover.getCurrentPosition()).isEqualTo(expectedPos);
    }

    @Test
    void moves_ignored_test() {
        String command = "bllfrb";
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.NORTH, new PlanetMapImpl(null));
        marsRover.initialize(Position.of(4, 4, Direction.SOUTH));

        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(4, 5, Direction.NORTH));
        obstaclePositions.add(Position.of(5, 4, Direction.NORTH));

        marsRover.updateMap(new PlanetMapImpl(obstaclePositions));

        Position newPosition = marsRover.move(command);

        Assertions.assertThat(newPosition)
            .as("new position after receiving command '" + command + "'")
            .isEqualTo(Position.of(3, 4, EAST));
    }

    @Test
    void moves_ignored_test_correc(){
        String command = "bllfrb";
        MarsRoverImpl marsRover = new MarsRoverImpl(4,4,Direction.SOUTH, new PlanetMapImpl(null));
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(4, 5, Direction.NORTH));
        obstaclePositions.add(Position.of(5, 4, Direction.NORTH));
        marsRover.updateMap(new PlanetMapImpl(obstaclePositions));

        Position newPosition = marsRover.move(command);

        Assertions.assertThat(newPosition)
            .as("new position after receiving command '" + command + "'")
            .isEqualTo(Position.of(3, 4, EAST));
    }

    @Test
    void moveAndShootTest(){
        String command = "sff";
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.NORTH, new PlanetMapImpl(null));
        marsRover.initialize(Position.of(0, 0, Direction.NORTH));

        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(0, 2, Direction.NORTH));

        marsRover.updateMap(new PlanetMapImpl(obstaclePositions));

        Position newPosition = marsRover.move(command);

        Assertions.assertThat(newPosition)
            .as("new position after receiving command '" + command + "'")
            .isEqualTo(Position.of(0, 2, Direction.NORTH));
    }

    @Test
    void playerDetectionForward(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        MarsRoverImpl marsRoverPlayer = new MarsRoverImpl(0,1,Direction.NORTH, planetMap);
        boolean playerDetection = marsRover.playerDetection("f", marsRoverPlayer, marsRover.getCurrentPosition());
        Assertions.assertThat(playerDetection).isEqualTo(true);
    }

    @Test
    void playerDetectionBackward(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        MarsRoverImpl marsRoverPlayer = new MarsRoverImpl(0,-1,Direction.NORTH, planetMap);
        boolean playerDetection = marsRover.playerDetection("b", marsRoverPlayer, marsRover.getCurrentPosition());
        Assertions.assertThat(playerDetection).isEqualTo(true);
    }

    @Test
    void noPlayerDetectedSameX(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        MarsRoverImpl marsRoverPlayer = new MarsRoverImpl(0,5,Direction.NORTH, planetMap);
        boolean playerDetection = marsRover.playerDetection("f", marsRoverPlayer, marsRover.getCurrentPosition());
        Assertions.assertThat(playerDetection).isEqualTo(false);
    }

    @Test
    void noPlayerDetectedSameY(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        MarsRoverImpl marsRoverPlayer = new MarsRoverImpl(5,0,Direction.NORTH, planetMap);
        boolean playerDetection = marsRover.playerDetection("f", marsRoverPlayer, marsRover.getCurrentPosition());
        Assertions.assertThat(playerDetection).isEqualTo(false);
    }

    @Test
    void noPlayerDetectedDifferentXandY(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        MarsRoverImpl marsRoverPlayer = new MarsRoverImpl(5,5,Direction.NORTH, planetMap);
        boolean playerDetection = marsRover.playerDetection("f", marsRoverPlayer, marsRover.getCurrentPosition());
        Assertions.assertThat(playerDetection).isEqualTo(false);
    }

    @Test
    void moves_on_spherical_map1() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(-49,49, EAST, planetMap);
        Position position = marsRover.move("b");
        Assertions.assertThat(position).isEqualTo(Position.of(50,49, EAST));
    }

    @Test
    void moves_on_spherical_map2() {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(49,49,Direction.WEST, planetMap);
        Position position = marsRover.move("bb");
        Assertions.assertThat(position).isEqualTo(Position.of(-49,49,Direction.WEST));
    }

}
