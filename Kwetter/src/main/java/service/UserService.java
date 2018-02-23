package service;

import dao.UserDAO;
import domain.User;

import javax.inject.Inject;
import java.util.List;

public class UserService {
    @Inject
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

    public User getUser(int id) { return userDAO.getUser(id);}

    public void addFollower(int idFollower, int idToFollow) { userDAO.addFollower(idFollower, idToFollow);}
    public void removeFollower(int idFollower, int idToUnfollow) { userDAO.removeFollower(idFollower, idToUnfollow);}

    public List<User> getUserFollowers(int id) { return userDAO.getUser(id).getFollowers();}
    public List<User> getUserFollowers(String username) { return userDAO.findUserByName(username).getFollowers();}

    public List<User> getUserFollowing(int id) { return userDAO.getUser(id).getFollowing();}
    public List<User> getUserFollowing(String username) { return findByName(username).getFollowing();}

    public boolean editUser(User user) { return userDAO.editUser(user);}
}
