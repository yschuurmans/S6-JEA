package dao;

import domain.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);
    void removeUser(User user);
    User findUserByName(String name);
    List<User> getAllUsers();
}
