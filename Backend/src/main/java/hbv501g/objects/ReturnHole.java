package hbv501g.objects;
import hbv501g.Persistence.Entities.Hole;
/**
 * hlutur sem er skilað þegar beðið er um holuupplýsingar
 */
public class ReturnHole {
    private long id;
    private String username;
    private int yeets;

    public ReturnHole(Hole hole,String username){
        this.id = hole.getId();
        this.username = username;
        this.yeets = hole.getYeets();
    }
    public long getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public int getYeets(){
        return yeets;
    }
}
