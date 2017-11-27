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
     * @param id driver id
     * @return Driver
     * @throws IllegalArgumentException when argumet is null
     */
    public Driver findDriverById(Long id);

    /**
     * Find person-driver based on name
     *
     * @param name driver's name
     * @param surname driver's surname
     * @return Driver
     * @throws IllegalArgumentException when some argumet is null
     */
    public Driver findDriverByName(String name, String surname);

    /**
     * Find person-test driver based on name
     *
     * @param name test driver's name
     * @param surname test driver's surname
     * @return Driver
     * @throws IllegalArgumentException when some argumet is null
     */
    public Driver findTestDriver(String name, String surname);

    /**
     * Add driver
     *
     * @param driver Driver
     * @return Driver
     * @throws IllegalArgumentException when argument is null, or not nullable attributes are null,
     *         or id is not null
     */
    public Driver addDriver(Driver driver);

    /**
     * Update driver
     *
     * @param driver Driver
     * @return Driver
     * @throws IllegalArgumentException when argument is null or not nullable component attributes are null
     */
    public Driver updateDriver(Driver driver);

    /**
     * Delete driver
     *
     * @param driver Driver
     * @return Driver
     * @throws IllegalArgumentException when argument is null or not nullable component attributes are null
     */
    public Driver deleteDriver(Driver driver);

}
