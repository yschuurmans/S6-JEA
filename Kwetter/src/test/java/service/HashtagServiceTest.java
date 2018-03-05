package service;

import dao.HashtagDAO;
import domain.Hashtag;
import domain.Tweet;
import domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HashtagServiceTest {

    HashtagService service;
    @Mock
    HashtagDAO dao;

    Hashtag hashtag;

    @Before
    public void setUp() throws Exception {
        service = new HashtagService();
        service.setHashtagDAO(dao);
        hashtag = new Hashtag("Test");
    }

    @Test
    public void getAllHashtags() {
        List<Hashtag> allHashtags = new ArrayList<>();
        allHashtags.add(hashtag);
        when(dao.getAllHashtags()).thenReturn(allHashtags);

        assertEquals(service.getAllHashtags().size(), 1);

        verify(dao, times(1)).getAllHashtags();
    }

    @Test
    public void addHashtag() {
        service.addHashtag(hashtag);
        verify(dao,times(1)).addHashTag(hashtag);
    }

    @Test
    public void removeHashtag() {
        service.removeHashtag(hashtag);
        verify(dao,times(1)).removeHashTag(hashtag);
    }

    @Test
    public void findHashtag() {
        when(dao.findHashtag("Test")).thenReturn(hashtag);
        assertEquals(service.findHashtag("Test"), hashtag);
        verify(dao, times(1)).findHashtag("Test");
    }

    @Test
    public void searchHashtags() {
        List<Hashtag> foundHashtags = new ArrayList<>();
        foundHashtags.add(hashtag);
        when(dao.searchHashtags("Test")).thenReturn(foundHashtags);

        assertEquals(service.searchHashtags("Test").size(), 1);

        verify(dao, times(1)).searchHashtags("Test");
    }
}