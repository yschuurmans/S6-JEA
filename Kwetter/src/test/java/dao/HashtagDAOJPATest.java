package dao;

import domain.Hashtag;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class HashtagDAOJPATest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterTest");
    private EntityManager em;
    private EntityTransaction tx;
    private HashtagDAOImplJPA hashtagDAO;

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(HashtagDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        hashtagDAO = new HashtagDAOImplJPA();
        hashtagDAO.setEm(em);
    }

    @Test
    public void addHashTag() {
        Hashtag ht1 = new Hashtag("TestHashtag");
        Hashtag ht2 = new Hashtag("TestHashtag2");

        tx.begin();
        hashtagDAO.addHashTag(ht1);
        hashtagDAO.addHashTag(ht2);
        tx.commit();

        tx.begin();
        int count = hashtagDAO.getAllHashtags().size();
        tx.commit();

        assertEquals(count, 2);
    }

    @Test
    public void removeHashTag() {
        Hashtag ht1 = new Hashtag("TestHashtag");
        Hashtag ht2 = new Hashtag("TestHashtag2");

        tx.begin();
        hashtagDAO.addHashTag(ht1);
        hashtagDAO.addHashTag(ht2);
        tx.commit();

        assertEquals(hashtagDAO.getAllHashtags().size(), 2);

        tx.begin();
        hashtagDAO.removeHashTag(hashtagDAO.findHashtag("TestHashtag"));
        tx.commit();

        assertEquals(hashtagDAO.getAllHashtags().size(), 1);

        assertNotNull(hashtagDAO.findHashtag("TestHashtag2"));
        assertEquals(hashtagDAO.findHashtag("TestHashtag2"), ht2);
    }

    @Test
    public void findHashtag() {
        Hashtag ht1 = new Hashtag("TestHashtag");
        Hashtag ht2 = new Hashtag("TestHashtag2");
        Hashtag ht3 = new Hashtag("TestHashtag3");
        Hashtag ht4 = new Hashtag("TestHashtag4");

        hashtagDAO.addHashTag(ht1);
        hashtagDAO.addHashTag(ht2);
        hashtagDAO.addHashTag(ht3);
        hashtagDAO.addHashTag(ht4);

        assertEquals(hashtagDAO.getAllHashtags().size(), 4);

        assertEquals(hashtagDAO.findHashtag("TestHashtag"), ht1);
        assertEquals(hashtagDAO.findHashtag("TestHashtag2"), ht2);
        assertEquals(hashtagDAO.findHashtag("TestHashtag3"), ht3);
        assertEquals(hashtagDAO.findHashtag("TestHashtag4"), ht4);

        assertNull(hashtagDAO.findHashtag("TestHashtag1"));
        assertNull(hashtagDAO.findHashtag("TestHashtag12"));
        assertNull(hashtagDAO.findHashtag("TestHashtag13"));
        assertNull(hashtagDAO.findHashtag("TestHashtag14"));

        assertNotEquals(hashtagDAO.findHashtag("TestHashtag2"), ht1);
        assertNotEquals(hashtagDAO.findHashtag("TestHashtag3"), ht2);
        assertNotEquals(hashtagDAO.findHashtag("TestHashtag4"), ht3);
        assertNotEquals(hashtagDAO.findHashtag("TestHashtag"), ht4);
    }

    @Test
    public void getAllHashtags() {
        Hashtag ht1 = new Hashtag("TestHashtag");
        Hashtag ht2 = new Hashtag("TestHashtag2");
        Hashtag ht3 = new Hashtag("TestHashtag3");
        Hashtag ht4 = new Hashtag("TestHashtag4");

        hashtagDAO.addHashTag(ht1);
        hashtagDAO.addHashTag(ht2);
        hashtagDAO.addHashTag(ht3);
        hashtagDAO.addHashTag(ht4);

        assertEquals(hashtagDAO.getAllHashtags().size(), 4);
        List<Hashtag> allHashtags = hashtagDAO.getAllHashtags();

        assertTrue(allHashtags.contains(ht1));
        assertTrue(allHashtags.contains(ht2));
        assertTrue(allHashtags.contains(ht3));
        assertTrue(allHashtags.contains(ht4));

        Hashtag ht5 = new Hashtag("TestHashtag5");
        Hashtag ht6 = new Hashtag("TestHashtag6");

        hashtagDAO.addHashTag(ht5);
        hashtagDAO.addHashTag(ht6);

        assertEquals(hashtagDAO.getAllHashtags().size(), 6);

        List<Hashtag> allHashtags2 = hashtagDAO.getAllHashtags();

        assertTrue(allHashtags.contains(ht1));
        assertTrue(allHashtags.contains(ht2));
        assertTrue(allHashtags.contains(ht3));
        assertTrue(allHashtags.contains(ht4));
        assertTrue(allHashtags.contains(ht5));
        assertTrue(allHashtags.contains(ht6));
    }

    @Test
    public void searchHashtags() {
        Hashtag ht1 = new Hashtag("Omg");
        Hashtag ht2 = new Hashtag("OMG");
        Hashtag ht3 = new Hashtag("O");
        Hashtag ht4 = new Hashtag("O");

        hashtagDAO.addHashTag(ht1);
        hashtagDAO.addHashTag(ht2);
        hashtagDAO.addHashTag(ht3);
        hashtagDAO.addHashTag(ht4);

        assertEquals(hashtagDAO.getAllHashtags().size(), 4);
        List<Hashtag> allHashtags = hashtagDAO.getAllHashtags();

        assertTrue(allHashtags.contains(ht1));
        assertTrue(allHashtags.contains(ht2));
        assertTrue(allHashtags.contains(ht3));
        assertTrue(allHashtags.contains(ht4));

        Hashtag ht5 = new Hashtag("TestHashtag5");
        Hashtag ht6 = new Hashtag("TestHashtag6");

        hashtagDAO.addHashTag(ht5);
        hashtagDAO.addHashTag(ht6);

        assertEquals(hashtagDAO.getAllHashtags().size(), 6);

        List<Hashtag> allHashtags2 = hashtagDAO.getAllHashtags();

        assertTrue(allHashtags.contains(ht1));
        assertTrue(allHashtags.contains(ht2));
        assertTrue(allHashtags.contains(ht3));
        assertTrue(allHashtags.contains(ht4));
        assertTrue(allHashtags.contains(ht5));
        assertTrue(allHashtags.contains(ht6));
    }
}