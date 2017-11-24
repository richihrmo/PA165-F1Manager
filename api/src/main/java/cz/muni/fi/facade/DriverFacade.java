/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.facade;

import cz.muni.fi.dto.DriverCreateDTO;
import cz.muni.fi.dto.DriverDTO;
import java.util.List;

/**
 * @author Lucie Kurečková, 445264
 */
public interface DriverFacade {
    /**
     * Find all drivers
     * 
     * @return list of DriverDTO
     */
    public List<DriverDTO> getAllDrivers();
    /**
     * Find all test drivers
     * 
     * @return list of DriverDTO
     */
    public List<DriverDTO> getAllTestDrivers();
    /**
     * Find specific driver by id
     * 
     * @param id wanted driver
     * @return DriverDTO
     */
    public DriverDTO getDriverByID(Long id);
    /**
     * Find driver by name and surname
     * 
     * @param name of wanted driver
     * @param surname of wanted driver
     * @return DriverDTO
     */
    public DriverDTO getDriverByName(String name, String surname);
    /**
     * Find test driver by name and surname
     * 
     * @param name of wanted driver
     * @param surname of wanted driver
     * @return DriverDTO
     */
    public DriverDTO getTestDriverByName(String name, String surname);
    /**
     * Create driver
     * 
     * @param driver to create
     * @return id of created driver
     */
    public Long createDriver(DriverCreateDTO driver);
    /**
     * Delete driver
     * 
     * @param id of driver to delete
     */
    public void deteleDriver(Long id);
}
