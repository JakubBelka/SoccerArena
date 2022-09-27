package com.example.JQBApp.API;

import com.example.JQBApp.DAO.Entity.Game;
import com.example.JQBApp.DAO.Entity.Player;
import com.example.JQBApp.Manager.PlayerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api")
public class PlayerApi {
    private PlayerManager playerManager;

    @Autowired
    public PlayerApi(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @GetMapping("/players")
    public Iterable<Player> getAllPlayers(){
        return playerManager.findAll();
    }
    @GetMapping("players/{id}")
    public Optional<Player> getById(@PathVariable Long id){
        return playerManager.findById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player){
        return playerManager.add(player);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping("/players")
    public Player updatePlayer(@RequestBody Player player){
        return playerManager.add(player);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/players/{id}")
    public void deletePlayer(@PathVariable Long id){
        playerManager.delete(id);
    }

    @GetMapping("/players/{id}/games")
    public Iterable<Game> getGames(@PathVariable Long id){
        return playerManager.getGames(id);
    }
    @GetMapping("/players/login")
    public Player checkEmail(@RequestParam String email, @RequestParam String password){
        return playerManager.login(email, Base64.getEncoder().encodeToString(password.getBytes()));
    }
}
