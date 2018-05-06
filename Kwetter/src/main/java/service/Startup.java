package service;

import Logic.PasswordHash;
import dao.PermissionGroupDAO;
import domain.PermissionGroup;
import domain.Role;
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

        userService.addUser(Youri);
        userService.addUser(Ken);
        userService.addUser(Mike);

        groupDaoJPA.create(PermissionGroup.ADMIN_GROUP);
        groupDaoJPA.create(PermissionGroup.MODERATOR_GROUP);
        groupDaoJPA.create(PermissionGroup.USER_GROUP);
        Youri.setProfilePicture("https://t2.rbxcdn.com/39e183ed6cf88b19a00ec07b42b3e04d");
        Ken.setProfilePicture("https://sanelythinking.files.wordpress.com/2012/01/img_2433.png");
        Mike.setProfilePicture("https://pre00.deviantart.net/168c/th/pre/i/2015/300/e/7/derp_face_by_cherryblossomcake-d9ekfoc.png");

        Youri.setPermissionGroup(Role.Admin);
        Mike.setPermissionGroup(Role.Moderator);
        Ken.setPermissionGroup(Role.User);

        userService.editUser(Youri);
        userService.editUser(Ken);
        userService.editUser(Mike);

        userService.addFollower(Ken.getUsername(), Youri.getUsername());
        userService.addFollower(Mike.getUsername(), Youri.getUsername());
        userService.addFollower(Youri.getUsername(), Ken.getUsername());

        tweetService.addTweet(new Tweet(Youri, "RandomTweet"), false);
        tweetService.addTweet(new Tweet(Youri, "RandomTweet2"), false);
        tweetService.addTweet(new Tweet(Youri, "Testdata is interessant"), false);
        tweetService.addTweet(new Tweet(Ken, "Random bericht wat ken zou sturen"), false);
        tweetService.addTweet(new Tweet(Youri, "Waarom lees je dit überhaupt?"), false);
        tweetService.addTweet(new Tweet(Youri, "#Twitter =/= #kwetter"), false);
        tweetService.addTweet(new Tweet(Youri, "werken aan de #kwetter app"), false);

        tweetService.addTweet(new Tweet(Ken, "Random bericht wat ken zou sturen"), false);
        tweetService.addTweet(new Tweet(Ken, "Nog een random bericht wat ken zou sturen"), false);
        tweetService.addTweet(new Tweet(Ken, "FUCK HOEVEEL BERICHTEN GAAT KEN STUREN"), false);
        tweetService.addTweet(new Tweet(Mike, "Waarom lees je dit überhaupt?"), false);
        tweetService.addTweet(new Tweet(Mike, "#Twitter =/= #kwetter"), false);
        tweetService.addTweet(new Tweet(Mike, "werken aan de #kwetter app"), false);




    }
}
