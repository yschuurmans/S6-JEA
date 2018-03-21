package service;

import Annotations.JPA;
import dao.UserDAO;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService {
    @Inject
    @JPA
    private UserDAO userDAO;

    public UserService() {
    }

    /**
     * Adds a new user
     * @param user the user to add
     */
    public void addUser(User user) {
        userDAO.addUser(user);
    }


    /**
     * Remove a user
     * @param user the user to remove
     */
    public void removeUser(User user) {
        userDAO.removeUser(user);
    }

    /**
     * Remove a user based on their username
     * @param username the username of the user to remove.
     */
    public void removeUser(String username) {
        User user = findByName(username);
        removeUser(user);
    }

    /**
     * Find a username based on their username
     * @param name The username of the user
     * @return the user
     */
    public User findByName(String name) {
        return userDAO.findUserByName(name);
    }

    /**
     * Sets the DAO used by the service, mainly used for testing.
     * @param userDAO The DAO to set
     */
    public void setdao(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Gets all users in the database
     * @return all users in the database
     */
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    /**
     * Gets a user based on their ID
     * @param id the ID of the user to find.
     * @return the user
     */
    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    /**
     * Add a follower to a user
     * @param nameFollower username of the follower
     * @param nameToFollow username of the user to follow
     */
    public void addFollower(String nameFollower, String nameToFollow) {
        User follower = userDAO.findUserByName(nameFollower);
        User toFollow = userDAO.findUserByName(nameToFollow);
        toFollow.addFollower(follower);
        follower.addFollowing(toFollow);
        editUser(toFollow);
        editUser(follower);
    }

    /**
     * Removes a follower from a user
     * @param nameFollower username of the follower
     * @param nameToUnfollow username of the user to remove the follower from
     */
    public void removeFollower(String nameFollower, String nameToUnfollow) {
        User follower = userDAO.findUserByName(nameFollower);
        User toUnfollow = userDAO.findUserByName(nameToUnfollow);
        toUnfollow.removeFollower(follower);
        follower.removeFollowing(toUnfollow);
        editUser(toUnfollow);
        editUser(follower);
    }

    /**
     * Gets the followers of a user
     * @param username the username of the user to get the followers from
     * @return A list of followers
     */
    public List<User> getUserFollowers(String username) {
        return userDAO.findUserByName(username).getFollowers();
    }

    /**
     * Gets the users a user is following
     * @param username the username of the user of whom to get a list of users he follows
     * @return A list of user which the user follows
     */
    public List<User> getUserFollowing(String username) {
        return findByName(username).getFollowing();
    }

    /**
     * Edit a user
     * @param user The user to edit
     * @return Whether or not the operation was successful
     */
    public boolean editUser(User user) {
        return userDAO.editUser(user);
    }
}
