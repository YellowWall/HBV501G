package hbv501g.Persistence.Entities;

import jakarta.persistence.*;


import java.util.Date;

@Entity
@Table(name = "game", schema = "public")
public class Game{
    @Id
    @GeneratedValue(generator = "game_generator",strategy = GenerationType.IDENTITY)
    private long id;
    private long fieldId;
    private long playerId;
    private Date date;
    private String score;

    public Game(){}

    public Game(Long playerId, Long fieldId, Date date,String score){
        this.playerId = playerId;
        this.fieldId = fieldId;
        this.date = date;
        this.score = score;
    }

    public void setDate(Date date) {
        this.date = date;
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
        return date;
    }

    public String getScore() {
        return score;
    }
    @Override
    public String toString(){
        String format = "fieldId: %l, playerId: %l, Score: %s";
        return String.format(format,this.fieldId,this.playerId,this.score);
    }
}