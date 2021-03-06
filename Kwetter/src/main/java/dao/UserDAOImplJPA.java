package dao;

import Annotations.JPA;
import Logic.PasswordHash;
import domain.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.Transaction;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Stateless
@JPA
public class UserDAOImplJPA implements UserDAO {
    public UserDAOImplJPA() {
    }

    @PersistenceContext(unitName = "Kwetter")
    private EntityManager em;

    @Override
    public boolean addUser(User user) {
        try {
            em.persist(user);
            return true;
        } catch (EntityExistsException ex) {
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public void removeUser(User user) {
        em.remove(user);
    }

    @Override
    public void removeUser(String user) {
        User u = findUserByName(user);
        em.remove(u);
    }

    @Override
    public User findUserByName(String name) {
        TypedQuery<User> query = em.createNamedQuery("user.findByname", User.class);
        query.setParameter("name", name);
        List<User> result = query.getResultList();
        if (result.size() <= 0) return null;
        return result.get(0);
    }

    @Override
    public User getUser(long id) {
        TypedQuery<User> query = em.createNamedQuery("user.findByID", User.class);
        query.setParameter("id", id);
        List<User> result = query.getResultList();
        if (result.size() <= 0) return null;
        return result.get(0);
    }

    @Override
    public List<User> getAllUsers() {
        em.flush();
        Query query = em.createQuery("SELECT s FROM User s");
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public boolean editUser(User user) {

        User u =findUserByName(user.getUsername());
        u.setBio(user.getBio());
        boolean success = false;
        try {
            em.merge(u);
            em.flush();
            success = true;
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
        }
        return success;
    }

    @Override
    public User authenticateUser(String username, String password) {
        TypedQuery<User> query = em.createNamedQuery("user.authenticate", User.class);
        query.setParameter("username", username);
        query.setParameter("password", PasswordHash.stringToHash(password));
        List<User> result = query.getResultList();
        if (result.size() <= 0) return null;
        return result.get(0);
    }


    public void setEm(EntityManager em) {
        this.em = em;
    }
}
