package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void getX() {
        Position position = Position.of(0,0,Direction.NORTH);
        int x = position.getX();
        Assertions.assertThat(x).isEqualTo(0);
    }

    @Test
    void getXLimitXWest() {
        Position position = Position.of(50,0,Direction.WEST);
        int x = position.getX();
        Assertions.assertThat(x).isEqualTo(-50);
    }

    @Test
    void getXLimitXEast() {
        Position position = Position.of(-49,0,Direction.EAST);
        int x = position.getX();
        Assertions.assertThat(x).isEqualTo(51);
    }

    @Test
    void getY() {
        Position position = Position.of(0,0,Direction.NORTH);
        int y = position.getY();
        Assertions.assertThat(y).isEqualTo(0);
    }

    @Test
    void getXLimitYNorth() {
        Position position = Position.of(0,50,Direction.NORTH);
        int y = position.getY();
        Assertions.assertThat(y).isEqualTo(-50);
    }

    @Test
    void getXLimitYSouth() {
        Position position = Position.of(0,-49,Direction.SOUTH);
        int y = position.getY();
        Assertions.assertThat(y).isEqualTo(51);
    }

    @Test
    void getDirection() {
        Position position = Position.of(0,0,Direction.NORTH);
        Direction direction = position.getDirection();
        Assertions.assertThat(direction).isEqualTo(Direction.NORTH);
    }

    @Test
    void of() {
        Position position = Position.of(0,0,Direction.NORTH);
        Assertions.assertThat(position).isEqualTo(Position.of(0,0,Direction.NORTH));
    }

    @Test
    void forward1() {
        for(int i = 0; i<4; i++) {
            Position position = Position.of(0, 0,Direction.values()[i]);
            position = position.forward1();
            if(i==0)
                Assertions.assertThat(position).isEqualTo(Position.of(0, 1, Direction.values()[i]));
            if(i==1)
                Assertions.assertThat(position).isEqualTo(Position.of(-1, 0, Direction.values()[i]));
            if(i==2)
                Assertions.assertThat(position).isEqualTo(Position.of(0, -1, Direction.values()[i]));
            if(i==3)
                Assertions.assertThat(position).isEqualTo(Position.of(1, 0, Direction.values()[i]));
        }
    }

    @Test
    void backward1() {
        for(int i = 0; i<4; i++) {
            Position position = Position.of(0, 0,Direction.values()[i]);
            position = position.backward1();
            if(i==0)
                Assertions.assertThat(position).isEqualTo(Position.of(0, -1, Direction.values()[i]));
            if(i==1)
                Assertions.assertThat(position).isEqualTo(Position.of(1, 0, Direction.values()[i]));
            if(i==2)
                Assertions.assertThat(position).isEqualTo(Position.of(0, 1, Direction.values()[i]));
            if(i==3)
                Assertions.assertThat(position).isEqualTo(Position.of(-1, 0, Direction.values()[i]));
        }
    }
}
