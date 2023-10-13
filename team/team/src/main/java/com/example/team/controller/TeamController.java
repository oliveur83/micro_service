package com.example.team.controller;

import com.example.team.model.Player;
import com.example.team.model.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private List<Player> teams = new ArrayList<>();
    private int nextId = 1;
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;
    @GetMapping("/{id}")
    public Player getTeamById(@PathVariable int id) {
        // Faites une requête HTTP directe au service "PlayerService" en utilisant RestTemplate
        Player player = this.restTemplate.getForObject("http://playerservice/players/" + id, Player.class);

        // Utilisez la réponse du service "PlayerService" pour créer une réponse pour votre contrôleur
        return player;
    }



    @PostMapping("/")
    public Team addTeam(@RequestBody Team team) {
        team.setId(nextId++);
        
        return team;
    }

    @PutMapping("/{id}")
    public Team updateTeam(@PathVariable int id, @RequestBody Team updatedTeam) {

        return null; // Ou renvoyez une réponse HTTP 404 (Not Found)
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable int id) {
        
    }
}
