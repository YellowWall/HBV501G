package hbv501g.objects;

public class ForumInput {
    private long id;
    private long parentPostId;
    private String username;
    private final String title;
    private String text;

    public ForumInput(long id, long parentPostId,String username, String title, String text){
        this.title = title;
        this.text = text;
        this.id = id;
        this.username = username;
        this.parentPostId = parentPostId;
    }
    public long getId() {
        return this.id;
    }
    public void setParentPostId(Long ppid) {
        this.parentPostId = ppid;
    }

    public long getParentPostId() {
        return this.parentPostId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
    }
    public String getUsername(){
        return this.username;
    }
    
}
