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
        assertThat(body).isEqualTo("Hello %s! your id is %d", name ,1);

    }

    /* Revoir le test petit problèmes dans les paramètres entrés maybe
    @Test
    public void return_get_current_position() {
        String join = this.restTemplate.getForObject("/join",String.class);
        Map<String,String> params = new HashMap<String, String>();
        params.put("id", "-1");
        Integer id = 1;
        String body = this.restTemplate.getForObject("/rover/position", String.class,params);
        assertThat(body).contains("Current position of rover : ");
    }

    /*
    @Test
    public void return_get_current_position_aft_moves() {
        String join = this.restTemplate.getForObject("/join",String.class);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "-1");
        String body = this.restTemplate.getForObject("/rover/move", String.class, params);
        assertThat(body).contains("Current position of rover : ");


    }

     */
}
