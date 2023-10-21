package hbv501g.Services;

import hbv501g.Persistence.Entities.Field;
import hbv501g.Persistence.Repositories.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {
    @Autowired
    private FieldRepository fieldRepository;

    /**
     * Skilar öllum völlum sem finnast
     * @return Listi af völlum sem finnast
     */
    public List<Field> getFields() {
        List<Field> fields = fieldRepository.findAll();
        return fields;
    }
}
