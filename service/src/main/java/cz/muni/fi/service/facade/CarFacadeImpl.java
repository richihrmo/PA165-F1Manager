package cz.muni.fi.service.facade;

import cz.muni.fi.dto.CarCreateDTO;
import cz.muni.fi.dto.CarDTO;
import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.entities.Car;
import cz.muni.fi.facade.CarFacade;
import cz.muni.fi.service.BeanMappingService;
import cz.muni.fi.service.CarService;
import cz.muni.fi.service.DriverService;
import org.dozer.inject.Inject;

import java.util.List;

/**
 * @author Richard Hrmo
 */
public class CarFacadeImpl implements CarFacade {

    @Inject
    private CarService carService;

    @Inject
    private DriverService driverService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public CarDTO findCarById(Long id) {
        Car car = carService.findCarById(id);
        if (car == null) return null;
        CarDTO carDTO = beanMappingService.mapTo(car, CarDTO.class);
        return carDTO;
    }
    @Override
    public CarDTO findCarByDriver(DriverDTO driverDTO) {
        Car car = carService.findCarByDriver(driverService.findDriverById(driverDTO.getId()));
        if (car == null) return null;
        CarDTO carDTO = beanMappingService.mapTo(car, CarDTO.class);
        return carDTO;
    }

    @Override
    public List<CarDTO> listAllCars() {
        List<Car> cars = carService.listAllCars();
        if (cars.isEmpty()) return null;
        List<CarDTO> carDTOS = beanMappingService.mapTo(cars, CarDTO.class);
        return carDTOS;
    }

    @Override
    public void createCar(CarCreateDTO carCreateDTO) {
        if (carCreateDTO == null) throw new IllegalArgumentException("null carCreateDTO, cannot create");
        carService.createCar(beanMappingService.mapTo(carCreateDTO, Car.class));
    }

    @Override
    public void updateCar(CarDTO carDTO) {
        if (carDTO == null) throw new IllegalArgumentException("null carDTO, cannot update");
        carService.updateCar(beanMappingService.mapTo(carDTO, Car.class));

    }

    @Override
    public void deleteCar(CarDTO carDTO) {
        if (carDTO == null) throw new IllegalArgumentException("null carDTO, cannot delete");
        carService.deleteCar(beanMappingService.mapTo(carDTO, Car.class));

    }

    @Override
    public List<CarDTO> listCarsByComponentName(String componentName) {
        if (componentName.isEmpty()) throw new IllegalArgumentException("null componentName, cannot list cars");
        List<Car> cars = carService.listCarsByComponentName(componentName);
        List<CarDTO> carDTOS = beanMappingService.mapTo(cars, CarDTO.class);
        return carDTOS;
    }
}
