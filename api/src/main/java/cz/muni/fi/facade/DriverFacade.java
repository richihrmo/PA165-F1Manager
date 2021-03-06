package cz.muni.fi.facade;

import cz.muni.fi.dto.DriverCreateDTO;
import cz.muni.fi.dto.DriverDTO;
import java.util.List;

/**
 * @author Lucie Kureckova, 445264
 */
public interface DriverFacade {
    
    /**
     * Find all drivers
     * 
     * @return list of DriverDTO
     */
    List<DriverDTO> getAllDrivers();
    
    /**
     * Find all test drivers
     * 
     * @return list of DriverDTO
     */
    List<DriverDTO> getAllTestDrivers();
    
    /**
     * Find specific driver by id
     * 
     * @param id wanted driver
     * @return DriverDTO
     */
    DriverDTO getDriverByID(Long id);
    
    /**
     * Find driver by name and surname
     * 
     * @param name of wanted driver
     * @param surname of wanted driver
     * @return DriverDTO
     */
    DriverDTO getDriverByName(String name, String surname);
    
    /**
     * Find test driver by name and surname
     * 
     * @param name of wanted driver
     * @param surname of wanted driver
     * @return DriverDTO
     */
    DriverDTO getTestDriverByName(String name, String surname);
    
    /**
     * Create driver
     * 
     * @param driver to create
     */
    void createDriver(DriverCreateDTO driver);
    
    /**
     * Update driver
     * 
     * @param driver to update
     */
    void updateDriver(DriverDTO driver);
    
    /**
     * Delete driver
     * 
     * @param driver to delete
     */
    void deleteDriver(DriverDTO driver);
}