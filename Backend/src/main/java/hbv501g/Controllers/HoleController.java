package hbv501g.Controllers;

import hbv501g.Classes.JsonResponse;
import hbv501g.Persistence.Entities.Game;
import hbv501g.Persistence.Entities.Hole;
import hbv501g.Persistence.Entities.User;
import hbv501g.objects.ReturnHole;
import hbv501g.objects.HoleInput;
import hbv501g.Services.GameService;
import hbv501g.Services.HoleService;
import hbv501g.Services.UserService;
import hbv501g.Services.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Style;

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

    @Autowired
    private FieldService fService;

    @GetMapping("/")
    public JsonResponse<List<Hole>> getAllHoles() {
        List<Hole> holes = holeService.findAll();

        if (holes != null) {
            return new JsonResponse<List<Hole>>(true, "Holes found", holes);
        }

        return new JsonResponse<List<Hole>>(false, "Holes not found", null);
    }

    @GetMapping("/displayholes/{id}")
    public JsonResponse<List<ReturnHole>> getReturnHoles(@PathVariable Long id){
        List<Hole> holes = holeService.findAllByGameId(id);
        if( holes != null){
            List<ReturnHole> retholes = new ArrayList<ReturnHole>();
            for(Hole hole: holes){
                User user = uService.getUserById(hole.getPlayerId());
                String username = null;
                if(user != null){
                    username = user.getUsername();
                }
                ReturnHole temp = new ReturnHole(hole,username);
                retholes.add(temp);
            }
            return new JsonResponse<List<ReturnHole>>(true,"returnHoles returned",retholes);
        }
        return new JsonResponse<List<ReturnHole>>(false,"No holes found",null);
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
    @PostMapping("/postform")
    public JsonResponse<Hole> postHole(@RequestBody HoleInput holeInput){
        System.out.println(holeInput.getUsername());
        User user = uService.getUser(holeInput.getUsername());
        System.out.println(holeInput.getGameId());
        Hole hole = new Hole(holeInput.getGameId(),user.getId(),holeInput.getYeets());
        System.out.println(holeInput.getYeets());
        Hole rethole = holeService.saveHole(hole);
        if(rethole==null){
            return new JsonResponse<Hole>(false,"something went wrong",null);
        }
        return new JsonResponse<Hole>(true,"hole saved",rethole);
    }
    @PatchMapping("/updateScore")
    public JsonResponse<Hole> postFormHole(@RequestBody HoleInput holeInput){
        Hole hole = holeService.findById(holeInput.getId());
        hole.setYeets(holeInput.getYeets());
        Hole rethole = holeService.saveHole(hole);
        if(rethole== null){
            return new JsonResponse<Hole>(false,"Something went wrong",null);
        }
        return new JsonResponse<Hole>(true,"hole updated",rethole);
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
