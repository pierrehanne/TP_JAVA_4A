package com.esiea.tp4A.api;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void return_helloworld_wher_get_hello() {
        String name = "World";
        String body = this.restTemplate.getForObject("/hello", String.class);
        assertThat(body).isEqualTo("Hello %s!", name);
    }
}
