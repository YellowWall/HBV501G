package hbv501g.objects;
public class Field{
    private long id;
    private String name;
    private String par;

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