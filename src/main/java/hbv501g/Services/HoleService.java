package hbv501g.Services;

import hbv501g.Persistence.Repositories.HoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hbv501g.Persistence.Entities.Hole;

import java.util.List;

@Service
public class HoleService {
    @Autowired
    private HoleRepository holeRepository;

    public Hole saveHole(Hole hole){
        return holeRepository.save(hole);
    }
    public List<Hole> getAllHoles(){
        List<Hole> holes = holeRepository.findAll();
        return holes;
    }

    public void deleteHole(Hole hole){
        holeRepository.deleteById(hole.getId());
    }
}
