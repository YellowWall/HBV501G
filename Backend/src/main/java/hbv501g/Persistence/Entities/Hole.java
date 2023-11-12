package hbv501g.Persistence.Entities;

import jakarta.persistence.*;


@Entity
@Table(name = "hole", schema = "public")
public class Hole{

    @Id
    @GeneratedValue(generator = "hole_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "hole_generator", sequenceName = "hole_id_seq", allocationSize = 1)
    private long id;
    private long gameId;
    private long playerId;
    private int yeets; // throws má ekki vera því það er java function til að kasta villu

    public Hole(){}
    public Hole(Long gameID, Long playerId, int score){
        this.gameId = gameID;
        this.playerId = playerId;
        this.yeets = score;
    }

    public long getId() {
        return id;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public void setYeets(int yeets) {
        this.yeets = yeets;
    }

    public long getGameId() {
        return gameId;
    }

    public long getPlayerId() {
        return playerId;
    }

    public int getYeets() {
        return yeets;
    }

    @Override
    public String toString(){
        String format = "gameID: %x, playerId: %x, Score: %s";
        return String.format(format,this.gameId,this.playerId,this.yeets);
    }
}