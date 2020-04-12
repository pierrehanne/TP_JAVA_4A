package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class LaserBeamImplTest {

    @Test
    void ShootInitNORTH(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.NORTH, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(marsRover.getCurrentPosition().getX(), marsRover.getCurrentPosition().getY(), Direction.NORTH));
    }

    @Test
    void ShootInitSOUTH(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.SOUTH, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(marsRover.getCurrentPosition().getX(), marsRover.getCurrentPosition().getY(), Direction.SOUTH));
    }

    @Test
    void ShootInitWEST(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.WEST, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(marsRover.getCurrentPosition().getX(), marsRover.getCurrentPosition().getY(), Direction.WEST));
    }

    @Test
    void ShootInitEAST(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.EAST, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(marsRover.getCurrentPosition().getX(), marsRover.getCurrentPosition().getY(), Direction.EAST));
    }

    @Test
    void MoveLaserNORTH(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.NORTH, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Position oldPosition = laserBeam.getCurrentPosition();
        laserBeam.move(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(oldPosition.getX(), oldPosition.getY()+1, Direction.NORTH));
    }

    @Test
    void MoveLaserSOUTH(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.SOUTH, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Position oldPosition = laserBeam.getCurrentPosition();
        laserBeam.move(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(oldPosition.getX(), oldPosition.getY()-1, Direction.SOUTH));
    }

    @Test
    void MoveLaserWEST(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.WEST, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Position oldPosition = laserBeam.getCurrentPosition();
        laserBeam.move(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(oldPosition.getX()-1, oldPosition.getY(), Direction.WEST));
    }

    @Test
    void MoveLaserEAST(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.EAST, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Position oldPosition = laserBeam.getCurrentPosition();
        laserBeam.move(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(oldPosition.getX()+1, oldPosition.getY(), Direction.EAST));
    }

    @Test
    void destroyedLaserMove(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        LaserBeamImpl laserBeam = new LaserBeamImpl(Position.of(0,0, Direction.NORTH),30);
        laserBeam.setDestroyed(true);
        Position position = laserBeam.move(planetMap);
        Assertions.assertThat(position).isEqualTo(null);
    }

    @Test
    void ObstacleCollisionCheckNORTH(){
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(0, 1, Direction.NORTH));

        PlanetMapImpl planetMap = new PlanetMapImpl(obstaclePositions);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);

        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);

        System.out.println("Position : " + laserBeam.getCurrentPosition().getX() + " " + laserBeam.getCurrentPosition().getY());

        laserBeam.obstaclesDetection(planetMap);
        Position position = laserBeam.move(planetMap);
        Assertions.assertThat(laserBeam.getDestroyed() && planetMap.getInfo(position.getX(), position.getY())==0).isEqualTo(true);
    }

    @Test
    void ObstacleCollisionCheckSOUTH(){
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(0, -1, Direction.NORTH));
        PlanetMapImpl planetMap = new PlanetMapImpl(obstaclePositions);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.SOUTH, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Position position = laserBeam.move(planetMap);
        Assertions.assertThat(laserBeam.getDestroyed() && planetMap.getInfo(position.getX(), position.getY())==0).isEqualTo(true);
    }

    @Test
    void ObstacleCollisionCheckWEST(){
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(-1, 0, Direction.NORTH));
        PlanetMapImpl planetMap = new PlanetMapImpl(obstaclePositions);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.WEST, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Position position = laserBeam.move(planetMap);
        Assertions.assertThat(laserBeam.getDestroyed() && planetMap.getInfo(position.getX(), position.getY())==0).isEqualTo(true);
    }

    @Test
    void ObstacleCollisionCheckEAST(){
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(1, 0, Direction.NORTH));
        PlanetMapImpl planetMap = new PlanetMapImpl(obstaclePositions);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.EAST, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Position position = laserBeam.move(planetMap);
        Assertions.assertThat(laserBeam.getDestroyed() && planetMap.getInfo(position.getX(), position.getY())==0).isEqualTo(true);
    }

    @Test
    void initRangeTest(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.SOUTH, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        int range = laserBeam.getRange();
        boolean initRange = false;
        if(range == 30 || range == 60 || range == 10000){ initRange = true; }
        Assertions.assertThat(initRange).isEqualTo(true);
    }

    @Test
    void rangeReached(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        marsRover.move("r");
        marsRover.move("f"); //on se décale pour ne pas se faire toucher par notre propre missile
        int range = laserBeam.getRange();
        System.out.println("Range : " + range);
        //on se déplace jusqu'à la portée maximale du laser puis on vérifie qu'il a été détruit
        for(int i=1 ; i<=range+1 ; i++){
            laserBeam.move(planetMap);
        }
        Assertions.assertThat(laserBeam.getDestroyed()).isEqualTo(true);
    }

    @Test
    void obstacleOutOfRange(){
        String command = "sff";
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(3, 1, Direction.EAST));
        PlanetMapImpl planetMap = new PlanetMapImpl(obstaclePositions);
        MarsRoverImpl marsRover = new MarsRoverImpl(1,1, Direction.EAST, planetMap);
        marsRover.configureLaserRange(1);
        marsRover.move(command);
        Assertions.assertThat(marsRover.getCurrentPosition()).isEqualTo(Position.of(2,1,Direction.EAST));
    }

    @Test
    void obstacleCollisionCheckTest(){
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(0, 0, Direction.NORTH));
        PlanetMapImpl planetMap = new PlanetMapImpl(obstaclePositions);
        LaserBeamImpl laserBeam = new LaserBeamImpl(Position.of(0,0, Direction.NORTH),30); //on place directement le laser sur l'obstacle
        laserBeam.obstaclesDetection(planetMap);
        Assertions.assertThat(laserBeam.getDestroyed()).isEqualTo(true);
    }

    @Test
    void obstaclesDetectionNotDetected(){
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(0, 0, Direction.NORTH));
        PlanetMapImpl planetMap = new PlanetMapImpl(obstaclePositions);

        LaserBeamImpl laserBeam = new LaserBeamImpl(Position.of(0,1, Direction.NORTH),30); //on place le laser à côté de l'obstacle
        laserBeam.obstaclesDetection(planetMap);
        Assertions.assertThat(laserBeam.getDestroyed()).isEqualTo(false);

        laserBeam = new LaserBeamImpl(Position.of(1,0, Direction.NORTH),30);
        laserBeam.obstaclesDetection(planetMap);
        Assertions.assertThat(laserBeam.getDestroyed()).isEqualTo(false);

        laserBeam = new LaserBeamImpl(Position.of(1,1, Direction.NORTH),30);
        laserBeam.obstaclesDetection(planetMap);
        Assertions.assertThat(laserBeam.getDestroyed()).isEqualTo(false);
    }

    @Test
    void rangeCheckTest(){
        LaserBeamImpl laserBeam = new LaserBeamImpl(Position.of(0,0, Direction.NORTH),30);
        laserBeam.setDistanceTravelled(10001); //on met la distance parcourue à une valeur plus grande que n'importe quelle portée
        laserBeam.rangeCheck();
        Assertions.assertThat(laserBeam.getDestroyed()).isEqualTo(true);
    }
}
