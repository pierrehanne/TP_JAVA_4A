package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PlanetMapImplTest {

    @Test
    void init_map() {
        PlanetMapImpl planetMapImpl = new PlanetMapImpl(100, 100);
        Assertions.assertThat(planetMapImpl).isEqualToComparingFieldByField(new PlanetMapImpl(100, 100));
    }
}
