package dao;

import Annotations.JPA;
import domain.Tweet;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
@JPA
public class TweetDAOImplJPA implements TweetDAO {
    public TweetDAOImplJPA() {
    }

    @PersistenceContext(unitName = "Kwetter")
    private EntityManager em;

    @Override
    public void addTweet(Tweet tweet) {
        em.persist(tweet);
    }

    @Override
    public void removeTweet(Tweet tweet) {
        em.remove(tweet);
    }

    @Override
    public List<Tweet> searchTweets(String searchText) {

        TypedQuery<Tweet> query = em.createNamedQuery("tweet.searchTweets", Tweet.class);
        query.setParameter("searchContent", "%" + searchText + "%");
        List<Tweet> result = query.getResultList();
        return result;
    }

    @Override
    public List<Tweet> getAllTweets() {
        Query query = em.createQuery("SELECT t FROM Tweet t");
        return new ArrayList<>(query.getResultList());
    }
}
