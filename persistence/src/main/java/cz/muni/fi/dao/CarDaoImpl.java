package cz.muni.fi.dao;

import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Driver;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


/**
 * @author Robert Tamas
 */
@Repository
@Transactional
public class CarDaoImpl implements CarDao{

    @PersistenceContext
    private EntityManager em;

    public List<Car> listAllCars() {
        return em.createQuery("select c from Car c", Car.class).getResultList();
    }

    public Car findCarByDriver(Driver driver) {
        if (driver == null) throw new IllegalArgumentException("find: name is null");

        try {
            return em.createQuery("select c from Car c where c.driver = :driver", Car.class)
                    .setParameter("driver", driver).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Car findCarById(Long id) {
        if (id == null) throw new IllegalArgumentException("find: id is null");

        return em.find(Car.class, id);
    }

    public Car addCar(Car car) {
        if (car == null) throw new IllegalArgumentException("add: car is null");
        if (car.getDriver() == null) throw new IllegalArgumentException("add: driver is null");
        if (car.getSuspension() == null) throw new IllegalArgumentException("add: suspension is null");
        if (car.getTransmission() == null) throw new IllegalArgumentException("add: transmission is null");
        if (car.getBrakes() == null) throw new IllegalArgumentException("add: brakes are null");
        if (car.getEngine() == null) throw new IllegalArgumentException("add: engine is null");
        if (car.getAerodynamics() == null) throw new IllegalArgumentException("add: aero is null");
        if (car.getId() != null) throw new IllegalArgumentException("add: id must be null");

        em.persist(car);
        return car;
    }

    public Car updateCar(Car car) {
        if (car == null) throw new IllegalArgumentException("update: car is null");
        if (car.getDriver() == null) throw new IllegalArgumentException("update: driver is null");
        if (car.getSuspension() == null) throw new IllegalArgumentException("update: suspension is null");
        if (car.getTransmission() == null) throw new IllegalArgumentException("update: transmission is null");
        if (car.getBrakes() == null) throw new IllegalArgumentException("update: brakes are null");
        if (car.getEngine() == null) throw new IllegalArgumentException("update: engine is null");
        if (car.getAerodynamics() == null) throw new IllegalArgumentException("update: aero is null");
        if (car.getId() == null) throw new IllegalArgumentException("update: id is null");

        em.merge(car);
        return car;
    }

    public boolean deleteCar(Car car) {
        if (car == null) throw new IllegalArgumentException("delete: car is null");
        if (car.getId() == null) throw new IllegalArgumentException("delete: id is null");

        em.remove(em.merge(car));
        return true;
    }
}
