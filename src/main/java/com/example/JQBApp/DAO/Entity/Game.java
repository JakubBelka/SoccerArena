package com.example.JQBApp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
public class Game {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String title;
    private String Localization;
    private LocalDate dateOfGame;


    public Game() {
    }

    public Game(Long id, String title, String localization, LocalDate dateOfGame) {
        this.id = id;
        this.title = title;
        Localization = localization;
        this.dateOfGame = dateOfGame;
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
        return Localization;
    }

    public void setLocalization(String localization) {
        Localization = localization;
    }

    public LocalDate getDateOfGame() {
        return dateOfGame;
    }

    public void setDateOfGame(LocalDate dateOfGame) {
        this.dateOfGame = dateOfGame;
    }
}
