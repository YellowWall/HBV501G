package hbv501g.Persistence.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "field", schema = "public")
public class Field{
    @Id
    @GeneratedValue(generator = "field_generator",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "field_generator", sequenceName = "game_id_seq",allocationSize = 1)
    private long id;
    private String name;
    private String par;

    public Field(){};
    public Field(String name, String par){
        this.name = name;
        this.par = par;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPar() {
        return par;
    }
}