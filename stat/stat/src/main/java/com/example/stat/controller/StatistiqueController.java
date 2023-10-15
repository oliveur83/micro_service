package com.example.stat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.stat.model.PlayerStats;
import com.example.stat.model.TeamStats;
import java.util.HashMap;
import java.util.Map;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Statistique", description = "Opérations pour la gestion des statistiques")
@RestController
@RequestMapping("/statistiques")
public class StatistiqueController {

    @Autowired
    private static final Map<Integer, TeamStats> statsService = new HashMap<Integer, TeamStats>() {
        {
            put(1, new TeamStats(0, 0, 0, 0));
            put(2, new TeamStats(0, 0, 0, 0));
        }
    };

    @Autowired
    private static final Map<Integer, PlayerStats> playerStatsService = new HashMap<Integer, PlayerStats>() {
        {
            put(1, new PlayerStats(0, 0, 0, 0));
            put(2, new PlayerStats(0, 0, 0, 0));
        }
    };

    @ApiOperation(value = "Récupérer les statistiques d'une équipe par ID")
    @GetMapping("/team-stats/{teamId}")
    public ResponseEntity<TeamStats> getTeamStats(@PathVariable int teamId) {
        TeamStats teamStats = statsService.get(teamId);
        if (teamStats != null) {
            return ResponseEntity.ok(teamStats);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Récupérer les statistiques d'un joueur par ID")
    @GetMapping("/player-stats/{playerId}")
    public ResponseEntity<PlayerStats> getPlayerStats(@PathVariable int playerId) {
        PlayerStats playerStats = playerStatsService.get(playerId);
        if (playerStats != null) {
            return ResponseEntity.ok(playerStats);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
