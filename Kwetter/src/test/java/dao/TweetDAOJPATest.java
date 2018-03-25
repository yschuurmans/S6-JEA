package dao;

import domain.Tweet;
import domain.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class TweetDAOJPATest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterTest");
    private EntityManager em;
    private EntityTransaction tx;
    private TweetDAOImplJPA tweetDAO;
    private UserDAOImplJPA userDAO;

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(HashtagDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        tweetDAO = new TweetDAOImplJPA();
        userDAO = new UserDAOImplJPA();
        tweetDAO.setEm(em);
        userDAO.setEm(em);
    }

    @AfterClass
    public void breakDown() {
        setUp();
    }

    @Test
    public void addTweet() {
        User user = new User("Youri", "bio", "password");
        Tweet tw1 = new Tweet(user, "testTweet1");
        Tweet tw2 = new Tweet(user, "testTweet2");

        tx.begin();
        userDAO.addUser(user);
        tweetDAO.addTweet(tw1);
        tweetDAO.addTweet(tw2);
        tx.commit();

        assertEquals(tweetDAO.getAllTweets().size(), 2);

        assertEquals(tweetDAO.searchTweets("testTweet1").get(0), tw1);
        assertEquals(tweetDAO.searchTweets("testTweet2").get(0), tw2);
    }

    @Test
    public void removeTweet() {
        User user = new User("Youri", "bio", "password");
        Tweet tw1 = new Tweet(user, "testTweet1");
        Tweet tw2 = new Tweet(user, "testTweet2");

        tx.begin();
        userDAO.addUser(user);
        tweetDAO.addTweet(tw1);
        tweetDAO.addTweet(tw2);
        tx.commit();

        assertEquals(tweetDAO.getAllTweets().size(), 2);

        assertEquals(tweetDAO.searchTweets("testTweet1").get(0), tw1);
        assertEquals(tweetDAO.searchTweets("testTweet2").get(0), tw2);

        tx.begin();
        tweetDAO.removeTweet(tw1);
        tx.commit();

        assertEquals(tweetDAO.getAllTweets().size(), 1);

        tx.begin();
        tweetDAO.removeTweet(tw2);
        tx.commit();

        assertEquals(tweetDAO.getAllTweets().size(), 0);
    }

    @Test
    public void searchTweets() {
        User user = new User("Youri", "bio", "password");
        Tweet tw1 = new Tweet(user, "testTweet1");
        Tweet tw2 = new Tweet(user, "testTweet2");
        Tweet tw3 = new Tweet(user, "test123");
        Tweet tw4 = new Tweet(user, "tttest136");

        tx.begin();
        userDAO.addUser(user);
        tweetDAO.addTweet(tw1);
        tweetDAO.addTweet(tw2);
        tweetDAO.addTweet(tw3);
        tweetDAO.addTweet(tw4);
        tx.commit();

        assertEquals(tweetDAO.searchTweets("testTweet").size(), 2);
        assertEquals(tweetDAO.searchTweets("test").size(), 4);
        assertEquals(tweetDAO.searchTweets("test1").size(), 2);
    }

    @Test
    public void getAllTweets() {
        User user = new User("Youri", "bio", "password");
        Tweet tw1 = new Tweet(user, "testTweet1");
        Tweet tw2 = new Tweet(user, "testTweet2");
        Tweet tw3 = new Tweet(user, "test123");
        Tweet tw4 = new Tweet(user, "tttest136");

        tx.begin();
        userDAO.addUser(user);
        tx.commit();

        assertEquals(tweetDAO.getAllTweets().size(), 0);
        tx.begin();
        userDAO.addUser(user);
        tweetDAO.addTweet(tw1);
        tx.commit();
        assertEquals(tweetDAO.getAllTweets().size(), 1);
        tx.begin();
        tweetDAO.addTweet(tw2);
        tx.commit();
        assertEquals(tweetDAO.getAllTweets().size(), 2);
        tx.begin();
        tweetDAO.addTweet(tw3);
        tx.commit();
        assertEquals(tweetDAO.getAllTweets().size(), 3);
        tx.begin();
        tweetDAO.addTweet(tw4);
        tx.commit();
        assertEquals(tweetDAO.getAllTweets().size(), 4);
    }

    @Test
    public void getTweet() {
        User user = new User("Youri", "bio", "password");
        Tweet tw1 = new Tweet(user, "testTweet1");
        Tweet tw2 = new Tweet(user, "testTweet2");

        tx.begin();
        userDAO.addUser(user);
        tweetDAO.addTweet(tw1);
        tweetDAO.addTweet(tw2);
        tx.commit();

        tx.begin();
        Tweet tw1Copy = tweetDAO.searchTweets("testTweet1").get(0);
        Tweet tw2Copy = tweetDAO.searchTweets("testTweet2").get(0);
        tx.commit();

        assertEquals(tweetDAO.getTweet(tw1Copy.getId()), tw1Copy);
        assertEquals(tweetDAO.getTweet(tw2Copy.getId()), tw2Copy);
    }
}