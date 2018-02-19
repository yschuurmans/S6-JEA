package domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.List;

@Entity
public class Tweet {
    @Id
    @GeneratedValue
    private long id;
    private String tweetContent;
    @ManyToMany
    private List<User> likedBy;
    @ManyToMany(mappedBy = "tweetsUsingHashtag")
    private List<Hashtag> hashTagsUsed;
}
