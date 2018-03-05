package domain;

import Logic.HttpLogic;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.annotation.JsonbAnnotation;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Request;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "user.findByname", query = "SELECT s FROM User s WHERE s.username = :name"),
        @NamedQuery(name = "user.findByID", query = "SELECT s FROM User s WHERE s.id = :id")
})

public class User {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String username;
    private String bio;
    private String password;
    private String salt;
    @ManyToMany(mappedBy = "likedBy", cascade = CascadeType.PERSIST)
    private List<Tweet> likes;

    @OneToMany(mappedBy = "poster", cascade = CascadeType.PERSIST)
    private List<Tweet> tweets;

    @JsonbTransient
    @ManyToMany
    private List<User> followers;

    @JsonbTransient
    @ManyToMany(mappedBy = "followers", cascade = CascadeType.PERSIST)
    private List<User> following;

    public User(String username, String bio, String password) {
        this.username = username;
        this.bio = bio;
        this.password = password;

        likes = new ArrayList<>();
        tweets = new ArrayList<>();
        followers = new ArrayList<>();
        following = new ArrayList<>();
    }

    public User() {
    }

    public JsonObject toJson(HttpServletRequest request) {
        return Json.createObjectBuilder().
                add("id", this.id).
                add("username", this.username).
                add("bio", this.bio).
                add("tweets", HttpLogic.getResourceUrl(request,"/users/"+this.username+"/tweets")).
                add("likes", HttpLogic.getResourceUrl(request,"/users/"+this.username+"/liked")).
                build();
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

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public void addFollower(User follower) {
        followers.add(follower);
    }

    public void addFollowing(User toFollow) {
        following.add(toFollow);
    }

    public void removeFollower(User follower) {
        followers.remove(follower);
    }

    public void removeFollowing(User toUnfollow) {
        following.remove(toUnfollow);
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}
