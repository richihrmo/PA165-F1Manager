package cz.muni.fi.service;

import cz.muni.fi.dao.DriverDao;
import cz.muni.fi.entities.Driver;
import java.util.List;
import org.dozer.inject.Inject;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @author Lucie Kureckova, 445264
 */
@Service
public class DriverServiceImpl implements DriverService {

    @Inject
    private DriverDao driverDao;
    
    @Override
    public List<Driver> listDrivers() {
        return driverDao.listDrivers();
    }

    @Override
    public List<Driver> listTestDrivers() {
        return driverDao.listTestDrivers();
    }

    @Override
    public Driver findDriverById(Long id) {
        try{
            return driverDao.findDriverById(id);
        } catch(Exception ex){
            throw new DataAccessException(ex.getMessage()) {};
        }
    }

    @Override
    public Driver findDriverByName(String name, String surname) {
        try{
            return driverDao.findDriverByName(name, surname);
        } catch(Exception ex){
            throw new DataAccessException(ex.getMessage()) {};
        }
    }

    @Override
    public Driver findTestDriver(String name, String surname) {
        try{
            return driverDao.findTestDriver(name, surname);
        } catch(Exception ex){
            throw new DataAccessException(ex.getMessage()) {};
        }
    }

    @Override
    public void addDriver(Driver driver) {
        try{
            driverDao.addDriver(driver);
        } catch(Exception ex){
            throw new DataAccessException(ex.getMessage()) {};
        }
    }

    @Override
    public void updateDriver(Driver driver) {
        try{
            driverDao.updateDriver(driver);
        } catch(Exception ex){
            throw new DataAccessException(ex.getMessage()) {};
        }
    }

    @Override
    public void deleteDriver(Driver driver) {
        try{
            driverDao.deleteDriver(driver);
        } catch(Exception ex){
            throw new DataAccessException(ex.getMessage()) {};
        }
    }
}