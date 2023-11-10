package hbv501g.Controllers;

import hbv501g.Classes.JsonResponse;

import hbv501g.Services.FieldService;
import hbv501g.Services.GameService;
import hbv501g.Services.HoleService;
import hbv501g.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
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

}
