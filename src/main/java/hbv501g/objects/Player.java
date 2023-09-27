package hbv501g.objects;

public class Player{
    private final String userName;
    private String password = null;
    private boolean admin = false;
    private int userID;

    public Player(String username){
        this.userName = username; 
    }
    public void setId(int userid){
        this.userID = userid;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public int getUserID() {
        return userID;
    }
}