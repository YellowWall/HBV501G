package hbv501g.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hbv501g.Classes.JsonResponse;
import hbv501g.Persistence.Entities.Game;
import hbv501g.Persistence.Entities.User;
import hbv501g.Services.GameService;
import hbv501g.Services.HoleService;
import hbv501g.Services.UserService;

@RestController
@RequestMapping("/game")
public class GameController {
    private GameService gameService;
    private HoleService holeService;
    private UserService uService;
    
    /*
    @GetMapping(value = "") //her vantar að útfæra hvernig playerID er fengið
    public getPosts(@PathVariable Long playerID){

    }*/

    @PostMapping("/")
    public JsonResponse<Game> postGame(@RequestBody String username, String fieldID){
        User thisUser = uService.getUser(username);
        Game newgGame = gameService.createGame(thisUser, Long.parseLong(fieldID));
        if(newgGame == null){
            return new JsonResponse<Game>(false, "game not made", null);
        }
        return new JsonResponse<Game>(true, "game made", newgGame);

    }
}
