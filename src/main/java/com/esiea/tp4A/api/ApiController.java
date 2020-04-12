package com.esiea.tp4A.api;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRoverImpl;
import com.esiea.tp4A.domain.PlanetMapImpl;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ApiController {

    Map<Integer, MarsRoverImpl> Playerz = new HashMap<>();
    Integer Id = (1 + (int)(Math.random() * ((10 - 1) + 1))); // Nombre aléatoire entre 1 et 10

    //Permet à un joueur de rejoindre une session et attribut à un user un id ( ici fixé à 1)
    @GetMapping("/join")
    public String join(@RequestParam(value = "name", defaultValue = "World") String name) {
        PlanetMapImpl planetMap = new PlanetMapImpl(null);
        planetMap.generateObstacles();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.NORTH,planetMap);
        Playerz.put(1,marsRover);
        System.out.println("playerz:"+Playerz); //Debug visu
        return String.format("Hello %s! your id is %d", name ,1);
    }

    //Position du joueur en fonction de l'id entré
    @GetMapping("rover/position")
    public String currentPosition (@RequestParam (value = "id" , defaultValue = "-1") Integer marsRover) {
        return String.format("Current position of rover : " + Playerz.get(marsRover).getCurrentPosition());
    }

    /*
    @GetMapping("rover/move")
    public String moving(@RequestParam (value = "id", defaultValue = "-1") Integer marsRover, @RequestParam (value = "command" , defaultValue = "f") String movement) {

        return String.format("Current position of rover: " + Playerz.get(marsRover).getCurrentPosition());
    }
    */
}
