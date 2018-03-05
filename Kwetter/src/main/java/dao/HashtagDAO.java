package dao;

import domain.Hashtag;

import java.util.List;

public interface HashtagDAO {
    void addHashTag(Hashtag hashtag);
    void removeHashTag(Hashtag hashtag);
    Hashtag findHashtag(String hashtagName);
    List<Hashtag> getAllHashtags();

    List<Hashtag> searchHashtags(String searchTerm);

    boolean editHashtag(Hashtag hashtag);
}
