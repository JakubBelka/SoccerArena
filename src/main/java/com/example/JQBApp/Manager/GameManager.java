package com.example.JQBApp.Manager;

import com.example.JQBApp.DAO.GameRepo;

import com.example.JQBApp.Game;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class GameManager {
    private GameRepo gameRepo;

    public GameManager(GameRepo gameRepo) {
        this.gameRepo = gameRepo;
    }
    public Optional<Game> find(Long id){
        return gameRepo.findById(id);
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
    public void fillDB(){
        save(new Game(1L, "Trening", "Malcuzynskiego 4", LocalDate.of(2022, 8, 1)));
        save(new Game(2L, "Liga Mistrzow", "Stadion Legii", LocalDate.of(2022, 9, 21)));
        save(new Game(3L, "EURO", "PGE Narodowy", LocalDate.of(2022, 8, 22)));    }
}
