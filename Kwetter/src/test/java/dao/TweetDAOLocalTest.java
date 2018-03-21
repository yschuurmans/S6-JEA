package dao;

import domain.Tweet;
import domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class TweetDAOLocalTest {
    @Test
    public void addTweet() {
        User user = new User("Youri", "bio", "password");
        TweetDAO tweetDAO = new TweetDAOImplLocal();
        Tweet tw1 = new Tweet(user, "testTweet1");
        Tweet tw2 = new Tweet(user, "testTweet2");

        tweetDAO.addTweet(tw1);
        tweetDAO.addTweet(tw2);

        assertEquals(tweetDAO.getAllTweets().size(),2);

        assertEquals(tweetDAO.searchTweets("testTweet1").get(0), tw1);
        assertEquals(tweetDAO.searchTweets("testTweet2").get(0), tw2);
    }

    @Test
    public void removeTweet() {
        User user = new User("Youri", "bio", "password");
        TweetDAO tweetDAO = new TweetDAOImplLocal();
        Tweet tw1 = new Tweet(user, "testTweet1");
        Tweet tw2 = new Tweet(user, "testTweet2");

        tweetDAO.addTweet(tw1);
        tweetDAO.addTweet(tw2);

        assertEquals(tweetDAO.getAllTweets().size(),2);

        assertEquals(tweetDAO.searchTweets("testTweet1").get(0), tw1);
        assertEquals(tweetDAO.searchTweets("testTweet2").get(0), tw2);

        tweetDAO.removeTweet(tw1);

        assertEquals(tweetDAO.getAllTweets().size(),1);

        tweetDAO.removeTweet(tw2);

        assertEquals(tweetDAO.getAllTweets().size(),0);
    }

    @Test
    public void searchTweets() {
        User user = new User("Youri", "bio", "password");
        TweetDAO tweetDAO = new TweetDAOImplLocal();
        Tweet tw1 = new Tweet(user, "testTweet1");
        Tweet tw2 = new Tweet(user, "testTweet2");
        Tweet tw3 = new Tweet(user, "test123");
        Tweet tw4 = new Tweet(user, "tttest136");

        tweetDAO.addTweet(tw1);
        tweetDAO.addTweet(tw2);
        tweetDAO.addTweet(tw3);
        tweetDAO.addTweet(tw4);

        assertEquals(tweetDAO.searchTweets("testTweet").size(), 2);
        assertEquals(tweetDAO.searchTweets("test").size(), 4);
        assertEquals(tweetDAO.searchTweets("test1").size(), 2);
    }

    @Test
    public void getAllTweets() {
        User user = new User("Youri", "bio", "password");
        TweetDAO tweetDAO = new TweetDAOImplLocal();
        Tweet tw1 = new Tweet(user, "testTweet1");
        Tweet tw2 = new Tweet(user, "testTweet2");
        Tweet tw3 = new Tweet(user, "test123");
        Tweet tw4 = new Tweet(user, "tttest136");

        assertEquals(tweetDAO.getAllTweets().size(),0);
        tweetDAO.addTweet(tw1);
        assertEquals(tweetDAO.getAllTweets().size(),1);
        tweetDAO.addTweet(tw2);
        assertEquals(tweetDAO.getAllTweets().size(),2);
        tweetDAO.addTweet(tw1);
        assertEquals(tweetDAO.getAllTweets().size(),3);
        tweetDAO.addTweet(tw2);
        assertEquals(tweetDAO.getAllTweets().size(),4);
    }

    @Test
    public void getTweet() {
        User user = new User("Youri", "bio", "password");
        TweetDAO tweetDAO = new TweetDAOImplLocal();
        Tweet tw1 = new Tweet(user, "testTweet1");
        Tweet tw2 = new Tweet(user, "testTweet2");

        tweetDAO.addTweet(tw1);
        tweetDAO.addTweet(tw2);

        Tweet tw1Copy =tweetDAO.searchTweets("testTweet1").get(0);
        Tweet tw2Copy =tweetDAO.searchTweets("testTweet2").get(0);

        assertEquals(tweetDAO.getTweet(tw1Copy.getId()), tw1Copy);
        assertEquals(tweetDAO.getTweet(tw2Copy.getId()), tw2Copy);
    }
}