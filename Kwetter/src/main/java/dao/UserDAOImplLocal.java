package dao;

import domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Stateless
@Default
public class UserDAOImplLocal implements UserDAO {

    public UserDAOImplLocal() {
    }

    CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();

    @Override
    public boolean addUser(User user) {
        for (User u : users) {
            if (u.getUsername().contentEquals(user.getUsername())) return false;
        }
        users.add(user);
        return true;
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
    public User getUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public boolean editUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    @Override
    public void addFollower(int idFollower, int idToFollow) {
        User follower = getUser(idFollower);
        User toFollow = getUser(idToFollow);
        toFollow.addFollower(follower);
        follower.addFollowing(toFollow);
    }

    @Override
    public void removeFollower(int idFollower, int idToUnfollow) {
        User follower = getUser(idFollower);
        User toUnfollow = getUser(idToUnfollow);
        toUnfollow.removeFollower(follower);
        follower.removeFollowing(toUnfollow);
    }
}
