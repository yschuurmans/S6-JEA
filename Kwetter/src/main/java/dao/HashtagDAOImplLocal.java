package dao;

import domain.Hashtag;
import domain.Tweet;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Stateless
@Default
public class HashtagDAOImplLocal implements HashtagDAO{
    public HashtagDAOImplLocal() {
    }

    CopyOnWriteArrayList<Hashtag> hashtags = new CopyOnWriteArrayList<>();

    @Override
    public void addHashTag(Hashtag hashtag) {

    }

    @Override
    public void removeHashTag(Hashtag hashtag) {

    }

    @Override
    public Hashtag findHashtag(String hashtagName) {
        return null;
    }

    @Override
    public List<Hashtag> getAllHashtags() {
        return null;
    }

    @Override
    public List<Hashtag> searchHashtags(String searchTerm) {
        return null;
    }
}
