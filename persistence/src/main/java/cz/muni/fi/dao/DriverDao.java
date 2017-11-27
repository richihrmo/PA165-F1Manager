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
     * @throws IllegalArgumentException when argumet is null
     */
    public Driver findDriverByName(String name, String surname);

    /**
     * Find person-test driver based on name
     *
     * @param name test driver's name
     * @param surname test driver's surname
     * @return Driver
     * @throws IllegalArgumentException when argumet is null
     */
    public Driver findTestDriver(String name, String surname);

    /**
     * Add driver
     *
     * @param driver Driver
     * @return Driver
     * @throws IllegalArgumentException when argumet is null
     */
<<<<<<< HEAD
<<<<<<< HEAD
    public void addDriver(Driver driver);
=======
    public Driver addDriver(Driver driver) throws IllegalArgumentException;
>>>>>>> facade test and service tests
=======
    public Driver addDriver(Driver driver);
>>>>>>> update DriverDao

    /**
     * Update driver
     *
     * @param driver Driver
     * @return Driver
     * @throws IllegalArgumentException when argumet is null
     */
<<<<<<< HEAD
<<<<<<< HEAD
    public void updateDriver(Driver driver);
=======
    public Driver updateDriver(Driver driver) throws IllegalArgumentException;
>>>>>>> facade test and service tests
=======
    public Driver updateDriver(Driver driver);
>>>>>>> update DriverDao

    /**
     * Delete driver
     *
     * @param driver Driver
     * @return Driver
     * @throws IllegalArgumentException when argumet is null
     */
<<<<<<< HEAD
<<<<<<< HEAD
    public void deleteDriver(Driver driver);
=======
    public Driver deleteDriver(Driver driver) throws IllegalArgumentException;
>>>>>>> facade test and service tests
=======
    public Driver deleteDriver(Driver driver);
>>>>>>> update DriverDao

}
