package cz.muni.fi.service;

import cz.muni.fi.dao.CarDao;
import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.service.exception.ServiceDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Richard Hrmo
 */

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Override
    public Car findCarById(Long id) {
        if (id == null) throw new IllegalArgumentException("find by id argument is null");
        try {
            return carDao.findCarById(id);
        } catch (Throwable throwable) {
            throw new ServiceDataAccessException("could not find by id car", throwable);
        }
    }

    @Override
    public Car findCarByDriver(Driver driver) {
        if (driver == null) throw new IllegalArgumentException("find by driver argument is null");
        try {
            return carDao.findCarByDriver(driver);
        } catch (Throwable throwable) {
            throw new ServiceDataAccessException("could not find by driver car", throwable);
        }
    }

    @Override
    public List<Car> listAllCars() {
        try {
            return carDao.listAllCars();
        } catch (Throwable throwable) {
            throw new ServiceDataAccessException("could not list all cars", throwable);
        }
    }

    @Override
    public Car createCar(Car car) {
        if (car == null) throw new IllegalArgumentException("car create argument is null");
        try {
            return carDao.addCar(car);
        } catch (Throwable throwable) {
            throw new ServiceDataAccessException("could not create car", throwable);
        }
    }

    @Override
    public Car updateCar(Car car) {
        if (car == null) throw new IllegalArgumentException("car update argument is null");
        try {
            return carDao.updateCar(car);
        } catch (Throwable throwable) {
            throw new ServiceDataAccessException("could not update car", throwable);
        }
    }

    @Override
    public boolean deleteCar(Car car) {
        if (car == null) throw new IllegalArgumentException("car delete argument is null");
        try {
            return carDao.deleteCar(car);
        } catch (Throwable throwable) {
            throw new ServiceDataAccessException("could not delete car", throwable);
        }
    }

    @Override
    public List<Car> listCarsByComponentName(String componentName) {
        List<Car> cars = listAllCars();
        List<Car> result = new ArrayList<>();
        result.addAll(cars.stream().filter(p -> p.getAerodynamics().getName().equals(componentName)).collect(Collectors.toList()));
        result.addAll(cars.stream().filter(p -> p.getEngine().getName().equals(componentName)).collect(Collectors.toList()));
        result.addAll(cars.stream().filter(p -> p.getBrakes().getName().equals(componentName)).collect(Collectors.toList()));
        result.addAll(cars.stream().filter(p -> p.getTransmission().getName().equals(componentName)).collect(Collectors.toList()));
        result.addAll(cars.stream().filter(p -> p.getSuspension().getName().equals(componentName)).collect(Collectors.toList()));
        return result;
    }
}
