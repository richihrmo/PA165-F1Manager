package cz.muni.fi;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Richard Hrmo
 */
@Repository
public class DriverDaoImpl implements DriverDao{
    @PersistenceContext
    private EntityManager em;

    public List<Driver> listDrivers() {
        return em.createQuery("select d from Driver d", Driver.class)
                .getResultList();
    }

    public List<Driver> listTestDrivers() {
        return em.createQuery("select d from Driver d where d.istest = false", Driver.class)
                .getResultList();
    }

    public Driver findDriver(long id) {
        return em.find(Driver.class, id);
    }

    public Driver findDriver(String name, String surname) {
        return em.createQuery("select d from Driver d where name = :name and surname = :surname", Driver.class)
                .setParameter(":name", name)
                .setParameter(":surname", surname)
                .getSingleResult();
    }

    public Driver findTestDriver(String name, String surname) {
        return em.createQuery("select d from Driver d where d.istest = false and name = :name and surname = :surname", Driver.class)
                .setParameter(":name", name)
                .setParameter(":surname", surname)
                .getSingleResult();
    }

    public void addDriver(Driver driver) {
        em.persist(driver);
    }

    public void updateDriver(Driver driver) {
        em.merge(driver);
    }

    public void deleteDriver(Driver driver) {
        em.remove(em.merge(driver));
    }
}
