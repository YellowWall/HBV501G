package hbv501g.Controllers;

import hbv501g.Classes.JsonResponse;
import hbv501g.Persistence.Entities.Game;
import hbv501g.Persistence.Entities.Hole;
import hbv501g.Services.GameService;
import hbv501g.Services.HoleService;
import hbv501g.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/hole")
public class HoleController {

    @Autowired
    private HoleService holeService;

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService uService;

    @GetMapping("/")
    public JsonResponse<List<Hole>> getAllHoles() {
        List<Hole> holes = holeService.findAll();

        if (holes != null) {
            return new JsonResponse<List<Hole>>(true, "Holes found", holes);
        }

        return new JsonResponse<List<Hole>>(false, "Holes not found", null);
    }

    @GetMapping("/{id}")
    public JsonResponse<Hole> getGame(@PathVariable Long id){
        Hole hole = holeService.findById(id);

        if (hole != null) {
            return new JsonResponse<Hole>(true, "Hole found", hole);
        }

        return new JsonResponse<Hole>(false, "Hole not found", null);
    }

    @PostMapping("/")
    public JsonResponse<Hole> postHole(@RequestBody Hole hole){
        Hole retHole = holeService.saveHole(hole);
        if (retHole == null){
            return new JsonResponse<Hole>(false, "something went wrong", null);

        }
        return new JsonResponse<Hole>(true, "Hole saved", retHole);
    }

    @DeleteMapping("/{id}")
    public JsonResponse<String> deleteGame(@RequestParam Long id){
        if(holeService.findById(id) == null){
            return new JsonResponse<String>(false, "Hole object missing or does not contain appropriate parameters", "");
        }

        holeService.deleteHole(id);
        Hole hole = holeService.findById(id);

        if(hole != null){
            return new JsonResponse<String>(false, "Hole not deleted", "");
        }

        return new JsonResponse<String>(true, "Hole deleted", "");
    }


}
