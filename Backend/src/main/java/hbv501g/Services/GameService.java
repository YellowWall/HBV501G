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
     * @param game upplýsingar um leikinn.
     * @return Leikurinn sem verið er að spila
     */
    public Game createGame(Game game){
        return gameRepository.save(game);
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
        return gameRepository.findById(id);
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
        gameRepository.deleteById(game.getId());
        var check = gameRepository.findById(game.getId());
        return check == null;
    }
}
