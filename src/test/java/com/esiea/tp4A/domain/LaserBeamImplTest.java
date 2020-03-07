package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LaserBeamImplTest {

    @Test
    void ShootInitNORTH(){
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.NORTH);
        PlanetMapImpl planetMap = new PlanetMapImpl();
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(marsRover.getCurrentPosition().getX(), marsRover.getCurrentPosition().getY()+1, Direction.NORTH));
    }

    @Test
    void ShootInitSOUTH(){
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.SOUTH);
        PlanetMapImpl planetMap = new PlanetMapImpl();
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(marsRover.getCurrentPosition().getX(), marsRover.getCurrentPosition().getY()-1, Direction.SOUTH));
    }

    @Test
    void ShootInitWEST(){
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.WEST);
        PlanetMapImpl planetMap = new PlanetMapImpl();
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(marsRover.getCurrentPosition().getX()-1, marsRover.getCurrentPosition().getY(), Direction.WEST));
    }

    @Test
    void ShootInitEAST(){
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.EAST);
        PlanetMapImpl planetMap = new PlanetMapImpl();
        LaserBeamImpl laserBeam = marsRover.Shoot(planetMap);
        Assertions.assertThat(laserBeam.getCurrentPosition()).isEqualTo(Position.of(marsRover.getCurrentPosition().getX()+1, marsRover.getCurrentPosition().getY(), Direction.EAST));
    }

}
