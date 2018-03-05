package service;

import dao.TweetDAO;
import domain.Tweet;
import domain.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TweetServiceTest {

    TweetService service;
    @Mock
    TweetDAO dao;
    @Mock
    UserService userService;

    User poster;
    Tweet tweet;

    @Before
    public void setUp() throws Exception {
        poster = new User("Youri","Bio", "Password");
        tweet = new Tweet(poster, "TestTweet");
        service = new TweetService();
        service.setTweetDAO(dao);
        service.setUserService(userService);
    }

    @Test
    public void addTweet() {
        service.addTweet(tweet);
        verify(dao, times(1)).addTweet(tweet);
    }

    @Test
    public void removeTweet() {
        service.removeTweet(tweet);
        verify(dao, times(1)).removeTweet(tweet);
    }

    @Test
    public void likeTweet() {
    }

    @Test
    public void getTweet() {
    }

    @Test
    public void searchTweets() {
    }

    @Test
    public void getAllTweets() {
    }
}