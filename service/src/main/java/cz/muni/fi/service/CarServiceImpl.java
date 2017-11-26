package cz.muni.fi.service;

import cz.muni.fi.dao.CarDao;
import cz.muni.fi.dto.CarDTO;
import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Driver;
import org.dozer.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Richard Hrmo
 */

public class CarServiceImpl implements CarService {

    @Inject
    private CarDao carDao;

    @Override
    public Car findCarById(Long id) {
        return carDao.findCarById(id);
    }

    @Override
    public Car findCarByDriver(Driver driver) {
        return carDao.findCarByDriver(driver);
    }

    @Override
    public List<Car> listAllCars() {
        return carDao.listAllCars();
    }

    @Override
    public void createCar(Car car) {
        carDao.addCar(car);
    }

    @Override
    public void updateCar(Car car) {
        carDao.updateCar(car);
    }

    @Override
    public void deleteCar(Car car) {
        carDao.deleteCar(car);
    }

    //TODO
    @Override
    public List<Car> listCarsByComponentName(String componentName) {
        List<Car> cars = listAllCars();
        List<Car> aero = cars.stream().filter(p -> p.getAerodynamics().getName().equals(componentName)).collect(Collectors.toList());
        List<Car> engine = cars.stream().filter(p -> p.getEngine().getName().equals(componentName)).collect(Collectors.toList());
        List<Car> brakes = cars.stream().filter(p -> p.getBrakes().getName().equals(componentName)).collect(Collectors.toList());
        List<Car> transmission = cars.stream().filter(p -> p.getTransmission().getName().equals(componentName)).collect(Collectors.toList());
        List<Car> suspension = cars.stream().filter(p -> p.getSuspension().getName().equals(componentName)).collect(Collectors.toList());

        List<Car> result = new ArrayList<>();
        result.addAll(aero);
        result.addAll(engine);
        result.addAll(brakes);
        result.addAll(transmission);
        result.addAll(suspension);
        return result;
    }
}
