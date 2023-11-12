package hbv501g.Controllers;

import hbv501g.Classes.JsonResponse;
import hbv501g.Persistence.Entities.Field;
import hbv501g.Services.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/field")
public class FieldController {
    @Autowired
    private FieldService fieldservice;

    /**
     * Skilar öllum völlum sem eru skráðir
     * @return Lista yfir velli
     */
    @GetMapping("/fields")
    public JsonResponse<List<Field>> getAllFields(){
        //sækjum field úr fieldservice og skilum í JsonResponse
        List<Field> fields =fieldservice.getFields();
        if (fields.size() == 1){
            return new JsonResponse<List<Field>>(true, "1 field found", fields);
        }
        String format = "%d fields found";
        String message = String.format(format,fields.size());
        return new JsonResponse<List<Field>>(true, message, fields);
    }

    //setja inn nýjan field

    //sækja upplýsingar um field

    //sækja öll field
}
