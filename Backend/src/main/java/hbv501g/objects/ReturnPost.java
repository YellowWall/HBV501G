package hbv501g.objects;

public class ReturnPost{
    private long id;
    private long parentPostId;
    private String username;
    private final String title;
    private String text;


    /**
     * create new post to return to front end
     * @param title title of post
     * @param text contents of post
     * @param id id of post
     * @param parentPostId id of parentpost
     * @param username username of poster
     */
    public ReturnPost(String title, String text, long id, long parentPostId, String username){
        this.title = title;
        this.text = text;
        this.id = id;
        this.username = username;
        this.parentPostId = parentPostId;
    }

    public long getId() {
        return id;
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
    public String getUsername(){
        return username;
    }
}