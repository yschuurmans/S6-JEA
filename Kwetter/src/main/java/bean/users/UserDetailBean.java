package bean.users;

import domain.Tweet;
import domain.User;
import service.TweetService;
import service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "userDetailBean")
@ViewScoped
public class UserDetailBean  implements Serializable {

    @Inject
    private UserService userService;
    @Inject
    private TweetService tweetService;

    private String username;
    private User user;

    public UserDetailBean() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void loadUser(){
        User u = userService.findByName(username);
        this.setUser(u);
    }
    public String selectUsers() {
        return "user?faces-redirect=true";
    }

    public void removeTweet(long tweetid) {
        tweetService.removeTweet(tweetid);
    }

    public void testMethod() {
        System.out.println("Works");
    }
}
