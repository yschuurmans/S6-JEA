package bean.users;

import domain.User;
import service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value = "userDetailBean")
@RequestScoped
public class UserDetailBean {

    @Inject
    private UserService userService;

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
}
