package com.example.JQBApp.API;

import com.example.JQBApp.Manager.GameManager;
import com.example.JQBApp.Manager.PlayerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.JQBApp.DAO.Entity.Game;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GameApi {
    private GameManager gameManager;


    @Autowired
    public GameApi(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @GetMapping("/games")
    public Iterable<Game> getAll(){
        return gameManager.findAll();
    }

    @GetMapping("games/{id}")
    public Optional<Game> getById(@PathVariable Long id){
        return gameManager.find(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/games")
    public Game addGame(@RequestBody Game game){
        return gameManager.save(game);
    }
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping("/games")
    public Game updateGame(@RequestBody Game game){
        return gameManager.save(game);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping("games/{gameId}/add-player/{playerId}")
    public void addPlayer(@PathVariable Long gameId, @PathVariable Long playerId){
        gameManager.AddPlayer(gameId, playerId);
    }
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("games/{id}")
    public void deleteGame(@PathVariable Long id){
        gameManager.deleteById(id);
    }
}
