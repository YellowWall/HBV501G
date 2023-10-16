package hbv501g.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hbv501g.Persistence.Entities.Game;
import hbv501g.Persistence.Entities.User;
import hbv501g.Persistence.Repositories.GameRepository;


@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public Game createGame(User firstUser, Long fieldID){
        //bætum við prófun fyrir tilvist field eftir að field hefur verið útfært
        Game newGame = new Game(firstUser.getId(), fieldID);
        newGame = gameRepository.save(newGame);
        return newGame;
    };
}
