package cz.muni.fi.service;

import cz.muni.fi.entities.Driver;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author Lucie Kureckova, 445264
 */
@Service
public interface DriverService {
    
    /**
     * Find all drivers
     * 
     * @return list of drivers
     */
    List<Driver> listDrivers();
    
    /**
     * Find all test drivers
     * 
     * @return list of test drivers
     */
    List<Driver> listTestDrivers();
    
    /**
     * Find driver by id
     * 
     * @param id of wanted driver
     * @return wanted driver
     * @throws DataAccessException when DAO throws some exception
     */
    Driver findDriverById(Long id);
    
    /**
     * Find driver by name
     * 
     * @param name of driver
     * @param surname of driver
     * @return wanted driver
     * @throws DataAccessException when DAO throws some exception
     */
    Driver findDriverByName(String name, String surname);
    
    /**
     * Find test driver by name
     * 
     * @param name of driver
     * @param surname of driver
     * @return wanted driver
     * @throws DataAccessException when DAO throws some exception
     */
    Driver findTestDriver(String name, String surname);
    
    /**
     * Add driver
     * 
     * @param driver to add
     * @throws DataAccessException when DAO throws some exception
     */
    void addDriver(Driver driver);
    
    /**
     * Update driver
     * 
     * @param driver to update
     * @throws DataAccessException when DAO throws some exception
     */
    void updateDriver(Driver driver);
    
    /**
     * Delete driver
     * 
     * @param driver to delete
     * @throws DataAccessException when DAO throws some exception
     */
    void deleteDriver(Driver driver);
}