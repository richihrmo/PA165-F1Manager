package cz.muni.fi.rest.controllers;

import cz.muni.fi.dto.DriverCreateDTO;
import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.facade.DriverFacade;
import cz.muni.fi.rest.ApiUris;
import cz.muni.fi.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.rest.exceptions.ResourceCouldNotBeDeleted;
import cz.muni.fi.rest.exceptions.ResourceNotFoundException;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucie Kureckova, 445264
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_DRIVER)
public class DriverController {
    
    final static Logger logger = LoggerFactory.getLogger(DriverController.class);

    @Inject
    private DriverFacade driverFacade;
    
    /**
     * Get list of driverDTO 
     * curl -i -X GET http://localhost:8080/pa165/rest/driver
     * 
     * @return List of DriverDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<DriverDTO> getDrivers() {
        
        logger.debug("rest getDrivers()");
        return driverFacade.getAllDrivers();
    }
    
    /**
     * Get specific driver by id.
     * curl -i -X GET http://localhost:8080/pa165/rest/driver/{id}
     * 
     * @param id identifier for driver
     * @return driverDTO
     * @throws ResourceNotFoundException if resource is not found
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final DriverDTO getDriver(@PathVariable("id") long id) throws Exception {

        logger.debug("rest getDriver({})", id);
        try{
            DriverDTO driverDTO = driverFacade.getDriverByID(id);
            return driverDTO;
        } catch(Exception ex){
            throw new ResourceNotFoundException();
        }
    }
    
    /**
     * Delete driver 
     * curl -i -X DELETE http://localhost:8080/pa165/rest/driver/delete/{id}
     * 
     * @param id identifier for driver
     * @throws ResourceNotFoundException if driver to delete is not found
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteDriver(@PathVariable("id") long id) throws Exception {
        logger.debug("rest deleteDriver({})", id);
        try{
            DriverDTO driverDTO = driverFacade.getDriverByID(id);
            if(driverDTO == null){
                throw new ResourceNotFoundException();
            }
            driverFacade.deleteDriver(driverDTO);
        } catch(Exception ex){
            throw new ResourceCouldNotBeDeleted();
        }
    }
    
    /**
     * Create driver. 
     * curl -X POST -i -H "Content-Type: application/json" --data 
     * '{"name":"test","surname":"test","nationality":"uk","specialSkill":"POWER_SLIDING"}' 
     * http://localhost:8080/pa165/rest/driver/create
     * 
     * @param driver to create
     * @return the created DriverDTO
     * @throws ResourceAlreadyExistingException if driver already exist
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final DriverDTO createProduct(@RequestBody DriverCreateDTO driver) throws Exception {

        logger.debug("rest createDriver()");

        try {
            driverFacade.createDriver(driver);
            return driverFacade.getDriverByName(driver.getName(), driver.getSurname());
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }
    
    /**
     * Get list of test drivers. 
     * curl -i -X GET http://localhost:8080/pa165/rest/driver/show-testDrivers
     * 
     * @return list of test DriverDTO
     */
    @RequestMapping(value = "/show-testDrivers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<DriverDTO> getTestDrivers(){
        
        logger.debug("rest getTestDrivers()");
        return driverFacade.getAllTestDrivers();
    }
}
