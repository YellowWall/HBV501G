package hbv501g.Persistence.Repositories;

import hbv501g.Persistence.Entities.Hole;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;



public interface HoleRepository extends JpaRepository<Hole,Long> {
    public List<Hole> findByGameId(long gameId);
    public List<Hole> findByPlayerId(long playerId);
    //verð að finna út úr findbyplayerandgame
    //public List<Hole> findByPlayerIdandGameId(long playerId,long gameId);
    @Transactional
    public void deleteByGameId(long gameId);

}
