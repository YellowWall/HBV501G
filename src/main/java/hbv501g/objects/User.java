package hbv501g.objects;

public class User{
    String userMame;
    String password = null;
    boolean admin = false;
    int userId;

    public User(String username){
        this.userName = username; 
    }
    public setId(int userid){
        this.userId = userid;
    }

}