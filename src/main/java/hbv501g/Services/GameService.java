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

    /**
     * Býr til nýjan leik á gefnum velli
     * @param firstUser Sá spilari sem býr til leikinn
     * @param fieldID Völlurinn sem spilað er á
     * @return Leikurinn sem verið er að spila
     */
    public Game createGame(User firstUser, Long fieldID){
        //bætum við prófun fyrir tilvist field eftir að field hefur verið útfært
        Game newGame = new Game(firstUser.getId(),fieldID,new Date(),"");
        System.out.println(newGame.toString());
        newGame = gameRepository.save(newGame);
        return newGame;
    };

    /**
     * Uppfærir leik sem er núþegar til
     * @param game Leikurinn sem á að uppfæra
     * @return uppfærður leikur
     */
    public Game updateGame(Game game){
        if(game != null && game.getId() != 0){
            return gameRepository.save(game);
        }
        return null;
    };

    /**
     * Finnur leik sem hefur tiltekið gameId
     * @param id ID tala leiksins sem leitað er að
     * @return Ef leikurinn finnst er honum skilað
     */

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

    /**
     * Eyðir þeim leik sem fallið fær
     * @param game leikurinn sem á að eyða
     * @return true ef tókst að eiða leiknum, annars false
     */
    public boolean deleteGame(Game game){
        gameRepository.delete(game);
        var check = gameRepository.findById(game.getId());
        if(check.get() == null){
            return true;
        }
        return false;

    }
}
