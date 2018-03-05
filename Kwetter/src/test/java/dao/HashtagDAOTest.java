package dao;

import domain.Hashtag;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.validation.constraints.AssertTrue;

import java.util.List;

import static org.junit.Assert.*;

public class HashtagDAOTest {


    @Test
    public void addHashTag() {
        HashtagDAO hashtagDAO = new HashtagDAOImplLocal();
        Hashtag ht1 = new Hashtag("TestHashtag");
        Hashtag ht2 = new Hashtag("TestHashtag2");

        hashtagDAO.addHashTag(ht1);
        hashtagDAO.addHashTag(ht2);

        assertEquals(hashtagDAO.getAllHashtags().size(),2);
    }

    @Test
    public void removeHashTag() {
        HashtagDAO hashtagDAO = new HashtagDAOImplLocal();
        Hashtag ht1 = new Hashtag("TestHashtag");
        Hashtag ht2 = new Hashtag("TestHashtag2");

        hashtagDAO.addHashTag(ht1);
        hashtagDAO.addHashTag(ht2);

        assertEquals(hashtagDAO.getAllHashtags().size(),2);

        hashtagDAO.removeHashTag(hashtagDAO.findHashtag("TestHashtag"));

        assertEquals(hashtagDAO.getAllHashtags().size(),1);

        assertNotNull(hashtagDAO.findHashtag("TestHashtag2"));
        assertEquals(hashtagDAO.findHashtag("TestHashtag2"), ht2);
    }

    @Test
    public void findHashtag() {
        HashtagDAO hashtagDAO = new HashtagDAOImplLocal();
        Hashtag ht1 = new Hashtag("TestHashtag");
        Hashtag ht2 = new Hashtag("TestHashtag2");
        Hashtag ht3 = new Hashtag("TestHashtag3");
        Hashtag ht4 = new Hashtag("TestHashtag4");

        hashtagDAO.addHashTag(ht1);
        hashtagDAO.addHashTag(ht2);
        hashtagDAO.addHashTag(ht3);
        hashtagDAO.addHashTag(ht4);

        assertEquals(hashtagDAO.getAllHashtags().size(),4);

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
        HashtagDAO hashtagDAO = new HashtagDAOImplLocal();
        Hashtag ht1 = new Hashtag("TestHashtag");
        Hashtag ht2 = new Hashtag("TestHashtag2");
        Hashtag ht3 = new Hashtag("TestHashtag3");
        Hashtag ht4 = new Hashtag("TestHashtag4");

        hashtagDAO.addHashTag(ht1);
        hashtagDAO.addHashTag(ht2);
        hashtagDAO.addHashTag(ht3);
        hashtagDAO.addHashTag(ht4);

        assertEquals(hashtagDAO.getAllHashtags().size(),4);
        List<Hashtag> allHashtags = hashtagDAO.getAllHashtags();

        assertTrue(allHashtags.contains(ht1));
        assertTrue(allHashtags.contains(ht2));
        assertTrue(allHashtags.contains(ht3));
        assertTrue(allHashtags.contains(ht4));

        Hashtag ht5 = new Hashtag("TestHashtag5");
        Hashtag ht6 = new Hashtag("TestHashtag6");

        hashtagDAO.addHashTag(ht5);
        hashtagDAO.addHashTag(ht6);

        assertEquals(hashtagDAO.getAllHashtags().size(),6);

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
        HashtagDAO hashtagDAO = new HashtagDAOImplLocal();
        Hashtag ht1 = new Hashtag("Omg");
        Hashtag ht2 = new Hashtag("OMG");
        Hashtag ht3 = new Hashtag("O");
        Hashtag ht4 = new Hashtag("O");

        hashtagDAO.addHashTag(ht1);
        hashtagDAO.addHashTag(ht2);
        hashtagDAO.addHashTag(ht3);
        hashtagDAO.addHashTag(ht4);

        assertEquals(hashtagDAO.getAllHashtags().size(),4);
        List<Hashtag> allHashtags = hashtagDAO.getAllHashtags();

        assertTrue(allHashtags.contains(ht1));
        assertTrue(allHashtags.contains(ht2));
        assertTrue(allHashtags.contains(ht3));
        assertTrue(allHashtags.contains(ht4));

        Hashtag ht5 = new Hashtag("TestHashtag5");
        Hashtag ht6 = new Hashtag("TestHashtag6");

        hashtagDAO.addHashTag(ht5);
        hashtagDAO.addHashTag(ht6);

        assertEquals(hashtagDAO.getAllHashtags().size(),6);

        List<Hashtag> allHashtags2 = hashtagDAO.getAllHashtags();

        assertTrue(allHashtags.contains(ht1));
        assertTrue(allHashtags.contains(ht2));
        assertTrue(allHashtags.contains(ht3));
        assertTrue(allHashtags.contains(ht4));
        assertTrue(allHashtags.contains(ht5));
        assertTrue(allHashtags.contains(ht6));
    }
}