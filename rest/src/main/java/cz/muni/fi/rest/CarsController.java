package cz.muni.fi.rest;

import cz.muni.fi.dto.CarDTO;
import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.facade.CarFacade;
import cz.muni.fi.rest.exceptions.InvalidParameterException;
import cz.muni.fi.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.rest.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Richard Hrmo
 */

@RestController
@RequestMapping("/cars")
public class CarsController {

    @Autowired
    private CarFacade carFacade;

    /**
     * Create new car using POST method
     *
     * @param car CarDTO entity
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final void createCar(@RequestBody CarDTO car) throws Exception {
        try {
            carFacade.createCar(car);
        } catch (Exception e){
            throw new ResourceAlreadyExistingException();
        }
    }


    /**
     * update car using PUT method
     *
     * @param car CarDTO entity
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "/update",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final void updateCar(@RequestBody CarDTO car) throws Exception {
        try {
            carFacade.updateCar(car);
        } catch (Exception e){
            throw new InvalidParameterException();
        }
    }


    /**
     * Delete car using DELETE method
     *
     * @param car CarDTO entity
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/delete",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteCar(@RequestBody CarDTO car) throws Exception {
        try {
            carFacade.deleteCar(car);
        } catch (Exception e){
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Get car by id using GET method
     *
     * @param id long
     * @return carDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CarDTO getCar(@PathVariable("id") long id) throws Exception {
        CarDTO carDTO = carFacade.findCarById(id);
        if (carDTO != null) return carDTO;
        else throw new ResourceNotFoundException();
    }

    /**
     * Get car by driver using GET method
     *
     * @param driver DriverDTO
     * @return carDTO
     * @throws ResourceNotFoundException
     */
    // not sure what to add here as I need DTO parameter
    @RequestMapping(value = "/{}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CarDTO getCar(@RequestBody DriverDTO driver) throws Exception {
        CarDTO carDTO = carFacade.findCarByDriver(driver);
        if (carDTO != null) return carDTO;
        else throw new ResourceNotFoundException();
    }

    /**
     * Get all cars using GET method
     *
     * @return list of carDTO
     */
    @RequestMapping(value = "/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<CarDTO> getAllCars() {
        return carFacade.listAllCars();
    }

    /**
     * Get all cars by component name using GET method
     *
     * @param name component name
     * @return list of carDTO
     */
    @RequestMapping(value = "/all{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<CarDTO> getCarsByComponentName(@PathVariable("name") String name) {
        return carFacade.listCarsByComponentName(name);
    }


}
