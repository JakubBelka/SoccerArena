package com.example.JQBApp.Manager;

import com.example.JQBApp.DAO.Entity.Player;
import com.example.JQBApp.DAO.Enum.GameLevel;
import com.example.JQBApp.DAO.Enum.GameStatus;
import com.example.JQBApp.DAO.Enum.PitchType;
import com.example.JQBApp.DAO.GameRepo;

import com.example.JQBApp.DAO.Entity.Game;
import com.example.JQBApp.DAO.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class GameManager {

    private GameRepo gameRepo;
    private PlayerRepo playerRepo;

    public GameManager(GameRepo gameRepo, PlayerRepo playerRepo) {
        this.gameRepo = gameRepo;
        this.playerRepo = playerRepo;
    }
    public Optional<Game> find(Long id){
        return gameRepo.findById(id);
    }
    public Game AddPlayer(Long gameId, Long playerId){
        Game game = gameRepo.findById(gameId).get();
        Player player = playerRepo.findById(playerId).get();
        game.addPlayer(player);
        return save(game);
    }
    public Iterable<Game> findAll(){
        return gameRepo.findAll();
    }
    public Game save(Game game){
        return gameRepo.save(game);
    }
    public void deleteById(Long id){
        gameRepo.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        save(new Game("Trening", "Malcuzynskiego 4", LocalDate.of(2022, 8, 1), 8, 14, PitchType.ARTIFICIAL_TURF, 90, GameLevel.MEDIUM, GameStatus.CANCELED));
    }
}
