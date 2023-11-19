package hbv501g.Persistence.Entities;

import jakarta.persistence.*;
import java.util.Date;

import hbv501g.objects.ForumInput;

@Entity
@Table(name = "forum",schema="public")
public class Forumpost{
    @Id
    @GeneratedValue(generator = "forum_generator",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "forum_generator", sequenceName = "forum_id_seq",allocationSize = 1)
    private long id;
    private long playerId;
    private long parentPostId;
    private Date postDate;
    private Date updatePost;

    private String title;
    private String text;

    public Forumpost(){}

    public Forumpost(String title, String text, Long playerId,Long parentPostId){
        this.title = title;
        this.text = text;
        this.playerId = playerId;
        this.parentPostId = parentPostId;
    }
    public Forumpost(ForumInput thatpost,Long playerId){
        this.title = thatpost.getTitle();
        this.text = thatpost.getText();
        this.playerId = playerId;
        this.parentPostId = thatpost.getParentPostId();
        Date current = new Date();
        this.postDate = current;
        this.updatePost = current;
        
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
    public void setParentPostId(long id){
        this.parentPostId = id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
    public Date getPostDate(){
        return postDate;
    }
    public Date getUpdatePost(){
        return updatePost;
    }
    public void update(){
        this.updatePost = new Date();
    }
}