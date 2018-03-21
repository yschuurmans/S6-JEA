package bean.users;

import domain.PermissionGroup;
import domain.Role;
import domain.User;
import service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Named(value = "userBean")
@ViewScoped
public class UserBean implements Serializable {

    @Inject
    private UserService userService;

    private List<User> users;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private String username;
    private String password;
    private String bio;
    private String salt;

    private String filter;

    public UserBean() {
    }



    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public void initUsers() {
        try {
            this.users = userService.getAllUsers();
        }
        catch (Exception e) {}

    }

    public List<User> getFilteredUsers() {
        if (filter != null && filter.length() > 0) {
            ArrayList<User> filtered = new ArrayList<>();
            for (User s : users) {
                if (s.getUsername().toLowerCase().contains(filter)) {
                    filtered.add(s);
                }
            }
            return filtered;
        } else {
            return users;
        }
    }

    public void removeUser(User user) {
        userService.removeUser(user);
    }

    public void addUser() {
        User user = new User(username, bio, password);
        userService.addUser(user);
    }

    public void editUser(User user) {
        userService.editUser(user);
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

    public List<SelectItem> getRoleList() {
        List<SelectItem> returnList = new ArrayList<>();
        for (Role role : Role.values()) {
            returnList.add(new SelectItem(role));
        }
        return returnList;
    }
}
