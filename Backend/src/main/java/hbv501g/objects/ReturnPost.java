package hbv501g.objects;

import java.util.Date;

import hbv501g.Persistence.Entities.Forumpost;

public class ReturnPost{
    private long id;
    private long parentPostId;
    private String username;
    private final String title;
    private String text;
    private Date postDate;
    private Date updatePost;
    private boolean updated = false;

    /**
     * create new post to return to front end
     * @param thatpost Forumpost objec to be converted to return post
     * @param username username of poster
     */
    public ReturnPost(Forumpost thatpost,String username){
        this.title = thatpost.getTitle();
        this.text = thatpost.getText();
        this.id = thatpost.getId();
        this.username = username;
        this.postDate = thatpost.getPostDate();
        this.updatePost = thatpost.getUpdatePost();
    }
    public Date getPostDate(){
        return this.postDate;
    };
    public Date getUpdatePost(){
        return this.updatePost;
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