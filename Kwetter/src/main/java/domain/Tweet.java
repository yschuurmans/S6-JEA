package domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "tweet.searchTweets", query = "SELECT t FROM Tweet t WHERE t.tweetContent LIKE :searchContent"),
        @NamedQuery(name = "tweet.findByID", query = "SELECT t FROM Tweet t WHERE t.id = :id")
})
public class Tweet {
    public Tweet() {
    }

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private User poster;
    private String tweetContent;
    @OneToMany
    public List<User> mentions;
    @ManyToMany
    private List<User> likedBy;
    @ManyToMany(mappedBy = "tweetsUsingHashtag")
    private List<Hashtag> hashTagsUsed;



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
}
