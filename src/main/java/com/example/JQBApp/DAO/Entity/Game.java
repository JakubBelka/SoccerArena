package com.example.JQBApp.DAO.Entity;

import com.example.JQBApp.DAO.Enum.GameLevel;
import com.example.JQBApp.DAO.Enum.GameStatus;
import com.example.JQBApp.DAO.Enum.PitchType;
import com.example.JQBApp.DAO.PlayerRepo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Game {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    @Id
    private Long id;
    private String title;
    private String localization;
    private LocalDate dateOfGame;

    private int maxPlayers;

    private int minPlayers;

    @Enumerated(EnumType.STRING)
    private PitchType type;

    private int duration;

    @Enumerated(EnumType.STRING)
    private GameLevel gameLevel;

    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    public Game() {
    }

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "Players_Games",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    public Set<Player> listOfPlayers = new HashSet<>();

    public Game(String title, String localization, LocalDate dateOfGame, int maxPlayers, int minPlayers, PitchType type, int duration, GameLevel gameLevel, GameStatus gameStatus) {
        this.title = title;
        this.localization = localization;
        this.dateOfGame = dateOfGame;
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.type = type;
        this.duration = duration;
        this.gameLevel = gameLevel;
        this.gameStatus = gameStatus;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public LocalDate getDateOfGame() {
        return dateOfGame;
    }

    public void setDateOfGame(LocalDate dateOfGame) {
        this.dateOfGame = dateOfGame;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public PitchType getType() {
        return type;
    }

    public void setType(PitchType type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public GameLevel getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public boolean isAvailable()
    {
        int counter = listOfPlayers.size();
        if(counter<maxPlayers){
            return true;
        }
        else
            return false;
    }

    public void addPlayer(Player player){
        listOfPlayers.add(player);
    }

    public void removePlayer(Player player){
        if(listOfPlayers.contains(player))
            listOfPlayers.remove(player);
    }
}
