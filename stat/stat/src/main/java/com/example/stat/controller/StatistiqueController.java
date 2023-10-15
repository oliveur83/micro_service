package com.example.stat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.stat.model.PlayerStats;
import com.example.stat.model.TeamStats;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/statistiques")
public class StatistiqueController {

    @Autowired
    private static final Map<Integer, TeamStats> StatsService = new HashMap<Integer, TeamStats>() {
        {
            TeamStats STeamStats1 = new TeamStats(0, 0, 0, 0);

              put(1, STeamStats1);
    
              TeamStats STeamStats2 = new TeamStats(0, 0, 0, 0);

            put(2, STeamStats2);
        }
    };

    @Autowired
    private static final Map<Integer, PlayerStats> playerStatsService = new HashMap<Integer, PlayerStats>() {
        {
            PlayerStats playerStats1 = new PlayerStats(0, 0, 0, 0);

              put(1, playerStats1);
    
            PlayerStats playerStats2 = new PlayerStats(0, 0, 0, 0);

            put(2, playerStats2);
        }
    }; @GetMapping("/team-stats/{teamId}")
    public ResponseEntity<TeamStats> getTeamStats(@PathVariable int teamId) {
        // Récupérez les statistiques de l'équipe en fonction de teamId
        TeamStats teamStats = StatsService.get(teamId);
        if (teamStats != null) {
            return ResponseEntity.ok(teamStats);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/player-stats/{playerId}")
    public ResponseEntity<PlayerStats> getPlayerStats(@PathVariable int playerId) {
        // Récupérez les statistiques du joueur en fonction de playerId
        PlayerStats playerStats = playerStatsService.get(playerId);
        if (playerStats != null) {
            return ResponseEntity.ok(playerStats);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
