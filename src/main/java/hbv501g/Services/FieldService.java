package hbv501g.Services;

import hbv501g.Persistence.Repositories.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldService {
    @Autowired
    private FieldRepository fieldRepository;

    public List<Field> getAllFields(){
        List<Field> fields = fieldRepository.findAll();
        return fields;
    }

    public Field getField(String fieldname){
        Field field = fieldRepository.findByFieldname();
        return field;
    }

    public Field saveField(Field field){
        return fieldRepository.save(field);
    }



}
