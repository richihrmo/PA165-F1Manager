package cz.muni.fi.service;

import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Driver;
import java.util.List;

/**
 * @author Richard Hrmo
 */

public interface CarService {

    /**
     * Returns entity based on Id
     *
     * @param id
     * @return Car entity
     * @throws org.springframework.dao.DataAccessException if there is problem with dao
     */
    Car findCarById(Long id);

    /**
     * Returns entity based on driver
     *
     * @param driver
     * @return Car entity
     * @throws org.springframework.dao.DataAccessException if there is problem with dao
     */
    Car findCarByDriver(Driver driver);

    /**
     * Returns List of all cars
     *
     * @return List of Car entities
     * @throws org.springframework.dao.DataAccessException if there is problem with dao
     */
    List<Car> listAllCars();

    /**
     * CRUD operation create
     *
     * @param car
     * @return car
     * @throws org.springframework.dao.DataAccessException if there is problem with dao
     */
    Car createCar(Car car);

    /**
     * CRUD operation update
     *
     * @param car
     * @return car
     * @throws org.springframework.dao.DataAccessException if there is problem with dao
     */
    Car updateCar(Car car);

    /**
     * CRUD operation delete
     *
     * @param car
     * @return boolean
     * @throws org.springframework.dao.DataAccessException if there is problem with dao
     */
    boolean deleteCar(Car car);

    /**
     * Returns list of cars using component of given name
     *
     * @param componentName string
     * @return List of Car entities
     * @throws org.springframework.dao.DataAccessException if there is problem with dao
     */
    List<Car> listCarsByComponentName(String componentName);

}