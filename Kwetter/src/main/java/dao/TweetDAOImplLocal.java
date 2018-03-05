package dao;

import domain.Tweet;
import domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
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
        tweets.add(tweet);
    }

    @Override
    public void removeTweet(Tweet tweet) {
        tweets.remove(tweet);
    }

    @Override
    public List<Tweet> searchTweets(String searchText) {
        List<Tweet> matchingTweets = new ArrayList();
        for (Tweet tweet : tweets) {
            if (tweet.getTweetContent().toLowerCase().contains(searchText.toLowerCase())) {
                matchingTweets.add(tweet);
            }
        }
        return matchingTweets;
    }

    @Override
    public List<Tweet> getAllTweets() {
        return tweets;
    }

    @Override
    public Tweet getTweet(long id) {
        for (Tweet tweet : tweets) {
            if (tweet.getId() == id) {
                return tweet;
            }
        }
        return null;
    }

    @Override
    public boolean editTweet(Tweet likedTweet) {
        for (int i = 0; i < tweets.size(); i++) {
            if (tweets.get(i).getId() == likedTweet.getId()) {
                tweets.set(i, likedTweet);
                return true;
            }
        }
        return false;
    }
}
