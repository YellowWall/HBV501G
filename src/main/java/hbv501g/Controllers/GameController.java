package hbv501g.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import hbv501g.Classes.JsonResponse;
import hbv501g.Persistence.Entities.Hole;
import hbv501g.Persistence.Entities.Game;
import hbv501g.Persistence.Entities.User;
import hbv501g.Services.GameService;
import hbv501g.Services.HoleService;
import hbv501g.Services.UserService;
import hbv501g.objects.GameInput;


@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;
    @Autowired
    private HoleService holeService;
    @Autowired
    private UserService uService;
    
    /**
     * skilar game hlut fyrir id leiks
     * @param game
     * @return
     */
    @GetMapping("/")
    public JsonResponse<String> getGame(@RequestBody Game game){
        return new JsonResponse<String>(false, "aðferð ekki útfærð", null);
        //útfæra
    }
    
    /**
     * skilar öllum leikjum sem ákveðin spilari hefur skráð á ákveðnum velli
     * @param query GameInput hlutur sem inniheldur notandanafn og fieldId
     * @return  jsonresponse með lista af leikjum sem spilari hefur spilað á ákveðnum velli
     */
    @GetMapping("/fieldPlayer")
    public JsonResponse<List<Game>> getGamesForFieldAndPlayer(@RequestBody GameInput query){
        Long field = query.getFieldId();
        String userName = query.getUsername();
        User user = uService.getUser(userName);
        List<Game> games = gameService.findByUserGame(user);
        if (games != null) {
            return new JsonResponse<List<Game>>(true, "Games found", games);
        }
        return new JsonResponse<List<Game>>(false, "Games not found", null);
    }


    /**
     * skapar nýjan leik
     * @param gameInput stofnspilari leiks og völlur þar sem leikurinn mun vera leikinn
     * @return jsonresponse með nýja Game hlutinn sem var skapaður
     */
    @PostMapping("/")
    public JsonResponse<Game> postGame(@RequestBody GameInput gameInput){
        User thisUser = uService.getUser(gameInput.getUsername());
        if(thisUser == null){
            return new JsonResponse<Game>(false, "no such user", null);
        };
        Game newgGame = gameService.createGame(thisUser, gameInput.getFieldId());
        if(newgGame == null){
            return new JsonResponse<Game>(false, "game not made", null);
        };
        return new JsonResponse<Game>(true, "game made", newgGame);

    }

    /**
     * Vistar holu í leik
     * @param hole holan sem á að vista, hver leikur inniheldur margar spilaðar holur.
     * @return jsonresponse með vistuðu holunni ef það gekk að vista hana
     */
    @PostMapping("/hole")
    public JsonResponse<Hole> postHole(@RequestBody Hole hole){
        Hole retHole = holeService.saveHole(hole);
        if (retHole == null){
            return new JsonResponse<Hole>(false, "something went wrong", null);

        }
        return new JsonResponse<Hole>(true, "Hole saved", retHole);
    }

    /**
     * skilar öllum holum fyrir ákveðinn leik
     */
    @GetMapping("/gameHoles")
    public JsonResponse<List<Hole>> getGameHoles(@RequestBody Game game){
        List<Hole> retHoles = holeService.getGameHoles(game.getId());
        if(retHoles != null){
            return new JsonResponse<List<Hole>>(true, "gameHoles returned",retHoles);
        }
        return new JsonResponse<List<Hole>>(false, "gameholes not found",null);
    }

    /**
     * skilar öllum leikjum sem spilari hefur skráð
     * @param user spilari sem leitað er fyrir
     * @return allir leikir sem hann hefur skráð
     */
    @GetMapping("/playerGames")
    public JsonResponse<List<Game>> getUserGames(@RequestBody User user){
        List<Game> retgames = gameService.findByUserGame(user);
        if(retgames.size() != 0){
            return new JsonResponse<>(true, "User games returned", retgames);
        };
        return new JsonResponse<>(false, "no games found for provided user", null);
    }

    @DeleteMapping("/")
    public JsonResponse<Boolean> deleteGame(@RequestBody Game game){
        if(game == null){
            return new JsonResponse<Boolean>(false, "Game object missing or does not contain appropriate parameters", false);
        }
        //eyðum öllum holum tengdum við leik, á eftir að útfæra í HoleService
        holeService.deleteGameHoles(game.getId());
        //testum hvort að öllum holum hafi verið eytt
        
        Boolean deleted = gameService.deleteGame(game);
        if(!deleted){
            return new JsonResponse<Boolean>(false, "game not deleted", deleted);
        }
        return new JsonResponse<Boolean>(true, "game deleted", deleted);
    }
    
}
