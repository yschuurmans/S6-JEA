package service;

import Annotations.JPA;
import dao.HashtagDAO;
import domain.Hashtag;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class HashtagService {

    @Inject
    @JPA
    private HashtagDAO hashtagDAO;

    public HashtagService(){

    }

    /**
     * Sets the DAO used by the service, mainly used for testing.
     * @param dao The DAO to set
     */
    public void setHashtagDAO(HashtagDAO dao) {this.hashtagDAO = dao;}


    /**
     * Gets all hashtags in the database.
     * @return all hashtags in the database.
     */
    public List<Hashtag> getAllHashtags() {return hashtagDAO.getAllHashtags();}


    /**
     * Add a new hashtag.
     * @param hashtag the hashtag to post.
     */
    public void addHashtag(Hashtag hashtag) { hashtagDAO.addHashTag(hashtag);}


    /**
     * Removes a hashtag.
     * @param hashtag the hashtag to remove
     */
    public void removeHashtag(Hashtag hashtag) { hashtagDAO.removeHashTag(hashtag);}


    /**
     * Finds a hashtag based on the full string
     * @param hashtagName the text of the hashtag to find.
     * @return The found hashtag.
     */
    public Hashtag findHashtag(String hashtagName) { return hashtagDAO.findHashtag(hashtagName);}


    /**
     * Searches all hashtags and returns all hashtags that contain the search term
     * @param searchTerms the search term to look for
     * @return all hashtags that contain the search term.
     */
    public List<Hashtag> searchHashtags(String searchTerms) { return hashtagDAO.searchHashtags(searchTerms);}


}
