package hbv501g.objects;

public class Forumpost{
    private long id;
    private long playerId;
    private long parentPostId;

    private final String title;
    private String text;

    public Forumpost(String title, String text, Long playerId){
        this.title = title;
        this.text = text;
        this.playerId = playerId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setText(String text){
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public long getPlayerId() {
        return playerId;
    }

    public long getParentPostId() {
        return parentPostId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}