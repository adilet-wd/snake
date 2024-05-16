package com.example.snake.contest.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "winner_id", referencedColumnName = "id")
    private Player winner_id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "loser_id", referencedColumnName = "id")
    private Player loser_id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Set<Player> players_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Player getWinner_id() {
        return winner_id;
    }

    public void setWinner_id(Player winner_id) {
        this.winner_id = winner_id;
    }

    public Player getLoser_id() {
        return loser_id;
    }

    public void setLoser_id(Player loser_id) {
        this.loser_id = loser_id;
    }

    public Set<Player> getPlayers_id() {
        return players_id;
    }

    public void setPlayers_id(Set<Player> players_id) {
        this.players_id = players_id;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", winner_id=" + winner_id +
                ", loser_id=" + loser_id +
                ", players_id=" + players_id +
                '}';
    }
}
