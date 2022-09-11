package com.example.JQBApp.DAO;

import com.example.JQBApp.DAO.Entity.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends CrudRepository<Player, Long> {
}
