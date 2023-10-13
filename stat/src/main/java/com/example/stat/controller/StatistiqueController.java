package com.example.stat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.stat.model.Statistique;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/statistiques")
public class StatistiqueController {

    @Autowired
    private static final Map<Integer, Statistique> statistiqueService = new HashMap<>();

    @GetMapping("/{id}")
    public Statistique getStatistiqueById(@PathVariable int id) {
        // Implémentez la logique pour récupérer des statistiques par leur identifiant
        Statistique statistique = statistiqueService.get(id);
        return statistique;
    }

    @PostMapping
    public Statistique addStatistique(@RequestBody Statistique statistique) {
         int id = 0;// Générez un identifiant unique pour les statistiques
        statistique.setId(id);
        statistiqueService.put(id, statistique);
        return statistique;
    }

    @PutMapping("/{id}")
    public Statistique updateStatistique(@PathVariable int id, @RequestBody Statistique statistique) {
        // Implémentez la logique pour mettre à jour des statistiques existantes
        if (statistiqueService.containsKey(id)) {
            statistique.setId(id);
            statistiqueService.put(id, statistique);
            return statistique;
        } else {
            return null; // Ou une autre réponse d'erreur
        }
    }

    @DeleteMapping("/{id}")
    public void deleteStatistique(@PathVariable int id) {
        // Implémentez la logique pour supprimer des statistiques par leur identifiant
        statistiqueService.remove(id);
    }


}
