package hbv501g.Persistence.Entities;

import jakarta.persistence.*;


import java.util.Date;

@Entity
@Table(name = "game", schema = "public")
public class Game{
    @Id
    @GeneratedValue(generator = "game_generator",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "game_generator", sequenceName = "game_id_seq", allocationSize = 1)
    private long id;
    private long fieldId;
    private long playerId;
    private Date gameDate;
    private String score;

    public Game(){}

    public Game(Long playerId, Long fieldId, Date gamedate,String score){
        this.playerId = playerId;
        this.fieldId = fieldId;
        this.gameDate = gamedate;
        this.score = score;
    }

    public void setDate(Date date) {
        this.gameDate = date;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public long getFieldId() {
        return fieldId;
    }

    public long getPlayerId() {
        return playerId;
    }

    public Date getDate() {
        return gameDate;
    }

    public String getScore() {
        return score;
    }
    @Override
    public String toString(){
        String format = "fieldId: %x, playerId: %x, Score: %s";
        return String.format(format,this.fieldId,this.playerId,this.score);
    }
}