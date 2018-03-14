package dao;

import domain.User;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class UserDAOJPATest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterTest");
    private EntityManager em;
    private EntityTransaction tx;
    private TweetDAOImplJPA tweetDAO;

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(HashtagDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        tweetDAO = new TweetDAOImplJPA();
        tweetDAO.setEm(em);
    }

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