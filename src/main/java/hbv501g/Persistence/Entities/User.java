package hbv501g.Persistence.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user", schema = "public")
public class User {
    @Id
    @GeneratedValue(generator = "user_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_generator", sequenceName = "user_id_seq", allocationSize = 1)
    private long id;
    private String username;
    private String password;
    private String salt;
    private String name;

    public User() {}

    public User(String username, String password, String salt, String name) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.name = name;
    }

    public long getId(){
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String format = "name: %s, username: %s, password: %s, salt: %s";
        return String.format(format, this.name, this.username, this.password, this.salt);
    }
}
