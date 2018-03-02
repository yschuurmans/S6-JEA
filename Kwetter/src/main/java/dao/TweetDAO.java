package dao;

import domain.Tweet;

import java.util.List;

public interface TweetDAO {

    void addTweet(Tweet tweet);
    void removeTweet(Tweet tweet);
    List<Tweet> searchTweets(String searchText);
    List<Tweet> getAllTweets();
    Tweet findTweetByID(int id);
}
