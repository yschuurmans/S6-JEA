package dao;

import Annotations.JPA;
import domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Stateless @JPA
public class UserDAOImplJPA implements UserDAO {
    public UserDAOImplJPA() {
    }

    @PersistenceContext(unitName = "Kwetter")
    private EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public void removeUser(User user) {
        em.remove(user);
    }

    @Override
    public User findUserByName(String name) {
        TypedQuery<User> query = em.createNamedQuery("user.findByname", User.class);
        query.setParameter("name", name);
        List<User> result = query.getResultList();
        return result.get(0);
    }

    @Override
    public List<User> getAllUsers() {
        Query query = em.createQuery("SELECT s FROM User s");
        return new ArrayList<>(query.getResultList());
    }
}
