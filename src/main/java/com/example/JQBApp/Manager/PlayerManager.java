package com.example.JQBApp.Manager;

import com.example.JQBApp.DAO.Entity.Game;
import com.example.JQBApp.DAO.Entity.Player;
import com.example.JQBApp.DAO.Enum.Position;
import com.example.JQBApp.DAO.PlayerRepo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PlayerManager {
    private PlayerRepo playerRepo;

    public PlayerManager(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public Optional<Player> findById(Long id){
        return playerRepo.findById(id);
    }
    public void delete(Long id){
        Player player = playerRepo.findById(id).get();
        player.getListOfGames().forEach(game ->
                game.removePlayer(player));
//        System.out.println(player.getListOfGames().size());

        playerRepo.deleteById(id);
    }
    public Player add(Player player){
        return playerRepo.save(player);
    }
    public Iterable<Player> findAll(){
        return playerRepo.findAll();
    }

    public Iterable<Game> getGames(Long id){
        return playerRepo.findById(id).get().getListOfGames();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        Player player = new Player("Kuba", "Belka", "kuba.belka@email.com", "haslo123!", Position.DEFENDER);
        add(player);
    }

    public Player login(String email, String password) {
        ArrayList<Player> list = (ArrayList<Player>) findAll();
        Player player = list.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
        if (player == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect email.");
        if(player.getPassword().equals(password))
            return player;
        else
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password.");
    }
}
