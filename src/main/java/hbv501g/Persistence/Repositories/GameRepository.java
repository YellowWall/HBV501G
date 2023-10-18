package hbv501g.Persistence.Repositories;

import hbv501g.Persistence.Entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long> {

    
}
