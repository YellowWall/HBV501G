package hbv501g.Persistence.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hbv501g.Persistence.Entities.Game;

public interface GameRepository extends JpaRepository<Game,Long> {

    
}
