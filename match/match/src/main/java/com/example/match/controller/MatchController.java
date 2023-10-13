
package com.example.match.controller;
import com.example.match.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private static final Map<Integer, Match> matchService = new HashMap<>();

    @GetMapping("/{id}")
    public Match getMatchById(@PathVariable int id) {
        // Implémentez la logique pour récupérer un match par son identifiant
        Match match = matchService.get(id);
        return match;
    }

    @PostMapping("/{id}-{id2}")
    public Match addMatch(@RequestBody Match match) {
        int id = 0;// Générez un identifiant unique pour le match
        match.setId(id);
        matchService.put(id, match);
        return match;
    }

    @PutMapping("/{id}")
    public Match updateMatch(@PathVariable int id, @RequestBody Match match) {
        // Implémentez la logique pour mettre à jour un match existant
        if (matchService.containsKey(id)) {
            match.setId(id);
            matchService.put(id, match);
            return match;
        } else {
            return null; // Ou une autre réponse d'erreur
        }
    }

    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable int id) {
        // Implémentez la logique pour supprimer un match par son identifiant
        matchService.remove(id);
    }


}

