package com.example.match.controller;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.example.match.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Match", description = "Opérations pour la gestion des matchs")
@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private static final Map<Integer, Match> matchService = new HashMap<Integer, Match>() {
        {
            put(1, new Match(1, "toto", "tata"));
            put(2, new Match(2, "tata", "titi"));
        }
    };

   
    @ApiOperation(value = "Récupérer un match par ID")
    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "getMatchByIdFallback")
    public ResponseEntity<Match> getMatchById(@PathVariable int id) {
        Match match = matchService.get(id);
        if (match != null) {
            return ResponseEntity.ok(match);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @ApiOperation(value = "Ajouter un match")
    @PostMapping("/{name1}-{name2}")
    public ResponseEntity<List<Match> >addMatch(@PathVariable String name1, @PathVariable String name2) {
        int id1 = matchService.size() + 1;
        Match match = new Match(id1, name1, name2);
        matchService.put(id1, match);
        return ResponseEntity.ok(new ArrayList<>(matchService.values()));
    }
    
    @ApiOperation(value = "Mettre à jour un match par ID")
    @PutMapping("/{id}")
    @HystrixCommand(fallbackMethod = "updateMatchFallback")
    public ResponseEntity<Match>updateMatch(@PathVariable int id, @RequestBody Match updatedMatch) {
        if (matchService.containsKey(id)) {
            updatedMatch.setId(id);
            matchService.put(id, updatedMatch);
            return ResponseEntity.ok(updatedMatch);
        } else {
            return ResponseEntity.notFound().build();
        }
    };


    @ApiOperation(value = "Supprimer un match par ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Match> >deleteMatch(@PathVariable int id) {
        matchService.remove(id);
        return ResponseEntity.ok(new ArrayList<>(matchService.values()));
    }
}
