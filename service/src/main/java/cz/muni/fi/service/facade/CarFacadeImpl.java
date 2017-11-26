package cz.muni.fi.service.facade;

import cz.muni.fi.dto.CarCreateDTO;
import cz.muni.fi.dto.CarDTO;
import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.facade.CarFacade;

import java.util.List;

/**
 * @author Richard Hrmo
 */
public class CarFacadeImpl implements CarFacade {
    @Override
    public CarDTO findCarById(Long id) {
        return null;
    }

    @Override
    public CarDTO findCarByDriver(DriverDTO driverDTO) {
        return null;
    }

    @Override
    public List<CarDTO> listAllCars() {
        return null;
    }

    @Override
    public void createCar(CarCreateDTO carCreateDTO) {

    }

    @Override
    public void updateCar(CarDTO carDTO) {

    }

    @Override
    public void deleteCar(CarDTO carDTO) {

    }

    @Override
    public List<CarDTO> listCarsByComponentName(String componentName) {
        return null;
    }
}
