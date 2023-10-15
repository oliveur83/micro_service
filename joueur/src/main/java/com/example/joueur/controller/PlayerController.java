package com.example.joueur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.joueur.model.Player;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;

import java.util.Map;

@Api(value = "Player", description = "Opérations pour la gestion des joueurs")
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
    @ApiOperation(value = "Récupérer un joueur par I")
    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable int id) {
        Player toto= playerService.get(id);
       
        return  toto;
    }
    @ApiOperation(value = "Ajouter un joueur")
@PostMapping("/")
public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
    int maxId = 0;

    // Recherchez l'ID maximum actuel dans le service de joueurs
    for (int id : playerService.keySet()) {
        if (id > maxId) {
            maxId = id;
        }
    }

    // Incrémentez l'ID maximum de 1 pour obtenir le nouvel ID
    int newId = maxId + 1;

    // Ajoutez le joueur avec le nouvel ID dans le service de joueurs
    player.setId(newId);
    playerService.put(newId, player);

    // Récupérez le joueur ajouté
    Player newPlayer = playerService.get(newId);

    return ResponseEntity.ok(newPlayer);
}

@ApiOperation(value = "Mettre à jour un joueur par ID")
    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable int id, @RequestBody Player player) {
        //todo identifiant unique 
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
    @ApiOperation(value = "Supprimer un joueur par ID")
    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable int id) {
        if (playerService.containsKey(id)) {
            playerService.remove(id);
        } 
    }
}