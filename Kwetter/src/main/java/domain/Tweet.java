package domain;

import Logic.HttpLogic;
import api.Link;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "tweet.searchTweets", query = "SELECT t FROM Tweet t WHERE t.tweetContent LIKE :searchContent"),
        @NamedQuery(name = "tweet.findByID", query = "SELECT t FROM Tweet t WHERE t.id = :id"),
        @NamedQuery(name = "tweet.findByPoster", query = "SELECT t FROM Tweet t WHERE t.poster = :poster")

})
public class Tweet implements Comparable<Tweet> {
    public Tweet() {
        mentions = new ArrayList<>();
        likedBy = new ArrayList<>();
        hashTagsUsed = new ArrayList<>();
        dateTime = new Date();
    }

    public Tweet(User poster, String content) {
        this.poster = poster;
        this.tweetContent = content;

        mentions = new ArrayList<>();
        likedBy = new ArrayList<>();
        hashTagsUsed = new ArrayList<>();
        dateTime = new Date();
    }

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private User poster;
    private String tweetContent;
    @JsonbTransient
    @OneToMany
    private List<User> mentions;
    @JsonbTransient
    @ManyToMany
    private List<User> likedBy;
    @JsonbTransient
    @ManyToMany(mappedBy = "tweetsUsingHashtag", cascade = CascadeType.PERSIST)
    private List<Hashtag> hashTagsUsed;

    private Date dateTime;


    public JsonObject toJson() {
        return Json.createObjectBuilder().
                add("id", this.id).
                add("poster", this.poster.toJson()).
                add("tweetContent", this.tweetContent).
                add("_links", buildRelations()).
                build();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }

    public String getTweetContent() {
        return tweetContent;
    }

    public void setTweetContent(String tweetContent) {
        this.tweetContent = tweetContent;
    }

    public List<User> getMentions() {
        return mentions;
    }

    public void setMentions(List<User> mentions) {
        this.mentions = mentions;
    }

    public List<User> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<User> likedBy) {
        this.likedBy = likedBy;
    }

    public List<Hashtag> getHashTagsUsed() {
        return hashTagsUsed;
    }

    public void setHashTagsUsed(List<Hashtag> hashTagsUsed) {
        this.hashTagsUsed = hashTagsUsed;
    }


    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }


    @Override
    public int compareTo(Tweet o) {
        return o.getDateTime().compareTo(getDateTime());
    }

    private String buildRelations() {
        List<Link> relLinks = new ArrayList<>();
        relLinks.add(new Link("self", "/Kwetter/api/tweets/" + id, "GET"));
        relLinks.add(new Link("poster", "/Kwetter/api/users/" + poster.getUsername(), "GET"));


        return relLinks.toString();
    }
}
