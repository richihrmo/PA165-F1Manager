package facade;

import cz.muni.fi.entities.Driver;
import dto.CarCreateDTO;
import dto.CarDTO;

import java.util.List;

/**
 * @author Richard Hrmo
 */
public interface CarFacade {

    /**
     * Returns entity based on Id
     * @param id
     * @return Car entity
     */
    CarDTO findCarById(long id);

    /**
     * Returns entity based on driver
     * @param driverDTO
     * @return Car entity
     */
    CarDTO findCarByDriver(DriverDTO driverDTO);

    /**
     * Returns List of all cars
     * @param carDTO
     * @return List of Car entities
     */
    List<CarDTO> listAllCars(CarDTO carDTO);

    /**
     * CRUD operation create
     * @param carCreateDTO
     */
    void createCar(CarCreateDTO carCreateDTO);

    /**
     * CRUD operation update
     * @param carDTO
     */
    void updateCar(CarDTO carDTO);

    /**
     * CRUD operation delete
     * @param carDTO
     */
    void deleteCar(CarDTO carDTO);

}
