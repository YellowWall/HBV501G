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

    public List<Hole> findAll() { return holeRepository.findAll(); }

    public List<Hole> findAllByUserId(long id) { return holeRepository.findByPlayerId(id); }

    public List<Hole> findAllByGameId(Long gameId){ return holeRepository.findByGameId(gameId); }

    public Hole findById(long id){
        return holeRepository.findById(id);
    }

    public Hole saveHole(Hole hole){
        return holeRepository.save(hole);
    }
    public List<Hole> getAllHoles(){
        List<Hole> holes = holeRepository.findAll();
        return holes;
    }

    public void deleteHole(long id){
        holeRepository.deleteById(id);
    }
    public void deleteGameHoles(Long gameId){
        holeRepository.deleteByGameId(gameId);
    }
}
