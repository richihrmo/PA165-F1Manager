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
    public List<Car> listAllCars();

    /**
     * Find specific car by its driver
     *
     * @param driver driver
     * @return car with driver
     */
    public Car findCar(Driver driver);

    /**
     * Find specific car by its id
     *
     * @param id DB id
     * @return car
     */
    public Car findCar(Long id);

    /**
     * Adds car to DB
     *
     * @param car car to be added
     */
    public void addCar(Car car);

    /**
     * Updates car in DB
     *
     * @param car car to be updated
     */
    public void updateCar(Car car);

    /**
     * Deletes car from DB
     *
     * @param car car to be deleted
     */
    public void deleteCar(Car car);
}
