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

    public Driver findDriverById(Long id) {
        if(id == null) throw new IllegalArgumentException("find: id is null");

        return em.find(Driver.class, id);
    }

    public Driver findDriverByName(String name, String surname) {
        if(name == null || surname == null) throw new IllegalArgumentException("find: name is null");

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
        if(name == null || surname == null){
            throw new IllegalArgumentException("find: testdriver is null");
        }
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
        if (driver == null) throw new IllegalArgumentException("add: driver is null");
        if (driver.getId() != null) throw new IllegalArgumentException("add: id must be null");
        if (driver.getName() == null) throw new IllegalArgumentException("add: name is null");
        if (driver.getNationality() == null) throw new IllegalArgumentException("add: nationality is null");
//        if (driver.getSpecialSkill() == null) throw new IllegalArgumentException("add: skill is null");
        if (driver.getSurname() == null) throw new IllegalArgumentException("add: surname is null");

        em.persist(driver);
    }

    public void updateDriver(Driver driver) {
        if (driver == null) throw new IllegalArgumentException("update: driver is null");
        if (driver.getId() == null) throw new IllegalArgumentException("update: id is null");
        if (driver.getName() == null) throw new IllegalArgumentException("update: name is null");
        if (driver.getNationality() == null) throw new IllegalArgumentException("update: nationality is null");
//        if (driver.getSpecialSkill() == null) throw new IllegalArgumentException("update: skill is null");
        if (driver.getSurname() == null) throw new IllegalArgumentException("update: surname is null");

        em.merge(driver);
    }

    public void deleteDriver(Driver driver) {
        if (driver == null) throw new IllegalArgumentException("delete: driver is null");
        if (driver.getId() == null) throw new IllegalArgumentException("delete: id is null");

        em.remove(em.merge(driver));
    }
}
