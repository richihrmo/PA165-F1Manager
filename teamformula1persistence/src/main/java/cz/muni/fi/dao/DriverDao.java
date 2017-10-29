package cz.muni.fi.dao;

import cz.muni.fi.entities.Driver;

import java.util.List;

/**
 * @author Richard Hrmo
 */
public interface DriverDao{
    /**
     * List all drivers
     *
     * @return List<Driver>
     */
    public List<Driver> listDrivers();

    /**
     * List of drivers not listed as main drivers
     *
     * @return List<Driver>
     */
    public List<Driver> listTestDrivers();

    /**
     * Find person by ID
     *
     * @param id
     * @return Driver
     */
    public Driver findDriver(long id);

    /**
     * Find person-driver based on name
     *
     * @param name
     * @param surname
     * @return Driver
     */
    public Driver findDriver(String name, String surname);

    /**
     * Find person-test driver based on name
     *
     * @param name
     * @param surname
     * @return Driver
     */
    public Driver findTestDriver(String name, String surname);

    /**
     * Add driver
     *
     * @param driver
     */
    public void addDriver(Driver driver);

    /**
     * Update driver
     *
     * @param driver
     */
    public void updateDriver(Driver driver);

    /**
     * Delete driver
     *
     * @param driver
     */
    public void deleteDriver(Driver driver);

}
