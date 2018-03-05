package dao;

import domain.Tweet;
import domain.User;

import java.util.List;

public interface TweetDAO {

    void addTweet(Tweet tweet);
    void removeTweet(Tweet tweet);
    List<Tweet> searchTweets(String searchText);
    List<Tweet> getAllTweets();

    Tweet getTweet(long id);

    boolean editTweet(Tweet likedTweet);
}
