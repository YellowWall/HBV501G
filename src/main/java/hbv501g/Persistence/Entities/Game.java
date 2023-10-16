package hbv501g.Persistence.Entities;

import jakarta.persistence.*;


import java.util.Date;

@Entity
@Table(name = "game", schema = "public")
public class Game{
    @Id
    @GeneratedValue(generator = "game_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "game_generator", sequenceName = "game_id_seq", allocationSize = 1)
    private long id;
    private long fieldId;
    private long playerId;
    private Date date;
    private String score;

    public Game(Long playerId, Long fieldId){
        this.playerId = playerId;
        this.fieldId = fieldId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(long id) {
        this.id = id;
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
}