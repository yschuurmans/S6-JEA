package service;

import dao.HashtagDAO;
import domain.Hashtag;

import javax.inject.Inject;
import java.util.List;

public class HashtagService {

    @Inject
    private HashtagDAO hashtagDAO;

    public HashtagService(){

    }

    public void setHashtagDAO(HashtagDAO dao) {this.hashtagDAO = dao;}

    public List<Hashtag> getAllHashtags() {return hashtagDAO.getAllHashtags();}

    public void addHashtag(Hashtag hashtag) { hashtagDAO.addHashTag(hashtag);}

    public void removeHashtag(Hashtag hashtag) { hashtagDAO.removeHashTag(hashtag);}

    public Hashtag findHashtag(String hashtagName) { return hashtagDAO.findHashtag(hashtagName);}

    public List<Hashtag> searchHashtags(String searchTerms) { return hashtagDAO.searchHashtags(searchTerms);}


}
