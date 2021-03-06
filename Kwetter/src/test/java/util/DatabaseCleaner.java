package util;

import domain.Hashtag;
import domain.PermissionGroup;
import domain.Tweet;
import domain.User;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.sql.SQLException;

public class DatabaseCleaner {

    private static final Class<?>[] ENTITY_TYPES = {
            Tweet.class,
            Hashtag.class,
            User.class,
            PermissionGroup.class,
    };

    private final EntityManager em;

    public DatabaseCleaner(EntityManager entityManager) {
        em = entityManager;
    }

    public void clean() throws SQLException {
        em.getTransaction().begin();

        for (Class<?> entityType : ENTITY_TYPES) {
            deleteEntities(getEntityName(entityType));
        }
        em.getTransaction().commit();
        em.close();
    }

    private void deleteEntities(String entityName) {
        em.createQuery("delete from " + entityName).executeUpdate();
    }

    protected String getEntityName(Class<?> clazz) {
        EntityType et = em.getMetamodel().entity(clazz);
        return et.getName();
    }
}
