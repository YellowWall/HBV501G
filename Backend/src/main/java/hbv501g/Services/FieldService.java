package hbv501g.Services;


import hbv501g.Persistence.Entities.Field;
import hbv501g.Persistence.Repositories.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbv501g.Persistence.Entities.Field;
import java.util.List;
import java.util.Optional;

@Service
public class FieldService {
    @Autowired
    private FieldRepository fieldRepository;



    public Field getFieldName(String name){
        Field field = fieldRepository.findByName(name);
        return field;
    }

    public Field getFieldId(Long id){
        Optional<Field> field = null;
        field = fieldRepository.findById(id);
        if(field.isPresent())return field.get();
        return null;
    }

    public Field saveField(Field field){
        return fieldRepository.save(field);
    }


    /**
     * Skilar öllum völlum sem finnast
     * @return Listi af völlum sem finnast
     */
    public List<Field> getFields() {
        List<Field> fields = fieldRepository.findAll();
        return fields;
    }
}
