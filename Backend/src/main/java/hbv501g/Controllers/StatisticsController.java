package hbv501g.Controllers;

import hbv501g.Classes.JsonResponse;

import hbv501g.Classes.Stats;
import hbv501g.Persistence.Entities.Game;
import hbv501g.Persistence.Entities.Hole;
import hbv501g.Services.FieldService;
import hbv501g.Services.GameService;
import hbv501g.Services.HoleService;
import hbv501g.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public JsonResponse<Stats> getStats(@PathVariable long id) {
        Stats stats = new Stats();

        List<Hole> holes = holeService.findAllByUserId(id);
        List<Game> games = gameService.findALLByUserId(id);
        HashMap<Long, Integer> yeetsPerGame = new HashMap<>();


        int holeInOne = 0;
        double sumOfYeets = 0;

        for (Hole h: holes) {
            int y = h.getYeets();
            long gid = h.getGameId();

            if (y == 1) {
                holeInOne++;
            }

            sumOfYeets += y;
            int prevYeets = 0;

            if (yeetsPerGame.get(gid) != null)
                prevYeets = yeetsPerGame.get(gid);

            yeetsPerGame.put(gid,  prevYeets + y);
        }

        stats.setAverageScorePerHole(sumOfYeets / holes.size());
        stats.setNumberOfGamesPlayed(games.size());
        stats.setHolesInOne(holeInOne);

        HashMap<Long, ArrayList<Long>> sumYeetsAndNumPerField = new HashMap<>();

        for (Game g : games) {
            long fid = g.getFieldId();
            List<Hole> holesOfAGame = holeService.findAllByGameId(g.getId());
            long yeetsOfAGame = holesOfAGame
                                .stream()
                                .map(Hole::getYeets)
                                .reduce(0, Integer::sum);

            ArrayList<Long> values = new ArrayList<Long>();
            if (!sumYeetsAndNumPerField.containsKey(fid)) {
                values.add(yeetsOfAGame);
                values.add(1L); // sum of games played on a specific field.
                sumYeetsAndNumPerField.put(fid, values);
            }
            else {
                values = sumYeetsAndNumPerField.get(fid);
                values.set(0, values.get(0) + yeetsOfAGame);
                values.set(1, values.get(1) + 1);
                sumYeetsAndNumPerField.put(fid, values);
            }
        }
        
        HashMap<Long, Double> averageYeetsPerField = new HashMap<>();
        for (Map.Entry<Long, ArrayList<Long>> set : sumYeetsAndNumPerField.entrySet()) {
            double average = (double)(set.getValue().get(0) / set.getValue().get(1));
            averageYeetsPerField.put(set.getKey(), average);
        }

        stats.setAverageScorePerField(averageYeetsPerField);

        return new JsonResponse<Stats>(true, "Stats", stats);
    }

}
