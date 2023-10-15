
package com.example.match.controller;
import com.example.match.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@GetMapping("/{id}")
public ResponseEntity<Match> getMatchById(@PathVariable int id) {
    // Implémentez la logique pour récupérer un match par son identifiant
    Match match = matchService.get(id);
    if (match != null) {
        return ResponseEntity.ok(match);
    } else {
        return ResponseEntity.notFound().build(); // Réponse HTTP 404 si le match n'existe pas
    }
}

@PostMapping("/{name1}-{name2}")
public ResponseEntity<List<Match>> addMatch(@PathVariable String name1, @PathVariable String name2) {
    int id1 = matchService.size() + 1; // Générez un identifiant unique pour la première équipe
    Match match = new Match(id1, name1, name2); // Créez un nouveau match
 //todo communication 
    matchService.put(id1, match);

    return ResponseEntity.ok(new ArrayList<>(matchService.values())); // Renvoyez la liste mise à jour des matchs
}



@PutMapping("/{id}")
public ResponseEntity<List<Match>> updateMatch(@PathVariable int id, @RequestBody Match updatedMatch) {
    // Implémentez la logique pour mettre à jour un match existant
    if (matchService.containsKey(id)) {
        updatedMatch.setId(id);
        matchService.put(id, updatedMatch);
        return ResponseEntity.ok(new ArrayList<>(matchService.values())); // Renvoyez la liste mise à jour des matchs
    } else {
        return ResponseEntity.notFound().build(); // Réponse HTTP 404 si le match n'existe pas
    }
}

@DeleteMapping("/{id}")
public ResponseEntity<List<Match>> deleteMatch(@PathVariable int id) {
    // Implémentez la logique pour supprimer un match par son identifiant
    matchService.remove(id);
    return ResponseEntity.ok(new ArrayList<>(matchService.values())); // Renvoyez la liste mise à jour des matchs
}

}

