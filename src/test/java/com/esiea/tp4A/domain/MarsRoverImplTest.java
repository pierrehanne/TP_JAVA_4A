package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MarsRoverImplTest {
    @Test
    void initial() {
        MarsRoverImpl marsRoverImpl = new MarsRoverImpl(0, 0, Direction.NORTH);
        Position position = marsRoverImpl.move("");

        Assertions.assertThat(position).isEqualTo(Position.of(0, 0, Direction.NORTH));
    }
}
