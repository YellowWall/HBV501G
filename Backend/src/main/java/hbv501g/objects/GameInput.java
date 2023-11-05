package hbv501g.objects;

public class GameInput {
    private long field;
    private String username;
    public GameInput(long field, String username){
        this.field = field;
        this.username = username;
    }
    public long getFieldId(){
        return field;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setField(long field){
        this.field = field;
    }
}
