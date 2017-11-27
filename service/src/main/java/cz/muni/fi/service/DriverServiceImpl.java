package cz.muni.fi.service;

import cz.muni.fi.dao.DriverDao;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.service.exception.ServiceDataAccessException;
import org.dozer.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lucie Kureckova, 445264
 */
@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverDao driverDao;
    
    @Override
    public List<Driver> listDrivers() {
        try{
            return driverDao.listDrivers();
        } catch(Throwable throwable) {
            throw new ServiceDataAccessException("could not list all drivers", throwable);
        }
    }

    @Override
    public List<Driver> listTestDrivers() {
        try{
            return driverDao.listTestDrivers();
        } catch(Throwable throwable) {
            throw new ServiceDataAccessException("could not list all test drivers", throwable);
        }
    }

    @Override
    public Driver findDriverById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id argument cannot be null!");
        }
        try{
            return driverDao.findDriverById(id);
        } catch(Throwable throwable){
            throw new ServiceDataAccessException("could not find driver by id", throwable);
        }
    }

    @Override
    public Driver findDriverByName(String name, String surname) {
        if (name == null || surname == null) {
            throw new IllegalArgumentException("name or surname cannot be null!");
        }
        try{
            return driverDao.findDriverByName(name, surname);
        } catch(Throwable throwable){
            throw new ServiceDataAccessException("could not find driver by name and surname", throwable);
        }
    }

    @Override
    public Driver findTestDriver(String name, String surname) {
        if (name == null || surname == null) {
            throw new IllegalArgumentException("name or surname cannot be null!");
        }
        try{
            return driverDao.findTestDriver(name, surname);
        } catch(Throwable throwable){
            throw new ServiceDataAccessException("could not find test driver by name and surname", throwable);
        }
    }

    @Override
    public void addDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("driver argument cannot be null!");
        }
        try{
            driverDao.addDriver(driver);
        } catch(Throwable throwable){
            throw new ServiceDataAccessException("could not create a driver", throwable);
        }
    }

    @Override
    public void updateDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("driver argument cannot be null!");
        }
        try{
            driverDao.updateDriver(driver);
        } catch(Throwable throwable){
            throw new ServiceDataAccessException("could not update a driver", throwable);
        }
    }

    @Override
    public void deleteDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("driver argument cannot be null!");
        }
        try{
            driverDao.deleteDriver(driver);
        } catch(Throwable throwable){
            throw new ServiceDataAccessException("could not delete a driver", throwable);
        }
    }
}