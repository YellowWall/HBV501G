package hbv501g.Services;

import java.util.Date;
import java.util.List;

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
        Game newGame = new Game(firstUser.getId(),fieldID,new Date(),"");
        System.out.println(newGame.toString());
        newGame = gameRepository.save(newGame);
        return newGame;
    };

    public Game updateGame(Game game){
        if(game != null && game.getId() != 0){
            return gameRepository.save(game);
        }
        return null;
    };

    public Game findByIdGame(long id){
        var retgame = gameRepository.findById(id);
        if(retgame.get() == null){
            return null;
        }
        return retgame.get();
    }
    /**
     * skilar öllum leikjum ákveðins spilara
     * @param user spilari sem leitað er að leikjum fyrir
     * @return allir leikir spilara
     */
    public List<Game> findByUserGame(User user){
        List<Game> retgame = gameRepository.findByPlayerId(user.getId());
        return retgame;
    }

    /**
     * skilar öllum leikjum ákveðins spilara
     * @param userID ID tala spilara sem leitað er að leikjum fyrir
     * @return allir leikir spilara
     */
    public List<Game> findByUserGame(Long userID){
        List<Game> retgame = gameRepository.findByPlayerId(userID);
        return retgame;
    }
    
    public boolean deleteGame(Game game){
        gameRepository.deleteById(game.getId());
        var check = gameRepository.findById(game.getId());
        if(check.get() == null){
            return true;
        }
        return false;

    }
}
