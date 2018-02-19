package domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Hashtag {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String hashtag;
    @ManyToMany
    private List<Tweet> tweetsUsingHashtag;
}
