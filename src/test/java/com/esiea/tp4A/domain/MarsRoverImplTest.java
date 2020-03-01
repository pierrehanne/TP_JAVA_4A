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
    void left_direction()
    {
        MarsRover marsRover = new MarsRoverImpl(0,0,Direction.NORTH);
        Position position = marsRover.move("L");
        Assertions.assertThat(position)
            .isEqualTo(Position.of(0,0,Direction.WEST));
    }

    @Test
    void south_direction()
    {
        MarsRover marsRover = new MarsRoverImpl(0,-1,Direction.NORTH);
        Position position = marsRover.move("S");
        Assertions.assertThat(position).isEqualTo(Position.of(0,-1,Direction.NORTH));
    }

    @Test
    void forward_direction()
    {
        MarsRover marsRover = new MarsRoverImpl(0,+1,Direction.NORTH);
        Position position = marsRover.move("F");
        Assertions.assertThat(position).isEqualTo(Position.of(0,+1,Direction.NORTH));
    }


}
