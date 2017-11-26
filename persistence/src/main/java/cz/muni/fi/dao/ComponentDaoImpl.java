package cz.muni.fi.dao;

import cz.muni.fi.entities.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Matus Macko
 */
@Repository
@Transactional
public class ComponentDaoImpl implements ComponentDao {
    @PersistenceContext
    private EntityManager em;

    public List<Component> listAvailableComponents() {
        return em.createQuery("select c from Component c where c.isAvailable = :bool", Component.class).setParameter("bool", true).getResultList();
    }

    public List<Component> listAllComponents() {
        return em.createQuery("select c from Component c", Component.class).getResultList();
    }

    public Component findComponentByName(String name) {
        if(name == null){
            throw new IllegalArgumentException("argument is null");
        }
        try {
            return em
                    .createQuery("select c from Component c where c.name = :name",
                            Component.class).setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    public Component findComponentById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("argument is null");
        }
        return em.find(Component.class, id);
    }

    public Component addComponent(Component component) {
        if(component == null){
            throw new IllegalArgumentException("argument is null");
        }
        em.persist(component);
        return component;
    }

    public Component updateComponent(Component component) {
        if(component == null){
            throw new IllegalArgumentException("argument is null");
        }
        em.merge(component);
        return component;
    }

    public Component deleteComponent(Component component) {
        if(component == null){
            throw new IllegalArgumentException("argument is null");
        }
        em.remove(em.merge(component));
        return component;
    }
}
