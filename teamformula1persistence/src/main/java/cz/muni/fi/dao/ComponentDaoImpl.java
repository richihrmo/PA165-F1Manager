package cz.muni.fi.dao;

import cz.muni.fi.entities.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Matus Macko
 */
@Repository
public class ComponentDaoImpl implements ComponentDao {
    @PersistenceContext
    private EntityManager em;

    public List<Component> listAvailableComponents() {
        return em.createQuery("select c from Component c where c.isAvailable = true", Component.class).getResultList();
    }

    public List<Component> listAllComponents() {
        return em.createQuery("select c from Component c", Component.class).getResultList();
    }

    public Component findComponent(String name) {
        try {
            return em
                    .createQuery("select c from Component c where c.name = :name",
                            Component.class).setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    public Component findComponent(Long id) {
        return em.find(Component.class, id);
    }

    public void addComponent(Component component) {
        em.persist(component);
    }

    public void updateComponent(Component component) {
        em.merge(component);
    }

    public void deleteComponent(Component component) {
        em.remove(em.merge(component));
    }
}
