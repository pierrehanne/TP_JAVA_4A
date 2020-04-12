package com.esiea.tp4A.api;
import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRoverImpl;
import com.esiea.tp4A.domain.PlanetMapImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.test.context.junit4.SpringRunner;


import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void return_helloworld_wher_get_hello() {
        String name = "World";
        String body = this.restTemplate.getForObject("/join", String.class);
        assertThat(body).contains("Id");

    }

    @Test
    public void return_get_current_position() {
        String join = this.restTemplate.getForObject("/join",String.class);
        String id = join.split("=")[1];
        String body = this.restTemplate.getForObject("/rover/position?id="+id, String.class);
        assertThat(body).contains("Current position of rover : ");
    }


    @Test
    public void return_get_current_position_aft_moves() {
        String join = this.restTemplate.getForObject("/join",String.class);
        String id = join.split("=")[1];
        String body = this.restTemplate.getForObject("/rover/move?id="+id+"&command=f", String.class);
        assertThat(body).contains("Current position of rover : ");

    }

    @Test
    public void return_shoot() {
        String join = this.restTemplate.getForObject("/join",String.class);
        String id = join.split("=")[1];
        String body = this.restTemplate.getForObject("/rover/shoot?id="+id, String.class);
        assertThat(body).isEqualTo("Fire !");
    }

    @Test
    public void return_shoot_range() {
        String join = this.restTemplate.getForObject("/join",String.class);
        String id = join.split("=")[1];
        String body = this.restTemplate.getForObject("/rover/shoot/range?id="+id, String.class);
        assertThat(body).contains("Laser range = ");

    }

    @Test
    public void return_detection() {
        String join = this.restTemplate.getForObject("/join",String.class);
        String id = join.split("=")[1];
        String body = this.restTemplate.getForObject("/rover/detection?id="+id, String.class);
        assertThat(body).contains("Position: ");

    }

}
