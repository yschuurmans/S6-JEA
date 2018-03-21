package service;

import Logic.PasswordHash;
import dao.PermissionGroupDAO;
import domain.PermissionGroup;
import domain.Tweet;
import domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;

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
        PermissionGroup adminGroup = new PermissionGroup(PermissionGroup.ADMIN_GROUP);
        PermissionGroup modGroup = new PermissionGroup(PermissionGroup.MODERATOR_GROUP);
        PermissionGroup userGroup = new PermissionGroup(PermissionGroup.USER_GROUP);
        groupDaoJPA.create(adminGroup);
        groupDaoJPA.create(modGroup);
        groupDaoJPA.create(userGroup);

        Youri.getPermissionGroups().add(adminGroup);
        Mike.getPermissionGroups().add(modGroup);
        Ken.getPermissionGroups().add(userGroup);

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
