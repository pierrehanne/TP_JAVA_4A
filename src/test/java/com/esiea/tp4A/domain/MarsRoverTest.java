package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarsRoverTest {

    @Test
    public void initializeTest(){
        MarsRover marsRover = new MarsRoverImpl(0,0, Direction.NORTH, new PlanetMapImpl(null));
        Assertions.assertThat(marsRover.initialize(Position.of(0,0, Direction.NORTH))).isEqualTo(marsRover);
    }

    @Test
    public void updateMapTest(){
        MarsRover marsRover = new MarsRoverImpl(0,0, Direction.NORTH, new PlanetMapImpl(null));
        Assertions.assertThat(marsRover.updateMap(new PlanetMapImpl(null))).isEqualTo(marsRover);
    }

    @Test
    public void configureLaserRangeTest(){
        MarsRover marsRover = new MarsRoverImpl(0,0, Direction.NORTH, new PlanetMapImpl(null));
        Assertions.assertThat(marsRover.configureLaserRange(30)).isEqualTo(marsRover);
    }

    @Test
    public void moveTest(){
        MarsRover marsRover = new MarsRoverImpl(0,0, Direction.NORTH, new PlanetMapImpl(null));
        Assertions.assertThat(marsRover.move("x")).isEqualTo(Position.of(0,0, Direction.NORTH));
    }

}
