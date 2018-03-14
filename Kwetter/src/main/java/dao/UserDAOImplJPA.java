package dao;

import Annotations.JPA;
import domain.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.Stateless;
import javax.persistence.*;
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
        try {
            em.merge(user);
            return true;
        }catch(Exception ex) {
            return false;
        }
    }


    public void setEm(EntityManager em) {
        this.em = em;
    }
}
