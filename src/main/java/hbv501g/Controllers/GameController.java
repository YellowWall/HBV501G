package hbv501g.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {
    private GameService gameService;
    private HoleService holeService;

    @GetMapping(value = "") //her vantar að útfæra hvernig playerID er fengið
        public getPosts(@PathVariable Long playerID){

    }
}
