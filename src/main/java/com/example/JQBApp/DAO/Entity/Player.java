package com.example.JQBApp.DAO.Entity;

import com.example.JQBApp.DAO.Enum.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Console;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Player {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    @Id
    private Long id;
    private String name;
    private String surname;

    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    private int level = 1;

    @Enumerated(EnumType.STRING)
    public Position position;

    @JsonIgnore
    @ManyToMany(
            mappedBy = "listOfPlayers",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    public Set<Game> listOfGames = new HashSet<>();
    public Player() {}

    public Player(String name, String surname, String email, String password, Position position) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.position = position;
        setPassword(password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Base64.getEncoder().encodeToString(password.getBytes());
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public Set<Game> getListOfGames() {
        return listOfGames;
    }
    public void clearListOfGames() {
        listOfGames.clear();
    }
}
