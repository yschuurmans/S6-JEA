package service;

import dao.TweetDAO;
import domain.Tweet;

import javax.inject.Inject;

public class TweetService {

    @Inject
    private TweetDAO tweetDAO;

    public TweetService(){

    }

    public void addTweet(Tweet tweet){}

    public void removeTweet() {}
}
