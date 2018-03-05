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

import java.util.ArrayList;
import java.util.List;

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
    @Mock
    HashtagService hashtagService;

    User poster;
    Tweet tweet;

    @Before
    public void setUp() throws Exception {
        poster = new User("Youri","Bio", "Password");
        tweet = new Tweet(poster, "TestTweet");
        tweet.setId(0);
        service = new TweetService();
        service.setTweetDAO(dao);
        service.setUserService(userService);
        service.setHashtagService(hashtagService);
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
        when(userService.findByName(poster.getUsername())).thenReturn(poster);
        when(dao.getTweet(tweet.getId())).thenReturn(tweet);
        service.likeTweet(tweet.getId(), poster.getUsername());
        verify(userService, times(1)).findByName(poster.getUsername());
        verify(userService, times(1)).editUser(poster);
        verify(dao, times(1)).editTweet(tweet);
    }

    @Test
    public void getTweet() {
        when(dao.getTweet(tweet.getId())).thenReturn(tweet);
        assertEquals(service.getTweet(tweet.getId()), tweet);
        verify(dao, times(1)).getTweet(tweet.getId());
    }

    @Test
    public void searchTweets() {
        List<Tweet> searchResults = new ArrayList<>();
        searchResults.add(tweet);
        when(dao.searchTweets("Test")).thenReturn(searchResults);
        assertEquals(service.searchTweets("Test").size(), 1);
        verify(dao, times(1)).searchTweets("Test");
    }

    @Test
    public void getAllTweets() {
        List<Tweet> alltweets = new ArrayList<>();
        alltweets.add(tweet);
        when(dao.getAllTweets()).thenReturn(alltweets);
        assertEquals(service.getAllTweets().size(), 1);
        verify(dao, times(1)).getAllTweets();
    }
}