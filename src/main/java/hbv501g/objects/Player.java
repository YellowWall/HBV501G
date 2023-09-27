package hbv501g.objects;

public class Player{
    String userName;
    String password = null;
    boolean admin = false;
    int userId;

    public Player(String username){
        this.userName = username; 
    }
    public void setId(int userid){
        this.userId = userid;
    }

}