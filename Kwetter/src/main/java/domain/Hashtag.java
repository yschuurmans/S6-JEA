package domain;

import javax.persistence.*;
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
    @ManyToMany
    private List<Tweet> tweetsUsingHashtag;
}
