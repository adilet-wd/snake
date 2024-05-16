package com.example.snake.contest.models;

import jakarta.persistence.*;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_id", referencedColumnName = "id")
    private Participant participant_id;

    @Column
    private String buffs;

    @Column
    private String store;

    @Column
    private String moves;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Participant getParticipant_id() {
        return participant_id;
    }

    public void setParticipant_id(Participant participant_id) {
        this.participant_id = participant_id;
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
                ", participant_id=" + participant_id +
                ", buffs='" + buffs + '\'' +
                ", store='" + store + '\'' +
                ", moves='" + moves + '\'' +
                '}';
    }
}
