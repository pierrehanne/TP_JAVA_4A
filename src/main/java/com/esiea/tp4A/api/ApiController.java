package com.esiea.tp4A.api;

import com.esiea.tp4A.domain.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ApiController {

    Map<Integer, MarsRoverImpl> Playerz = new HashMap<>();
    Integer Id = (1 + (int)(Math.random() * ((10 - 1) + 1))); // Nombre aléatoire entre 1 et 10
    PlanetMapImpl planetMap = new PlanetMapImpl(null);

    //Permet à un joueur de rejoindre une session et attribut à un user un id
    @GetMapping("/join")
    public String join(@RequestParam(value = "name", defaultValue = "World") String name) {
        planetMap.generateObstacles();
        MarsRoverImpl marsRover = new MarsRoverImpl(0,0, Direction.NORTH,planetMap);
        Playerz.put(Id,marsRover);
        System.out.println("playerz:"+Playerz); //Debug visu
        return String.format("Id="+Id);
    }

    //Position du joueur en fonction de l'id entrée
    @GetMapping("rover/position")
    public String currentPosition (@RequestParam (value = "id" , defaultValue = "-1") Integer marsRover) {
        if (marsRover < 0) {
            return String.format("Wrong ID");
        }
        return String.format("Current position of rover : " + Playerz.get(marsRover).getCurrentPosition());
    }

    //Déplacement du joueur en fonction des commandes f,b,l,r
    @GetMapping("rover/move")
    public String moving(@RequestParam (value = "id", defaultValue = "-1") Integer marsRover, @RequestParam (value = "command" , defaultValue = "f") String movement) {
        Playerz.get(marsRover).move(movement);
        return String.format("Current position of rover : " + Playerz.get(marsRover).getCurrentPosition());
    }

    //Tir
    @GetMapping("rover/shoot")
    public String shoot(@RequestParam (value = "id", defaultValue = "-1") Integer marsRover) {
        Playerz.get(marsRover).Shoot(planetMap);
        return String.format("Fire !");
    }

    //Récupérer la portée du tir
    @GetMapping("rover/shoot/range")
    public String shoot_range(@RequestParam (value = "id", defaultValue = "-1") Integer marsRover) {
        int laser_range = Playerz.get(marsRover).getLaserRange();
        return "Laser range = "+laser_range;
    }

    //Détection position des autres joueurs dans une zone de 30*30
    @GetMapping("rover/detection")
    public String detection(@RequestParam (value = "id", defaultValue = "-1") Integer marsRover) {
      Position P_area = Playerz.get(marsRover).getCurrentPosition();
      String temp ="Position: ";
        for (HashMap.Entry player : Playerz.entrySet()) {
            if (player.getKey() != marsRover) {
                Position pos =  ((MarsRoverImpl)player.getValue()).getCurrentPosition();
                if(Math.abs(pos.getX()-P_area.getX()) <= 15 && Math.abs(pos.getY()-P_area.getY()) <= 15)
                    temp += ((MarsRoverImpl)player.getValue()).getCurrentPosition(); } }
        return temp;
    }

    /*@GetMapping("rover/status")
    public String status(@RequestParam (value = "id", defaultValue = "-1") Integer marsRover) {
        boolean stat = Playerz.get(marsRover).isAlive();
        if (stat) {
            return ("You're still Alive !");
        }
        return ("You're Dead...");
    }*/

}
