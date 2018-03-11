package domain;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "hashtag.findHashtag", query = "SELECT h FROM Hashtag h WHERE h.hashtag LIKE :word"),
        @NamedQuery(name = "hashtag.searchHashtags", query = "SELECT h FROM Hashtag h WHERE h.hashtag LIKE :searchTerm")

})
public class Hashtag {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String hashtag;
    @JsonbTransient
    @ManyToMany
    private List<Tweet> tweetsUsingHashtag;


    public Hashtag() {
        tweetsUsingHashtag = new ArrayList<>();
    }

    public Hashtag(String hashtag) {
        this.hashtag = hashtag;
        tweetsUsingHashtag = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public List<Tweet> getTweetsUsingHashtag() {
        return tweetsUsingHashtag;
    }

    public void setTweetsUsingHashtag(List<Tweet> tweetsUsingHashtag) {
        this.tweetsUsingHashtag = tweetsUsingHashtag;
    }
}
