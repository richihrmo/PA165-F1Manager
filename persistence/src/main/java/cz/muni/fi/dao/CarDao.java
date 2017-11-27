package cz.muni.fi.dao;

import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Driver;

import java.util.List;

/**
 * @author Robert Tamas
 */
public interface CarDao {

    /**
     * List all cars which are in DB
     *
     * @return list of cars
     */
    List<Car> listAllCars();

    /**
     * Find specific car by its driver
     *
     * @param driver driver
     * @return car with driver
     * @throws IllegalArgumentException when argument is null
     */
    Car findCarByDriver(Driver driver);

    /**
     * Find specific car by its id
     *
     * @param id DB id
     * @return car
     * @throws IllegalArgumentException when argument is null
     */
    Car findCarById(Long id);

    /**
     * Adds car to DB
     *
     * @param car car to be added
     * @throws IllegalArgumentException when argument is null or not nullable attributes are null
     * @return car
     */
    Car addCar(Car car);

    /**
     * Updates car in DB
     *
     * @param car car to be updated
     * @throws IllegalArgumentException when argument is null or not nullable attributes are null
     * @return car
     */
    Car updateCar(Car car);

    /**
     * Deletes car from DB
     *
     * @param car car to be deleted
     * @throws IllegalArgumentException when argument is null or not nullable attributes are null
     * @return car
     */
    boolean deleteCar(Car car);
}
