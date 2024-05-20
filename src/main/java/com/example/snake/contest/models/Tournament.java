package com.example.snake.contest.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.servlet.http.Part;

import java.sql.Date;
import java.util.Set;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(unique = true)
    private String name;

    // Дата проведения турнира
    @Column
    private Date date;

    // Описание турнира
    @Column
    private String description;

    // В одном турнире участвует множество участников
    @OneToMany(mappedBy = "tournament", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Participant> participants;

    // Участники, не выбывшие с турнира
    @OneToMany(mappedBy = "tournament", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Participant> active_participants;

    // В одном турнире множество матчей
    @OneToMany(mappedBy = "tournament", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Match> matches;

    // Победитель турнира
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "winner_id", referencedColumnName = "id")
    private Participant winner_id;


    // Сеттеры геттеры

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }

    public Set<Participant> getActive_participants() {
        return active_participants;
    }

    public void setActive_participants(Set<Participant> active_participants) {
        this.active_participants = active_participants;
    }

    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }

    public Participant getWinner_id() {
        return winner_id;
    }

    public void setWinner_id(Participant winner_id) {
        this.winner_id = winner_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", participants=" + participants +
                ", active_participants=" + active_participants +
                ", matches=" + matches +
                ", winner_id=" + winner_id +
                '}';
    }
}
