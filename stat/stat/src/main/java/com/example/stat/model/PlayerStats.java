package com.example.stat.model;



public class PlayerStats {
    private int id;
    private int matchId;
    private int team1Score;
    private int team2Score;

    public PlayerStats() {
    }

    public PlayerStats(int id, int matchId, int team1Score, int team2Score) {
        this.id = id;
        this.matchId = matchId;
        this.team1Score = team1Score;
        this.team2Score = team2Score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(int team1Score) {
        this.team1Score = team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(int team2Score) {
        this.team2Score = team2Score;
    }
}
