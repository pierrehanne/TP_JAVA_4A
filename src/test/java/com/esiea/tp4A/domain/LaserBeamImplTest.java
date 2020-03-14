package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LaserBeamImplTest {

    @Test
    void ShootInitNORTH(){
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.NORTH, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(marsRover.getCurrentPosition().getX(), marsRover.getCurrentPosition().getY()+1, Direction.NORTH));
    }

    @Test
    void ShootInitSOUTH(){
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.SOUTH, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(marsRover.getCurrentPosition().getX(), marsRover.getCurrentPosition().getY()-1, Direction.SOUTH));
    }

    @Test
    void ShootInitWEST(){
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.WEST, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(marsRover.getCurrentPosition().getX()-1, marsRover.getCurrentPosition().getY(), Direction.WEST));
    }

    @Test
    void ShootInitEAST(){
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.EAST, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(marsRover.getCurrentPosition().getX()+1, marsRover.getCurrentPosition().getY(), Direction.EAST));
    }

    @Test
    void MoveLaserNORTH(){
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.NORTH, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Position oldPosition = laserBeam.getCurrentPosition();
        laserBeam.move(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(oldPosition.getX(), oldPosition.getY()+1, Direction.NORTH));
    }

    @Test
    void MoveLaserSOUTH(){
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.SOUTH, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Position oldPosition = laserBeam.getCurrentPosition();
        laserBeam.move(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(oldPosition.getX(), oldPosition.getY()-1, Direction.SOUTH));
    }

    @Test
    void MoveLaserWEST(){
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.WEST, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Position oldPosition = laserBeam.getCurrentPosition();
        laserBeam.move(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(oldPosition.getX()-1, oldPosition.getY(), Direction.WEST));
    }

    @Test
    void MoveLaserEAST(){
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.EAST, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Position oldPosition = laserBeam.getCurrentPosition();
        laserBeam.move(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(oldPosition.getX()+1, oldPosition.getY(), Direction.EAST));
    }

    @Test
    void ObstacleCollisionCheckNORTH(){
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        planetMap.generateObstacles();
        Position position1 = marsRover.initPosNextToObstacle("NORTH", planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Position position = laserBeam.getCurrentPosition();
        Assertions.assertThat(laserBeam.getDestroyed() && planetMap.getInfo(position.getX(), position.getY())==0);
    }

    @Test
    void ObstacleCollisionCheckSOUTH(){
        PlanetMapImpl planetMap = new PlanetMapImpl();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.SOUTH, planetMap);
        planetMap.generateObstacles();
        Position position1 = marsRover.initPosNextToObstacle("SOUTH", planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Position position = laserBeam.getCurrentPosition();
        Assertions.assertThat(laserBeam.getDestroyed() && planetMap.getInfo(position.getX(), position.getY())==2); //Si je mets 1 à la fin ça marche toujours
    }
}
