package dao;

import domain.Hashtag;
import domain.User;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

public class UserDAOTest {


    @Test
    public void addUser() {
        UserDAO userDAO = new UserDAOImplLocal();
        User u1 = new User("User1", "bio", "password");
        User u2 = new User("User2", "bio", "password");

        assertEquals(userDAO.getAllUsers().size(),0);
        userDAO.addUser(u1);
        assertEquals(userDAO.getAllUsers().size(),1);
        userDAO.addUser(u2);
        assertEquals(userDAO.getAllUsers().size(),2);

    }

    @Test
    public void removeUser() {
        UserDAO userDAO = new UserDAOImplLocal();
        User u1 = new User("User1", "bio", "password");
        User u2 = new User("User2", "bio", "password");

        userDAO.addUser(u1);
        userDAO.addUser(u2);


        assertEquals(userDAO.getAllUsers().size(),2);
        userDAO.removeUser(u1);
        assertEquals(userDAO.getAllUsers().size(),1);
        userDAO.removeUser(u2);
        assertEquals(userDAO.getAllUsers().size(),0);

    }

    @Test
    public void findUserByName() {
        UserDAO userDAO = new UserDAOImplLocal();
        User u1 = new User("User1", "bio", "password");
        User u2 = new User("User2", "bio", "password");

        userDAO.addUser(u1);
        userDAO.addUser(u2);

        assertEquals(userDAO.findUserByName("User1"), u1);
        assertEquals(userDAO.findUserByName("User2"), u2);
    }

    @Test
    public void getUser() {
        UserDAO userDAO = new UserDAOImplLocal();
        User u1 = new User("User1", "bio", "password");
        User u2 = new User("User2", "bio", "password");

        u1.setId(0);
        u2.setId(1);

        userDAO.addUser(u1);
        userDAO.addUser(u2);

        User u1C = userDAO.findUserByName("User1");
        User u2C = userDAO.findUserByName("User2");

        assertEquals(userDAO.getUser(u1C.getId()), u1C);
        assertEquals(userDAO.getUser(u2C.getId()), u2C);

    }

    @Test
    public void getAllUsers() {
        UserDAO userDAO = new UserDAOImplLocal();
        User u1 = new User("User1", "bio", "password");
        User u2 = new User("User2", "bio", "password");

        assertEquals(userDAO.getAllUsers().size(),0);
        userDAO.addUser(u1);
        assertEquals(userDAO.getAllUsers().size(),1);
        userDAO.addUser(u2);
        assertEquals(userDAO.getAllUsers().size(),2);
        userDAO.removeUser(u1);
        assertEquals(userDAO.getAllUsers().size(),1);
        userDAO.removeUser(u2);
        assertEquals(userDAO.getAllUsers().size(),0);
    }

}