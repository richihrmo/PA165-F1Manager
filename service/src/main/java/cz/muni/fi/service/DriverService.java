package cz.muni.fi.service;

import cz.muni.fi.entities.Driver;
import java.util.List;
import org.springframework.stereotype.Service;
import cz.muni.fi.service.exception.ServiceDataAccessException;

/**
 * @author Lucie Kureckova, 445264
 */
@Service
public interface DriverService {
    
    /**
     * Find all drivers
     * 
     * @return list of drivers
     * @throws ServiceDataAccessException when DAO throws some exception
     */
    List<Driver> listDrivers();
    
    /**
     * Find all test drivers
     * 
     * @return list of test drivers
     * @throws ServiceDataAccessException when DAO throws some exception
     */
    List<Driver> listTestDrivers();
    
    /**
     * Find driver by id
     * 
     * @param id of wanted driver
     * @return wanted driver
     * @throws ServiceDataAccessException when DAO throws some exception
     * @throws IllegalArgumentException when argument is null
     */
    Driver findDriverById(Long id);
    
    /**
     * Find driver by name
     * 
     * @param name of driver
     * @param surname of driver
     * @return wanted driver
     * @throws ServiceDataAccessException when DAO throws some exception
     * @throws IllegalArgumentException when some argument is null
     */
    Driver findDriverByName(String name, String surname);
    
    /**
     * Find test driver by name
     * 
     * @param name of driver
     * @param surname of driver
     * @return wanted driver
     * @throws ServiceDataAccessException when DAO throws some exception
     * @throws IllegalArgumentException when some argument is null
     */
    Driver findTestDriver(String name, String surname);
    
    /**
     * Add driver
     * 
     * @param driver to add
     * @throws ServiceDataAccessException when DAO throws some exception
     * @throws IllegalArgumentException when argument is null
     */
    void addDriver(Driver driver);
    
    /**
     * Update driver
     * 
     * @param driver to update
     * @throws ServiceDataAccessException when DAO throws some exception
     * @throws IllegalArgumentException when argument is null
     */
    void updateDriver(Driver driver);
    
    /**
     * Delete driver
     * 
     * @param driver to delete
     * @throws ServiceDataAccessException when DAO throws some exception
     * @throws IllegalArgumentException when argument is null
     */
    void deleteDriver(Driver driver);
}