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
    void getY() {
        Position position = Position.of(0,0,Direction.NORTH);
        int y = position.getY();
        Assertions.assertThat(y).isEqualTo(0);
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
                Assertions.assertThat(position).isEqualTo(Position.of(1, 0, Direction.values()[i]));
            if(i==2)
                Assertions.assertThat(position).isEqualTo(Position.of(0, -1, Direction.values()[i]));
            if(i==3)
                Assertions.assertThat(position).isEqualTo(Position.of(-1, 0, Direction.values()[i]));
        }
    }

    @Test
    void forwardLimit() {
        Position position = Position.of(0, 50,Direction.NORTH);
        position = position.forward1();
        Assertions.assertThat(position).isEqualTo(Position.of(0, -49, Direction.NORTH));
        position = Position.of(50, 0,Direction.EAST);
        position = position.forward1();
        Assertions.assertThat(position).isEqualTo(Position.of(-49, 0, Direction.EAST));
        position = Position.of(0, -49,Direction.SOUTH);
        position = position.forward1();
        Assertions.assertThat(position).isEqualTo(Position.of(0, 50, Direction.SOUTH));
        position = Position.of(-49, 0,Direction.WEST);
        position = position.forward1();
        Assertions.assertThat(position).isEqualTo(Position.of(50, 0, Direction.WEST));
    }

    @Test
    void forwardLimitNORTH() {
        Position position = Position.of(0, 50, Direction.NORTH);
        position = position.forward1();
        Assertions.assertThat(position).isEqualTo(Position.of(0, -49, Direction.NORTH));
    }

    @Test
    void forwardLimitEAST() {
        Position position = Position.of(50, 0,Direction.EAST);
        position = position.forward1();
        Assertions.assertThat(position).isEqualTo(Position.of(-49, 0, Direction.EAST));
    }

    @Test
    void forwardLimitSOUTH() {
        Position position = Position.of(0, -49,Direction.SOUTH);
        position = position.forward1();
        Assertions.assertThat(position).isEqualTo(Position.of(0, 50, Direction.SOUTH));
    }

    @Test
    void forwardLimitWEST() {
        Position position = Position.of(-49, 0,Direction.WEST);
        position = position.forward1();
        Assertions.assertThat(position).isEqualTo(Position.of(50, 0, Direction.WEST));
    }

    @Test
    void backward1() {
        for(int i = 0; i<4; i++) {
            Position position = Position.of(0, 0,Direction.values()[i]);
            position = position.backward1();
            if(i==0)
                Assertions.assertThat(position).isEqualTo(Position.of(0, -1, Direction.values()[i]));
            if(i==1)
                Assertions.assertThat(position).isEqualTo(Position.of(-1, 0, Direction.values()[i]));
            if(i==2)
                Assertions.assertThat(position).isEqualTo(Position.of(0, 1, Direction.values()[i]));
            if(i==3)
                Assertions.assertThat(position).isEqualTo(Position.of(1, 0, Direction.values()[i]));
        }
    }

    @Test
    void backwardLimit() {
        Position position = Position.of(0, 50,Direction.SOUTH);
        position = position.backward1();
        Assertions.assertThat(position).isEqualTo(Position.of(0, -49, Direction.SOUTH));
        position = Position.of(50, 0,Direction.WEST);
        position = position.backward1();
        Assertions.assertThat(position).isEqualTo(Position.of(-49, 0, Direction.WEST));
        position = Position.of(0, -49,Direction.NORTH);
        position = position.backward1();
        Assertions.assertThat(position).isEqualTo(Position.of(0, 50, Direction.NORTH));
        position = Position.of(-49, 0,Direction.EAST);
        position = position.backward1();
        Assertions.assertThat(position).isEqualTo(Position.of(50, 0, Direction.EAST));
    }

    @Test
    void testToString() {
        Position position = Position.of(0, 0,Direction.SOUTH);
        String test = position.toString();
        Assertions.assertThat(test).isEqualTo("FixedPosition{" + "x=" + 0 + ", y=" + 0 + ", direction=" + "SOUTH" + '}');
    }

    @Test
    void equals() {
        Position position1 = Position.of(0, 0,Direction.SOUTH);
        //Position position2 = Position.of(0, 0,Direction.NORTH);
        Assertions.assertThat(position1.equals(null)).isEqualTo(false);
        //Assertions.assertThat(position1.equals(position2)).isEqualTo(Position.of(0,0,Direction.NORTH));
    }
}
