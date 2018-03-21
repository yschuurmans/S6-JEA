package bean.tweets;

import domain.Role;
import domain.Tweet;
import domain.User;
import service.TweetService;
import service.UserService;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "tweetBean")
@ViewScoped
public class TweetBean implements Serializable {

    @Inject
    private TweetService tweetService;

    private List<Tweet> tweets;

    public void initUsers() {
        this.tweets = tweetService.getAllTweets();
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}
