package com.example.JQBApp.API;

import com.example.JQBApp.Manager.GameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.JQBApp.Game;

import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameApi {
    private GameManager gameManager;

    @Autowired
    public GameApi(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @GetMapping("/all")
    public Iterable<Game> getAll(){
        return gameManager.findAll();
    }

    @GetMapping
    public Optional<Game> getById(@RequestParam Long index){
        return gameManager.find(index);
    }

    @PostMapping
    public Game addGame(@RequestBody Game game){
        return gameManager.save(game);
    }
    @PutMapping
    public Game updateGame(@RequestBody Game game){
        return gameManager.save(game);
    }

    @DeleteMapping
    public void deleteGame(@RequestParam Long index){
        gameManager.deleteById(index);
    }
}
