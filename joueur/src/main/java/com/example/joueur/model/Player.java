package com.example.joueur.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID unique du joueur")
    private int id;
    @ApiModelProperty(notes = "Nom du joueur")
    private String name;

    public Player(String name, int id) {
        this.name = name;
        this.id = id;

    }
    public Player() {
        // Constructeur par défaut sans arguments
    }
    // Getters et setters

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

    
}
