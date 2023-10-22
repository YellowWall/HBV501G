package hbv501g.Services;

import hbv501g.Persistence.Repositories.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbv501g.Persistence.Entities.Field;

import java.util.List;
@Service
public class FieldService {
    @Autowired
    private FieldRepository fieldRepository;

    public List<Field> getAllFields(){
        List<Field> fields = fieldRepository.findAll();
        return fields;
    }

    public Field getFieldName(String name){
        Field field = fieldRepository.findByName(name);
        return field;
    }

    public Field getFieldId(Long id){
        var fieldId = fieldRepository.findById(id);
        Field field = fieldId.get();
        return field;
    }

    public Field saveField(Field field){
        return fieldRepository.save(field);
    }



}
