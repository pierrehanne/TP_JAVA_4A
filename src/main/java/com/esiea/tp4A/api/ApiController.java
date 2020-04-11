package com.esiea.tp4A.api;

import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
