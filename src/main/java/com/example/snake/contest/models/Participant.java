package com.example.snake.contest.models;

import com.example.snake.security.models.CustomUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Set;


@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(unique = true)
    private String username;

    //    Функция пользователя, которую он отправляет при регистрации на турнир
    @Column
    private String function;

    //    Каждый участник турнира является пользователем, каждый пользователь может участвовать на множестве турниров
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private CustomUser customUser;

    //    Каждый участник турнира участвует в одном турнире
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="tournament_id", referencedColumnName = "id")
    @JsonBackReference
    private Tournament tournament;

    @OneToMany(mappedBy = "participant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Player> players;

    //    Сеттеры геттеры

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public CustomUser getCustomUser() {
        return customUser;
    }

    public void setCustomUser(CustomUser customUser) {
        this.customUser = customUser;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", function='" + function + '\'' +
                ", customUser=" + customUser +
                ", tournament=" + tournament +
                '}';
    }
}
