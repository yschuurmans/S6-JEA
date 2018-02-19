package domain;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "user.findByname", query = "SELECT s FROM User s WHERE s.username = :name")})

public class User {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String username;
    private String bio;
    private String password;
    private String salt;
    @ManyToMany(mappedBy = "likedBy")
    private List<Tweet> likes;

    public User(String username, String bio, String password) {
        this.username = username;
        this.bio = bio;
        this.password = password;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public List<Tweet> getLikes() {
        return likes;
    }

    public void setLikes(List<Tweet> likes) {
        this.likes = likes;
    }
}
