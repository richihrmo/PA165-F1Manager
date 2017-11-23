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
    public Driver findDriverById(Long id) throws IllegalArgumentException;

    /**
     * Find person-driver based on name
     *
     * @param name driver's name
     * @param surname driver's surname
     * @return Driver
     * @throws IllegalArgumentException when argumet is null
     */
    public Driver findDriverByName(String name, String surname) throws IllegalArgumentException;

    /**
     * Find person-test driver based on name
     *
     * @param name test driver's name
     * @param surname test driver's surname
     * @return Driver
     * @throws IllegalArgumentException when argumet is null
     */
    public Driver findTestDriver(String name, String surname) throws IllegalArgumentException;

    /**
     * Add driver
     *
     * @param driver Driver
     * @throws IllegalArgumentException when argumet is null
     */
    public void addDriver(Driver driver) throws IllegalArgumentException;

    /**
     * Update driver
     *
     * @param driver Driver
     * @throws IllegalArgumentException when argumet is null
     */
    public void updateDriver(Driver driver) throws IllegalArgumentException;

    /**
     * Delete driver
     *
     * @param driver Driver
     * @throws IllegalArgumentException when argumet is null
     */
    public void deleteDriver(Driver driver) throws IllegalArgumentException;

}
