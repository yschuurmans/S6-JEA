package service;

import dao.TweetDAO;
import domain.Tweet;

import javax.inject.Inject;
import java.util.List;

public class TweetService {

    @Inject
    private TweetDAO tweetDAO;

    public TweetService(){

    }

    public void addTweet(Tweet tweet){tweetDAO.addTweet(tweet);}

    public void removeTweet(Tweet tweet) {tweetDAO.removeTweet(tweet);}

    public List<Tweet> searchTweets(String seartText) {return tweetDAO.searchTweets(seartText);}

    public List<Tweet> getAllTweets() {return tweetDAO.getAllTweets();}
}
