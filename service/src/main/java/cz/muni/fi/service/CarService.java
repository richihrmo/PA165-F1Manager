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
     */
    Car findCarById(Long id);

    /**
     * Returns entity based on driver
     *
     * @param driver
     * @return Car entity
     */
    Car findCarByDriver(Driver driver);

    /**
     * Returns List of all cars
     *
     * @return List of Car entities
     */
    List<Car> listAllCars();

    /**
     * CRUD operation create
     *
     * @param car
     */
    Car createCar(Car car);

    /**
     * CRUD operation update
     *
     * @param car
     */
    Car updateCar(Car car);

    /**
     * CRUD operation delete
     *
     * @param car
     */
    boolean deleteCar(Car car);

    /**
     * Returns list of cars using component of given name
     *
     * @param componentName
     * @return
     */
    List<Car> listCarsByComponentName(String componentName);

}