package dao;

import domain.User;

import java.util.List;

public interface UserDAO {
    boolean addUser(User user);
    void removeUser(User user);
    void removeUser(String user);
    User findUserByName(String name);
    User getUser(long id);
    List<User> getAllUsers();

    boolean editUser(User user);

    User authenticateUser(String username, String password);
}
