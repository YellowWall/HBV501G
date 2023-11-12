package hbv501g.Controllers;

import hbv501g.Classes.JsonResponse;

import hbv501g.Classes.Stats;
import hbv501g.Persistence.Entities.Hole;
import hbv501g.Services.FieldService;
import hbv501g.Services.GameService;
import hbv501g.Services.HoleService;
import hbv501g.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/stats")
public class StatisticsController {
    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;
    @Autowired
    private FieldService fieldService;
    @Autowired
    private HoleService holeService;

    @GetMapping("/{id}")
    public JsonResponse<Stats> getStats(@RequestParam long id) {
        Stats stats = new Stats();

        List<Hole> holes = holeService.findAllByUserId(id);

        int holeInOne = 0;
        for (Hole h: holes) {
            if (h.getYeets() == 1) {
                holeInOne++;
            }
        }

        stats.setHolesInOne(holeInOne);

        return new JsonResponse<Stats>(true, "Stats", stats);
    }

}
