package cz.muni.fi.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.muni.fi.dto.DriverCreateDTO;
import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.facade.DriverFacade;
import cz.muni.fi.rest.ApiUris;
import cz.muni.fi.rest.exceptions.ResourceAlreadyExistingException;
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

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<DriverDTO> getDrivers() throws JsonProcessingException {
        
        logger.debug("rest getDrivers()");
        return driverFacade.getAllDrivers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final DriverDTO getDriver(@PathVariable("id") long id) throws Exception {

        logger.debug("rest getDriver({})", id);driverFacade.getDriverByID(id);
        try{
            DriverDTO driverDTO = driverFacade.getDriverByID(id);
            return driverDTO;
        } catch(Exception ex){
            throw new ResourceNotFoundException();
        }
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteDriver(@PathVariable("id") long id) throws Exception {
        logger.debug("rest deleteDriver({})", id);
        try{
            DriverDTO driverDTO = driverFacade.getDriverByID(id);
            driverFacade.deleteDriver(driverDTO);
        } catch(Exception ex){
            throw new ResourceNotFoundException();
        }
    }
    
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
    
    @RequestMapping(value = "/show-testDrivers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<DriverDTO> getTestDrivers() throws JsonProcessingException {
        
        logger.debug("rest getTestDrivers()");
        return driverFacade.getAllTestDrivers();
    }
}
