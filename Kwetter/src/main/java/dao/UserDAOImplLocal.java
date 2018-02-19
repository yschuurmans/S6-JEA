package dao;

import domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Stateless @Default
public class UserDAOImplLocal implements UserDAO {

    public UserDAOImplLocal() {
    }

    CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();
    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void removeUser(User user) {
        users.remove(user);
    }

    @Override
    public User findUserByName(String name) {
        for (User user : users) {
            if (user.getUsername().contentEquals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
}
