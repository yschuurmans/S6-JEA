package dao;

import domain.Hashtag;
import domain.Tweet;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
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
        hashtags.add(hashtag);
    }

    @Override
    public void removeHashTag(Hashtag hashtag) {
hashtags.remove(hashtag);
    }

    @Override
    public Hashtag findHashtag(String hashtagName) {
        for (Hashtag hashtag : hashtags) {
            if(hashtag.getHashtag().contentEquals(hashtagName))
                return hashtag;
        }
        return null;
    }

    @Override
    public List<Hashtag> getAllHashtags() {
        return hashtags;
    }

    @Override
    public List<Hashtag> searchHashtags(String searchTerm) {
        List<Hashtag> returnList = new ArrayList<>();
        for (Hashtag hashtag : hashtags) {
            if(hashtag.getHashtag().startsWith(searchTerm))
                returnList.add(hashtag);
        }
        return returnList;
    }
}
