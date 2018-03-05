package service;

import domain.Hashtag;
import domain.Tweet;
import domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Singleton
@javax.ejb.Startup
public class Startup {

    @Inject
    private UserService userService;
    @Inject
    private TweetService tweetService;
    @Inject
    private HashtagService hashtagService;

    public Startup() {
    }

    @PostConstruct
    private void initData() {
        User Youri = new User("Youri", "Oss", "YourisPassword");
        User Ken = new User("Ken", "KutDorp", "KensPassword");
        User Mike = new User("Mike", "NogEenKutdorp", "MikesPassword");

        userService.addUser(Youri);
        userService.addUser(Ken);
        userService.addUser(Mike);

        userService.addFollower("Ken", "Youri");
        userService.addFollower("Mike", "Youri");
        userService.addFollower("Youri", "Ken");
        userService.addFollower("Youri", "Mike");

        tweetService.addTweet(new Tweet(Youri, "RandomTweet"));
        tweetService.addTweet(new Tweet(Youri, "RandomTweet2"));
        tweetService.addTweet(new Tweet(Youri, "Testdata is interessant"));
        tweetService.addTweet(new Tweet(Youri, "Waarom lees je dit überhaupt?"));

        tweetService.addTweet(new Tweet(Youri, "#Twitter =/= #kwetter"));
        tweetService.addTweet(new Tweet(Youri, "werken aan de #kwetter app"));



    }
}
