package bean;

import domain.User;
import service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value = "userBean")
@RequestScoped
public class UserBean {

    @Inject
    private UserService userService;

    private String username;
    private String password;
    private String bio;
    private String salt;

    private String filter;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public List<User> getUsers() {
        if (filter != null && filter.length() > 0) {
            ArrayList<User> filtered = new ArrayList<>();
            for (User s : userService.getAllUsers()) {
                if (s.getUsername().toLowerCase().contains(filter)) {
                    filtered.add(s);
                }
            }
            return filtered;
        } else {
            return userService.getAllUsers();
        }
    }

    public void removeUser(User user) {
        userService.removeUser(user);
    }

    public void addUser() {
        User user = new User(username, bio, password);
        userService.addUser(user);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
