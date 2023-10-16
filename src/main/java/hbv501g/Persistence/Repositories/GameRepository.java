package hbv501g.Persistence.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hbv501g.objects.Game;

public interface GameRepository extends JpaRepository<Game,Long> {
    
    
}
