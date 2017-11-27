package cz.muni.fi.service;

import cz.muni.fi.dao.DriverDao;
import cz.muni.fi.entities.Driver;
import java.util.List;
import org.dozer.inject.Inject;
import cz.muni.fi.service.exception.TeamFormulaDataAccessException;
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
        try{
            return driverDao.listDrivers();
        } catch(Throwable ex){
            throw new FormulaTeamDataAccessException(ex.getMessage()) {};
    }

    @Override
    public List<Driver> listTestDrivers() {
        try{
            return driverDao.listTestDrivers();
        } catch(Throwable ex){
            throw new FormulaTeamDataAccessException(ex.getMessage()) {};
    }

    @Override
    public Driver findDriverById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id argument cannot be null!");
        }
        try{
            return driverDao.findDriverById(id);
        } catch(Throwable ex){
            throw new FormulaTeamDataAccessException(ex.getMessage()) {};
        }
    }

    @Override
    public Driver findDriverByName(String name, String surname) {
        if (name == null || surname == null) {
            throw new IllegalArgumentException("name or surname cannot be null!");
        }
        try{
            return driverDao.findDriverByName(name, surname);
        } catch(Throwable ex){
            throw new FormulaTeamDataAccessException(ex.getMessage()) {};
        }
    }

    @Override
    public Driver findTestDriver(String name, String surname) {
        if (name == null || surname == null) {
            throw new IllegalArgumentException("name or surname cannot be null!");
        }
        try{
            return driverDao.findTestDriver(name, surname);
        } catch(Throwable ex){
            throw new FormulaTeamDataAccessException(ex.getMessage()) {};
        }
    }

    @Override
    public void addDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("driver argument cannot be null!");
        }
        try{
            driverDao.addDriver(driver);
        } catch(Throwable ex){
            throw new FormulaTeamDataAccessException(ex.getMessage()) {};
        }
    }

    @Override
    public void updateDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("driver argument cannot be null!");
        }
        try{
            driverDao.updateDriver(driver);
        } catch(Throwable ex){
            throw new FormulaTeamDataAccessException(ex.getMessage()) {};
        }
    }

    @Override
    public void deleteDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("driver argument cannot be null!");
        }
        try{
            driverDao.deleteDriver(driver);
        } catch(Throwable ex){
            throw new FormulaTeamDataAccessException(ex.getMessage()) {};
        }
    }
}