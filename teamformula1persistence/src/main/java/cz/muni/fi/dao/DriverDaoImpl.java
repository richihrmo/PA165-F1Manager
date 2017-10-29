package cz.muni.fi.dao;

import cz.muni.fi.entities.Driver;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Richard Hrmo
 */
@Repository
@Transactional
public class DriverDaoImpl implements DriverDao{
    @PersistenceContext
    private EntityManager em;

    public List<Driver> listDrivers() {
        return em.createQuery("select d from Driver d", Driver.class)
                .getResultList();
    }

    public List<Driver> listTestDrivers() {
        return em.createQuery("select d from Driver d where ismaindriver = :ismaindriver", Driver.class)
                .setParameter("ismaindriver", false)
                .getResultList();
    }

    public Driver findDriver(long id) {
        return em.find(Driver.class, id);
    }

    public Driver findDriver(String name, String surname) {
        try {
            return em.createQuery("select d from Driver d where name = :name and surname = :surname", Driver.class)
                    .setParameter("name", name)
                    .setParameter("surname", surname)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    public Driver findTestDriver(String name, String surname) {
        try {
            return em.createQuery("select d from Driver d where ismaindriver = :ismaindriver and name = :name and surname = :surname", Driver.class)
                    .setParameter("name", name)
                    .setParameter("surname", surname)
                    .setParameter("ismaindriver", false)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
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
