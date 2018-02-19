package service;

import domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
@javax.ejb.Startup
public class Startup {

    @Inject
    private UserService userService;

    public Startup() {
    }

    @PostConstruct
    private void initData() {
        userService.addUser(new User("Youri", "Oss", "YourisPassword"));
        userService.addUser(new User("Ken", "KutDorp", "KensPassword"));
        userService.addUser(new User("Mike", "NogeenKutdorp", "MikesPassword"));

    }
}
