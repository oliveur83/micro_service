package com.example.team.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    private int id;
    private List<Player> players;

    public Team(String name, int id, List<Player> players) {
        this.name = name;
        this.id = id;
        this.players = players;
    }
    public Team() {
        // Constructeur par défaut sans arguments
    }
    // Méthode pour ajouter un joueur à la liste
    public void addPlayer(Player player) {
        players.add(player);
    }
    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<Player> getPlayers() {
        return players;
    }

    // Setter pour la liste de joueurs
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
