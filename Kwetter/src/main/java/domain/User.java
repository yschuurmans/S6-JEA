package domain;

import api.Link;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "user.authenticate", query = "SELECT s FROM User s WHERE s.username = :username AND s.password = :password"),
        @NamedQuery(name = "user.findByname", query = "SELECT s FROM User s WHERE s.username = :name"),
        @NamedQuery(name = "user.findByID", query = "SELECT s FROM User s WHERE s.id = :id")
})

public class User {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String username;
    private String profilePicture;
    @JsonbTransient
    private String password;
    private String salt;
    private String bio;

    @JoinTable(name = "User_PermissionGroup",
            joinColumns
                    = @JoinColumn(name = "Username", referencedColumnName = "username"),
            inverseJoinColumns
                    = @JoinColumn(name = "GroupName", referencedColumnName = "groupName")
    )
    @ManyToMany(cascade = {ALL}, fetch = FetchType.EAGER)
    private Collection<PermissionGroup> permissionGroups = new ArrayList<>();

    @JsonbTransient
    @ManyToMany(mappedBy = "likedBy", cascade = PERSIST)
    private List<Tweet> likes;

    @JsonbTransient
    @OneToMany(mappedBy = "poster", cascade = PERSIST)
    private List<Tweet> tweets;

    @JsonbTransient
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<User> followers;

    @JsonbTransient
    @ManyToMany(mappedBy = "followers")
    private List<User> following;

    public User(String username, String bio, String password) {
        likes = new ArrayList<>();
        tweets = new ArrayList<>();
        followers = new ArrayList<>();
        following = new ArrayList<>();

        this.username = username;
        this.bio = bio;
        this.password = password;
    }

    public User() {
        likes = new ArrayList<>();
        tweets = new ArrayList<>();
        followers = new ArrayList<>();
        following = new ArrayList<>();

    }

    public JsonObject toJson() {
        return Json.createObjectBuilder().
                add("id", this.id).
                add("username", this.username).
                add("bio", this.bio).
                add("profilePicture", this.profilePicture).
                add("_links", buildRelations()).
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

    public List<Tweet> recentTweets(int amount) {
        return tweets.subList(tweets.size() - amount, tweets.size());
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Collection<PermissionGroup> getPermissionGroups() {
        return permissionGroups;
    }

    public void setPermissionGroups(Collection<PermissionGroup> permissionGroups) {
        this.permissionGroups = permissionGroups;
    }

    public Role getPermissionGroup() {
        if (permissionGroups == null) permissionGroups = new ArrayList<>();
        if (permissionGroups.toArray().length <= 0) return Role.None;
        switch (((PermissionGroup) permissionGroups.toArray()[0]).getGroupName()) {
            case PermissionGroup.ADMIN_GROUP_NAME:
                return Role.Admin;
            case PermissionGroup.MODERATOR_GROUP_NAME:
                return Role.Moderator;
            case PermissionGroup.USER_GROUP_NAME:
                return Role.User;
            default:
                return Role.None;
        }
    }

    public void setPermissionGroup(Role newRole) {
        if (permissionGroups == null) permissionGroups = new ArrayList<>();
        permissionGroups.clear();
        switch (newRole) {
            case Admin:
                permissionGroups.add(PermissionGroup.ADMIN_GROUP);
                break;
            case Moderator:
                permissionGroups.add(PermissionGroup.MODERATOR_GROUP);
                break;
            case User:
                permissionGroups.add(PermissionGroup.USER_GROUP);
                break;
        }
    }

    private String buildRelations() {
        List<Link> relLinks = new ArrayList<>();
        relLinks.add(new Link("self", "/Kwetter/api/users/" + username, "GET"));
        relLinks.add(new Link("timeline", "/Kwetter/api/users/" + username + "/timeline", "GET"));
        relLinks.add(new Link("edit", "/Kwetter/api/users/" + username, "POST"));
        relLinks.add(new Link("delete", "/Kwetter/api/users/" + username, "DELETE"));
        relLinks.add(new Link("followers","/Kwetter/api/users/"+username+"/followers","GET"));
        relLinks.add(new Link("following","/Kwetter/api/users/"+username+"/following","GET"));
        relLinks.add(new Link("tweets","/Kwetter/api/users/"+username+"/tweets","GET"));
        relLinks.add(new Link("recenttweets","/Kwetter/api/users/"+username+"/recenttweets","GET"));
        relLinks.add(new Link("posttweet","/Kwetter/api/users/"+username+"/tweets","PUT"));
        relLinks.add(new Link("newfollower","/Kwetter/api/users/"+username+"/followers/{username}","PUT"));
        relLinks.add(new Link("removefollower","/Kwetter/api/users/"+username+"/followers/{username}","DELETE"));

        return relLinks.toString();
    }

}
