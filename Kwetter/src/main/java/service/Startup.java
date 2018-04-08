package service;

import Logic.PasswordHash;
import dao.PermissionGroupDAO;
import domain.PermissionGroup;
import domain.Tweet;
import domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.security.Permission;

@Singleton
@javax.ejb.Startup
public class Startup {

    @Inject
    private UserService userService;
    @Inject
    private TweetService tweetService;
    @Inject
    private HashtagService hashtagService;
    @Inject
    PermissionGroupDAO groupDaoJPA;

    public Startup() {
    }

    @PostConstruct
    private void initData() {
        User Youri = new User("Youri", "Oss", PasswordHash.stringToHash("Youri"));
        User Ken = new User("Ken", "KutDorp", PasswordHash.stringToHash("Ken"));
        User Mike = new User("Mike", "NogEenKutdorp", PasswordHash.stringToHash("Mike"));
        groupDaoJPA.create(PermissionGroup.ADMIN_GROUP);
        groupDaoJPA.create(PermissionGroup.MODERATOR_GROUP);
        groupDaoJPA.create(PermissionGroup.USER_GROUP);

        Youri.getPermissionGroups().add(PermissionGroup.ADMIN_GROUP);
        Mike.getPermissionGroups().add(PermissionGroup.MODERATOR_GROUP);
        Ken.getPermissionGroups().add(PermissionGroup.USER_GROUP);

        userService.addUser(Youri);
        userService.addUser(Ken);
        userService.addUser(Mike);

        userService.addFollower("Ken", "Youri");
        userService.addFollower("Mike", "Youri");
        userService.addFollower("Youri", "Ken");

        tweetService.addTweet(new Tweet(Youri, "RandomTweet"));
        tweetService.addTweet(new Tweet(Youri, "RandomTweet2"));
        tweetService.addTweet(new Tweet(Youri, "Testdata is interessant"));
        tweetService.addTweet(new Tweet(Ken, "Random bericht wat ken zou sturen"));
        tweetService.addTweet(new Tweet(Youri, "Waarom lees je dit überhaupt?"));
        tweetService.addTweet(new Tweet(Youri, "#Twitter =/= #kwetter"));
        tweetService.addTweet(new Tweet(Youri, "werken aan de #kwetter app"));

        tweetService.addTweet(new Tweet(Ken, "Random bericht wat ken zou sturen"));
        tweetService.addTweet(new Tweet(Ken, "Nog een random bericht wat ken zou sturen"));
        tweetService.addTweet(new Tweet(Ken, "FUCK HOEVEEL BERICHTEN GAAT KEN STUREN"));
        tweetService.addTweet(new Tweet(Mike, "Waarom lees je dit überhaupt?"));
        tweetService.addTweet(new Tweet(Mike, "#Twitter =/= #kwetter"));
        tweetService.addTweet(new Tweet(Mike, "werken aan de #kwetter app"));



    }
}
