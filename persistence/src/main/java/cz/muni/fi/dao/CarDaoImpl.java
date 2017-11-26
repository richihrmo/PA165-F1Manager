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
        if(driver == null){
            throw new IllegalArgumentException("argument is null");
        }
        try {
            return em.createQuery("select c from Car c where c.driver = :driver", Car.class)
                    .setParameter("driver", driver).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Car findCarById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("argument is null");
        }
        return em.find(Car.class, id);
    }

    public void addCar(Car car) {
        if (car == null) throw new IllegalArgumentException("argument is null");
        if (car.getDriver() == null) throw new IllegalArgumentException("driver is null");
        if (car.getSuspension() == null) throw new IllegalArgumentException("suspension is null");
        if (car.getTransmission() == null) throw new IllegalArgumentException("transmission is null");
        if (car.getBrakes() == null) throw new IllegalArgumentException("brakes are null");
        if (car.getEngine() == null) throw new IllegalArgumentException("engine is null");
        if (car.getAerodynamics() == null) throw new IllegalArgumentException("aero is null");
        if (car.getId() == null) throw new IllegalArgumentException("id is null");

        em.persist(car);
    }

    public void updateCar(Car car) {
        if (car == null) throw new IllegalArgumentException("argument is null");
        if (car.getDriver() == null) throw new IllegalArgumentException("driver is null");
        if (car.getSuspension() == null) throw new IllegalArgumentException("suspension is null");
        if (car.getTransmission() == null) throw new IllegalArgumentException("transmission is null");
        if (car.getBrakes() == null) throw new IllegalArgumentException("brakes are null");
        if (car.getEngine() == null) throw new IllegalArgumentException("engine is null");
        if (car.getAerodynamics() == null) throw new IllegalArgumentException("aero is null");
        if (car.getId() == null) throw new IllegalArgumentException("id is null");

        em.merge(car);
    }

    public void deleteCar(Car car) {
        if (car == null) throw new IllegalArgumentException("argument is null");
        if (car.getId() == null) throw new IllegalArgumentException("id is null");

        em.remove(em.merge(car));
    }
}
