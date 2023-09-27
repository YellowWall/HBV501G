package hbv501g.objects;

public class Hole{
    private long gameId;
    private long playerId;
    private int yeets; // throws má ekki vera því það er java function til að kasta villu

    public Hole(Long gameID, Long playerId, int score){
        this.gameId = gameID;
        this.playerId = playerId;
        this.yeets = score;
    }

    public long getGameId() {
        return gameId;
    }

    public long getPlayerId() {
        return playerId;
    }

    public int getScore() {
        return yeets;
    }

    public void setScore(int score) {
        this.yeets = score;
    }
}