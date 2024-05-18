package com.example.snake.contest.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //   Победитель матча
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "winner", referencedColumnName = "id")
    private Player winner;

    //   Проигравший матча
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "loser", referencedColumnName = "id")
    private Player loser;

    //   Игроки, принимавшие участие в матче
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Player> players;

    //   Турнир, в котором проходят матчи
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="tournament", referencedColumnName = "id")
    @JsonBackReference
    private Tournament tournament;

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", winner=" + winner +
                ", loser=" + loser +
                ", players=" + players +
                ", tournament=" + tournament +
                '}';
    }
}
