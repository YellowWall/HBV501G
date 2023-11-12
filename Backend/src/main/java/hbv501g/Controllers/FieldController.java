package hbv501g.Controllers;

import hbv501g.Classes.JsonResponse;
import hbv501g.Persistence.Entities.Field;
import hbv501g.Services.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    /**
     * Vistar völlinn og skilar honum eftir að hann hefur verið vistaður
     * @param field Völlurinn sem á að vista par er strengur talna fyrir par holana
     * @return Völlurinn eftir að hann er vistaður
     */
    @PostMapping("/postfield")
    public JsonResponse<Field> postField(@RequestBody Field field){
        Field savedField = fieldservice.saveField(field);
        return new JsonResponse<Field>(true, "Field saved", savedField);
    }
    //sækja upplýsingar um field

    @PostMapping("/fieldbyname")
    public JsonResponse<Field> getFieldByName(@RequestBody String name){
        System.out.println(name);
        Field returnField = fieldservice.getFieldName(name);
        if (returnField != null) {
            return new JsonResponse<>(true, "Field found", returnField);
        }
        return new JsonResponse<>(false, "Field not found", null);
    }

}
