package hbv501g.objects;

import java.util.List;
import java.util.Date;
import hbv501g.objects.ReturnHole;
import hbv501g.Persistence.Entities.User;
import hbv501g.Persistence.Entities.Game;
import hbv501g.Persistence.Entities.Field;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * leikhlutur sem er skilað þegar beðið er um leik
 */
public class ReturnGame {    
    private long id;
    private String fieldName;
    private String username;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date gameDate;
    private String score;
    
    public ReturnGame(User user,Game game,Field field){
        this.id = game.getId();
        this.gameDate = game.getDate();
        this.score = game.getScore();
        if(field != null)this.fieldName = field.getName();
        else this.fieldName = null;
        if(user != null) this.username = user.getUsername();
        else this.username = null;
    }
    public long getId(){
        return id;
    }
    public String getFieldName(){
        return fieldName;
    }
    public String getUsername(){
        return username;
    }
    public Date getDate(){
        return gameDate;
    }
    public String getScore(){
        return score;
    }

    
}
