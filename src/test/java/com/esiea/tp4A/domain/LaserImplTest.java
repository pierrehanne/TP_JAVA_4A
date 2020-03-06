package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.esiea.tp4A.domain.LaserRange.COURTE;

class LaserImplTest {
    @Test
    void init_laser() {
        LaserImpl laserImpl = new LaserImpl(COURTE);
        Assertions.assertThat(laserImpl).isEqualToComparingFieldByField(new LaserImpl(COURTE));
    }
}
