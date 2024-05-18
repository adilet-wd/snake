package com.example.snake.contest.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //    Каждый участник турнира принимает участие в разных матчах
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "participant", referencedColumnName = "id")
    @JsonBackReference
    private Participant participant;

    //    Каждый игрок принимает участие в одном матче
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="match", referencedColumnName = "id")
    @JsonBackReference
    private Match match;

    // Баффы, которые игрок получил во время игры
    @Column
    private String buffs;

    // Информация, которую хранит игрок
    @Column
    private String store;

    // Ходы, которые сделал игрок
    @Column
    private String moves;


    //    Сеттеры геттеры

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Participant getParticipant() {
//        return participant;
//    }
//
//    public void setParticipant(Participant participant) {
//        this.participant = participant;
//    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public String getBuffs() {
        return buffs;
    }

    public void setBuffs(String buffs) {
        this.buffs = buffs;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getMoves() {
        return moves;
    }

    public void setMoves(String moves) {
        this.moves = moves;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
//                ", participant=" + participant +
                ", match=" + match +
                ", buffs='" + buffs + '\'' +
                ", store='" + store + '\'' +
                ", moves='" + moves + '\'' +
                '}';
    }
}
