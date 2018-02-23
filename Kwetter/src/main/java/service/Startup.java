package service;

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

    public Startup() {
    }

    @PostConstruct
    private void initData() {
        User Youri = new User("Youri", "Oss", "YourisPassword");
        User Ken = new User("Ken", "KutDorp", "KensPassword");
        User Mike = new User("Mike", "NogEenKutdorp", "MikesPassword");

        List<User> youriFollowers = new ArrayList<User>();
        youriFollowers.add(Ken);
        youriFollowers.add(Mike);
        Youri.setFollowers(youriFollowers);

        List kenFollowers= new ArrayList<User>();
        kenFollowers.add(Youri);
        Ken.setFollowers(kenFollowers);
        List mikeFollowers= new ArrayList<User>();
        mikeFollowers.add(Youri);
        Mike.setFollowers(mikeFollowers);

        userService.addUser(Youri);
        userService.addUser(Ken);
        userService.addUser(Mike);

    }
}
