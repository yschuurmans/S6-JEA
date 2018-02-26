package dao;

import Annotations.JPA;
import domain.Hashtag;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
@JPA
public class HashtagDAOImplJPA implements HashtagDAO {

    @PersistenceContext(unitName = "Kwetter")
    private EntityManager em;

    public HashtagDAOImplJPA() {
    }

    @Override
    public void addHashTag(Hashtag hashtag) {
        em.persist(hashtag);
    }

    @Override
    public void removeHashTag(Hashtag hashtag) {
        em.remove(hashtag);
    }

    @Override
    public Hashtag findHashtag(String hashtagName) {
        TypedQuery<Hashtag> query = em.createNamedQuery("hashtag.findHashtag", Hashtag.class);
        query.setParameter("word", hashtagName );
        List<Hashtag> result = query.getResultList();
        if (result.size() <= 0) return null;
        return result.get(0);
    }

    @Override
    public List<Hashtag> getAllHashtags() {
        Query query = em.createQuery("SELECT h FROM Hashtag h");
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public List<Hashtag> searchHashtags(String searchTerm) {
        TypedQuery<Hashtag> query = em.createNamedQuery("hashtag.searchHashtags", Hashtag.class);
        query.setParameter("searchTerm", "%"+ searchTerm + "%" );
        List<Hashtag> result = query.getResultList();
        return result;
    }
}
