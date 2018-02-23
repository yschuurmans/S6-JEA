package dao;

import domain.User;

import java.util.List;

public interface UserDAO {
    boolean addUser(User user);
    void removeUser(User user);
    User findUserByName(String name);
    User getUser(int id);
    List<User> getAllUsers();

    boolean editUser(User user);

    void addFollower(int idFollower, int idToFollow);

    void removeFollower(int idFollower, int idToUnfollow);

}
