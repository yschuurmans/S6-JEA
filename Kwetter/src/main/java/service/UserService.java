package service;

import Annotations.JPA;
import dao.UserDAO;
import domain.User;

import javax.inject.Inject;
import java.util.List;

public class UserService {
    @Inject @JPA
    private UserDAO userDAO;

    public UserService() {
    }

    public void addUser(User user) { userDAO.addUser(user);}

    public void removeUser(User user) {userDAO.removeUser(user);}

    public void removeUser(String username){
        User user = findByName(username);
        removeUser(user);
    }

    public User findByName(String name) {return userDAO.findUserByName(name);}

    public void setdao(UserDAO userDAO) {this.userDAO = userDAO; }

    public List<User> getAllUsers() { return userDAO.getAllUsers(); }
}
