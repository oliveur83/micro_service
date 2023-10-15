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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Team", description = "Opérations pour la gestion des équipes")
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

    @ApiOperation(value = "Récupérer une équipe par ID")
    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable int id) {
        Team existingTeam = teamService.get(id);
        return existingTeam;
    }

    @ApiOperation(value = "Ajouter une équipe aléatoire")
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

        teamService.put(team1.getId(), team1);
        return team1;
    }

    @ApiOperation(value = "Mettre à jour une équipe par ID")
    @PutMapping("/{id}")
    public List<Team> updateTeam(@PathVariable int id, @RequestBody Team updatedTeam) {
        Team existingTeam = teamService.get(id);

        if (existingTeam == null) {
            List<Team> updatedTeamList = new ArrayList<>(teamService.values());
            return updatedTeamList;
        }

        existingTeam.setName(updatedTeam.getName());

        teamService.put(id, existingTeam);

        List<Team> updatedTeamList = new ArrayList<>(teamService.values());
        return updatedTeamList;
    }

    @ApiOperation(value = "Supprimer une équipe par ID")
    @DeleteMapping("/{id}")
    public List<Team> deleteTeam(@PathVariable int id) {
        Team existingTeam = teamService.get(id);

        if (existingTeam == null) {
            List<Team> updatedTeamList = new ArrayList<>(teamService.values());
            return updatedTeamList;
        }

        teamService.remove(id);

        List<Team> updatedTeamList = new ArrayList<>(teamService.values());
        return updatedTeamList;
    }
}
