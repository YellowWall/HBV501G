package hbv501g.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
import hbv501g.objects.GameInput;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;
    //private HoleService holeService;
    @Autowired
    private UserService uService;
    
    @GetMapping("/")
    public JsonResponse<String> getGame(){
        return new JsonResponse<String>(true, "new string, coming through", null);
    }


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
    
}
