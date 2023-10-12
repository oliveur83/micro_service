package com.example.joueur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.joueur.model.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
private static final Map<Integer, Player> playerService = new HashMap<Integer, Player>() {
        {
            put(1, new Player("toto",1));
            put(2, new Player("tata",2));
        }
    };

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable int id) {
        Player toto= playerService.get(id);
       
        return  toto;
    }

    @PostMapping("/{id}")
    public Player addPlayer(int id,@RequestBody Player player) {

        Player toto= new Player("tata", id);
    
        playerService.put(id,toto);
    return toto;
    }

    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable int id, @RequestBody Player player) {
        //pour recupere celle que l'on selctione 
        if (playerService.containsKey(id)) {
            playerService.remove(id);
            playerService.put(id,player);
            return player;
        } 
        else{
            return null;
        }
        //on modifie
        
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable int id) {
        if (playerService.containsKey(id)) {
            playerService.remove(id);
        } 
    }
}