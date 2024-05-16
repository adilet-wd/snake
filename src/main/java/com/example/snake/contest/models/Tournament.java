package com.example.snake.contest.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Set;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_id", referencedColumnName = "id")
    private Set<Participant> participant_id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "active_participant_id", referencedColumnName = "id")
    private Set<Participant> active_participant_id;

    @Column
    private Date date;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "match", referencedColumnName = "id")
    private Set<Match> match;

    public Set<Match> getMatch() {
        return match;
    }

    public void setMatch(Set<Match> match) {
        this.match = match;
    }

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

    public Set<Participant> getParticipant_id() {
        return participant_id;
    }

    public void setParticipant_id(Set<Participant> participant_id) {
        this.participant_id = participant_id;
    }

    public Set<Participant> getActive_participant_id() {
        return active_participant_id;
    }

    public void setActive_participant_id(Set<Participant> active_participant_id) {
        this.active_participant_id = active_participant_id;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", participant_id=" + participant_id +
                ", active_participant_id=" + active_participant_id +
                ", date=" + date +
                ", match=" + match +
                '}';
    }

}
