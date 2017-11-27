package cz.muni.fi.service;

import cz.muni.fi.dao.CarDao;
import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.service.exception.TeamFormulaDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public Car findCarById(Long id) throws DataAccessException {
        if (id == null) throw new IllegalArgumentException("find by id argument is null");
        try {
            return carDao.findCarById(id);
        } catch (Throwable throwable) {
            throw new TeamFormulaDataAccessException("could not find by id car", throwable);
        }
    }

    @Override
    public Car findCarByDriver(Driver driver) throws DataAccessException {
        if (driver == null) throw new IllegalArgumentException("find by driver argument is null");
        try {
            return carDao.findCarByDriver(driver);
        } catch (Throwable throwable) {
            throw new TeamFormulaDataAccessException("could not find by driver car", throwable);
        }
    }

    @Override
    public List<Car> listAllCars() throws DataAccessException {
        try {
            return carDao.listAllCars();
        } catch (Throwable throwable) {
            throw new TeamFormulaDataAccessException("could not list all cars", throwable);
        }
    }

    @Override
    public Car createCar(Car car) throws DataAccessException {
        if (car == null) throw new IllegalArgumentException("car create argument is null");
        try {
            return carDao.addCar(car);
        } catch (Throwable throwable) {
            throw new TeamFormulaDataAccessException("could not create car", throwable);
        }
    }

    @Override
    public Car updateCar(Car car) throws DataAccessException {
        if (car == null) throw new IllegalArgumentException("car update argument is null");
        try {
            return carDao.updateCar(car);
        } catch (Throwable throwable) {
            throw new TeamFormulaDataAccessException("could not update car", throwable);
        }
    }

    @Override
    public boolean deleteCar(Car car) throws DataAccessException {
        if (car == null) throw new IllegalArgumentException("car delete argument is null");
        try {
            return carDao.deleteCar(car);
        } catch (Throwable throwable) {
            throw new TeamFormulaDataAccessException("could not delete car", throwable);
        }
    }

    @Override
    public List<Car> listCarsByComponentName(String componentName) throws DataAccessException {
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
