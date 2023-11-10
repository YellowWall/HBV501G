package hbv501g.Persistence.Repositories;

import hbv501g.Persistence.Entities.Game;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface GameRepository extends JpaRepository<Game,Long> {

    public List<Game> findByPlayerId(long playerId);
    public Game findById(long id);

    @Transactional
    public void deleteById(long id);
}
