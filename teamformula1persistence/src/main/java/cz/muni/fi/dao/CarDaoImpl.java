package cz.muni.fi.dao;

import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Driver;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * @author Robert Tamas
 */
@Repository
public class CarDaoImpl implements CarDao{

    @PersistenceContext
    private EntityManager em;

    public List<Car> listAllCars() {
        return em.createQuery("select c from Car c", Car.class).getResultList();
    }

    public Car findCar(Driver driver) {
        try {
            return em.createQuery("select c from Car c where c.driver = :driver", Car.class)
                    .setParameter("driver", driver).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Car findCar(Long id) {
        return em.find(Car.class, id);
    }

    public void addCar(Car car) {
        em.persist(car);
    }

    public void updateCar(Car car) {
        em.merge(car);
    }

    public void deleteCar(Car car) {
        em.remove(em.merge(car));
    }
}
