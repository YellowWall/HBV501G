package hbv501g.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hbv501g.Classes.JsonResponse;
import hbv501g.Persistence.Entities.Field;
import hbv501g.Services.FieldService;

@RestController
@RequestMapping("/field")
public class FieldController {
    @Autowired
    FieldService fieldservice;

    /**
     * Skilar öllum völlum sem eru skráðir
     * @return Lista yfir velli
     */
    @GetMapping("/fields")
    public JsonResponse<List<Field>> getAllFields(){
        //sækjum field úr fieldservice og skilum í JsonResponse
        return new JsonResponse<List<Field>>(false, "aðferð ekki útfærð", null);
    }
}
