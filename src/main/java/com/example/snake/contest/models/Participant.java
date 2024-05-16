package com.example.snake.contest.models;

import com.example.snake.security.models.CustomUser;
import jakarta.persistence.*;

@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private CustomUser user_id;

    @Column
    private String function;

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

    public CustomUser getUser_id() {
        return user_id;
    }

    public void setUser_id(CustomUser user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", function='" + function + '\'' +
                '}';
    }
}
