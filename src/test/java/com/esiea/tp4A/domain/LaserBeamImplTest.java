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
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(marsRover.getCurrentPosition().getX(), marsRover.getCurrentPosition().getY()+1, Direction.NORTH));
    }

    @Test
    void ShootInitSOUTH(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.SOUTH, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(marsRover.getCurrentPosition().getX(), marsRover.getCurrentPosition().getY()-1, Direction.SOUTH));
    }

    @Test
    void ShootInitWEST(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.WEST, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(marsRover.getCurrentPosition().getX()-1, marsRover.getCurrentPosition().getY(), Direction.WEST));
    }

    @Test
    void ShootInitEAST(){
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.EAST, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(marsRover.getCurrentPosition().getX()+1, marsRover.getCurrentPosition().getY(), Direction.EAST));
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
    void ObstacleCollisionCheckNORTH(){
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(0, 1, Direction.NORTH));

        PlanetMapImpl planetMap = new PlanetMapImpl(obstaclePositions);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.NORTH, planetMap);
        /*
        planetMap.generateObstacles();
        Position position1 = marsRover.initPosNextToObstacle("NORTH", planetMap);
        */
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);

        System.out.println("Position : " + laserBeam.getCurrentPosition().getX() + " " + laserBeam.getCurrentPosition().getY());

        laserBeam.obstacleCollisionCheck(planetMap);
        Position position = laserBeam.getCurrentPosition();
        Assertions.assertThat(laserBeam.getDestroyed() && planetMap.getInfo(position.getX(), position.getY())==0).isEqualTo(true);
    }

    @Test
    void ObstacleCollisionCheckSOUTH(){
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(0, -1, Direction.NORTH));
        PlanetMapImpl planetMap = new PlanetMapImpl(obstaclePositions);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.SOUTH, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Position position = laserBeam.getCurrentPosition();
        Assertions.assertThat(laserBeam.getDestroyed() && planetMap.getInfo(position.getX(), position.getY())==0).isEqualTo(true);
    }

    @Test
    void ObstacleCollisionCheckWEST(){
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(-1, 0, Direction.NORTH));
        PlanetMapImpl planetMap = new PlanetMapImpl(obstaclePositions);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.WEST, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Position position = laserBeam.getCurrentPosition();
        Assertions.assertThat(laserBeam.getDestroyed() && planetMap.getInfo(position.getX(), position.getY())==0).isEqualTo(true);
    }

    @Test
    void ObstacleCollisionCheckEAST(){
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(1, 0, Direction.NORTH));
        PlanetMapImpl planetMap = new PlanetMapImpl(obstaclePositions);
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0,Direction.EAST, planetMap);
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Position position = laserBeam.getCurrentPosition();
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
        for(int i=1 ; i<range+1 ; i++){
            //System.out.println("Position map:" + laserBeam.getCurrentPosition().getX() + "," + laserBeam.getCurrentPosition().getY());
            laserBeam.move(planetMap);
        }
        Assertions.assertThat(laserBeam.getDestroyed()).isEqualTo(true);
    }

    /*
    //si le laser rencontre un obstacle, ces deux éléments sont détruits
    public void obstacleCollisionCheck(PlanetMapImpl planetMap){
        if(planetMap.getInfo(currentPosition.getX(), currentPosition.getY())==1){
            planetMap.MajMap(currentPosition.getX(), currentPosition.getY(), 0);
            destroyed = true;
        }
    }
    */

    @Test
    void obstacleCollisionCheckTest(){
        Set<Position> obstaclePositions = new HashSet<>();
        obstaclePositions.add(Position.of(0, 0, Direction.NORTH));
        PlanetMapImpl planetMap = new PlanetMapImpl(obstaclePositions);
        LaserBeamImpl laserBeam = new LaserBeamImpl(0,0, Direction.NORTH); //on place directement le laser sur l'obstacle
        laserBeam.obstacleCollisionCheck(planetMap);
        Assertions.assertThat(laserBeam.getDestroyed()).isEqualTo(true);
    }

    @Test
    void rangeCheckTest(){
        LaserBeamImpl laserBeam = new LaserBeamImpl(0,0, Direction.NORTH);
        //System.out.println("Détruit :" + laserBeam.getDestroyed());
        laserBeam.setDistanceTravelled(10001); //on met la distance parcourue à une valeur plus grande que n'importe quelle portée
        laserBeam.rangeCheck();
        Assertions.assertThat(laserBeam.getDestroyed()).isEqualTo(true);
    }
}
