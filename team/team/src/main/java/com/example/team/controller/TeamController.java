package com.example.team.controller;

import com.example.team.model.Player;
import com.example.team.model.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    private static final Map<Integer, Team> teamService = new HashMap<Integer, Team>() {
        {
            Team team1 = new Team("tottttto", 1, Arrays.asList(new Player("Joueur1", 1), new Player("Joueur2", 2)));

              put(1, team1);
    
              Team team2 = new Team("tatoa", 2, Arrays.asList(new Player("Joueur1", 1), new Player("Joueur2", 2)));

            put(2, team2);
        }
    };
    
    private int nextId = 1;
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;
    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable int id) {
        // Faites une requête HTTP directe au service "PlayerService" en utilisant RestTemplate
        Team existingTeam = teamService.get(id);
        // Utilisez la réponse du service "PlayerService" pour créer une réponse pour votre contrôleur
        return existingTeam ;
    }



    @PostMapping("/")
    public Team addTeam() {
                
        Random random = new Random();
        int min = 1;
        int max = 2;
        int idplay1 = random.nextInt(max - min + 1) + min;
        int idplay2 = random.nextInt(max - min + 1) + min;
        Player player1 = this.restTemplate.getForObject("http://playerservice/players/" + idplay1, Player.class);
        Player player2 = this.restTemplate.getForObject("http://playerservice/players/" + idplay2, Player.class);
        
        Team team1 = new Team("tottttto", 3, Arrays.asList(player1, player2));


        teamService.put(team1.getId(), team1); // Ajouter l'équipe à playerService avec son ID
        return team1;
    }
    

    @PutMapping("/{id}")
    public List<Team> updateTeam(@PathVariable int id, @RequestBody Team updatedTeam) {
        Team existingTeam = teamService.get(id);

        if (existingTeam == null) {
         List<Team> updatedTeamList = new ArrayList<>(teamService.values());
        return updatedTeamList;}

        // Mettez à jour les informations de l'équipe avec les données fournies dans le corps de la requête
        existingTeam.setName(updatedTeam.getName());
        // Vous pouvez mettre à jour d'autres attributs de l'équipe ici

        // Mettez à jour l'équipe dans la carte
        teamService.put(id, existingTeam);

        // Renvoyez la liste mise à jour de toutes les équipes
        List<Team> updatedTeamList = new ArrayList<>(teamService.values());
        return updatedTeamList;
    }

    @DeleteMapping("/{id}")
    public List<Team> deleteTeam(@PathVariable int id) {
        Team existingTeam = teamService.get(id);

        if (existingTeam == null) {
            List<Team> updatedTeamList = new ArrayList<>(teamService.values());
        return updatedTeamList; // Réponse HTTP 404 si l'équipe n'existe pas
        }

        // Supprimez l'équipe de la carte
        teamService.remove(id);

        // Renvoyez la liste mise à jour de toutes les équipes
        List<Team> updatedTeamList = new ArrayList<>(teamService.values());
        return updatedTeamList;
    }

}
