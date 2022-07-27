package com.example.JQBApp.DAO;

import com.example.JQBApp.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepo extends CrudRepository<Game, Long> {
}
