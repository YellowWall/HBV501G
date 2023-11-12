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
@CrossOrigin
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @Autowired
    private HoleService holeService;

    @Autowired
    private UserService uService;

    @GetMapping("/")
    public JsonResponse<List<Game>> getAllGames() {
        List<Game> games = gameService.findAll();

        if (games != null) {
            return new JsonResponse<List<Game>>(true, "Games found", games);
        }

        return new JsonResponse<List<Game>>(false, "Games not found", null);
    }

    /**
     * skilar game hlut fyrir id leiks
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public JsonResponse<Game> getGame(@PathVariable Long id){
        Game game = gameService.findByIdGame(id);

        if (game != null) {
            return new JsonResponse<Game>(true, "Game found", game);
        }

        return new JsonResponse<Game>(false, "Game not found", null);
    }

    /**
     * skapar nýjan leik
     * @param game stofnspilari leiks og völlur þar sem leikurinn mun vera leikinn
     * @return jsonresponse með nýja Game hlutinn sem var skapaður
     */
    @PostMapping("/")
    public JsonResponse<Game> postGame(@RequestBody Game game){
        Game newGame = gameService.createGame(game);
        if(newGame == null){
            return new JsonResponse<Game>(false, "game not made", null);
        };
        return new JsonResponse<Game>(true, "game made", newGame);

    }

    @DeleteMapping("/{id}")
    public JsonResponse<Boolean> deleteGame(@RequestParam Long id){
        if(gameService.findByIdGame(id) == null){
            return new JsonResponse<Boolean>(false, "Game object missing or does not contain appropriate parameters", false);
        }
        //eyðum öllum holum tengdum við leik, á eftir að útfæra í HoleService
        holeService.deleteGameHoles(id);
        //testum hvort að öllum holum hafi verið eytt

        Boolean deleted = gameService.deleteGame(id);
        if(!deleted){
            return new JsonResponse<Boolean>(false, "game not deleted", deleted);
        }
        return new JsonResponse<Boolean>(true, "game deleted", deleted);
    }

    /**
     * skilar öllum leikjum sem ákveðin spilari hefur skráð á ákveðnum velli
     * @param query GameInput hlutur sem inniheldur notandanafn og fieldId
     * @return  jsonresponse með lista af leikjum sem spilari hefur spilað á ákveðnum velli
     */
    @PostMapping("/fieldPlayer")
    public JsonResponse<List<Game>> getGamesForFieldAndPlayer(@RequestBody GameInput query){
        Long field = query.getFieldId();
        String userName = query.getUsername();
        User user = uService.getUser(userName);
        List<Game> games = gameService.findALLByUserId(user);
        if (games != null) {
            return new JsonResponse<List<Game>>(true, "Games found", games);
        }
        return new JsonResponse<List<Game>>(false, "Games not found", null);
    }

    /**
     * skilar öllum holum fyrir ákveðinn leik
     */
    @PostMapping("/gameHoles")
    public JsonResponse<List<Hole>> getGameHoles(@RequestBody Game game){
        List<Hole> retHoles = holeService.findAllByGameId(game.getId());
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
    @PostMapping("/playerGames")
    public JsonResponse<List<Game>> getUserGames(@RequestBody User user){
        List<Game> retgames = gameService.findALLByUserId(user);
        if(retgames.size() != 0){
            return new JsonResponse<>(true, "User games returned", retgames);
        };
        return new JsonResponse<>(false, "no games found for provided user", null);
    }
    
}
