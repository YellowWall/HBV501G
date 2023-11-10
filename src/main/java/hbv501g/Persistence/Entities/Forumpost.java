package hbv501g.Persistence.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "forum",schema="public")
public class Forumpost{
    @Id
    @GeneratedValue(generator = "forum_generator",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "forum_generator", sequenceName = "forum_id_seq",allocationSize = 1)
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