package dao;

import domain.Tweet;
import domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Stateless
@Default
public class TweetDAOImplLocal implements TweetDAO {

    public TweetDAOImplLocal() {
    }

    CopyOnWriteArrayList<Tweet> tweets = new CopyOnWriteArrayList<>();

    @Override
    public void addTweet(Tweet tweet) {

    }

    @Override
    public void removeTweet(Tweet tweet) {

    }

    @Override
    public List<Tweet> searchTweets(String searchText) {
        return null;
    }

    @Override
    public List<Tweet> getAllTweets() {
        return null;
    }
}
