import cz.muni.fi.entities.Driver;
import cz.muni.fi.dto.CarDTO;
import cz.muni.fi.facade.CarFacade;

import java.util.List;

/**
 * @author Richard Hrmo
 */
public class CarFacadeImpl implements CarFacade {
    @Override
    public CarDTO findCarById(long id) {
        return null;
    }

    @Override
    public CarDTO findCarByDriver(Driver driver) {
        return null;
    }

    @Override
    public List<CarDTO> listAllCars(CarDTO carDTO) {
        return null;
    }

    @Override
    public void createCar(CarDTO carDTO) {

    }

    @Override
    public void updateCar(CarDTO carDTO) {

    }

    @Override
    public void deleteCar(CarDTO carDTO) {

    }
}
