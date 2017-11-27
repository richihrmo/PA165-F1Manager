package cz.muni.fi.service.facade;

import cz.muni.fi.dto.DriverCreateDTO;
import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.entities.Driver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cz.muni.fi.facade.DriverFacade;
import cz.muni.fi.service.BeanMappingService;
import cz.muni.fi.service.DriverService;

/**
 * @author Lucie Kureckova, 445264
 */
@Service
@Transactional
public class DriverFacadeImpl implements DriverFacade {
    
    @Autowired
    private DriverService driverService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public List<DriverDTO> getAllDrivers() {
        return beanMappingService.mapTo(driverService.listDrivers(), DriverDTO.class);
    }

    @Override
    public List<DriverDTO> getAllTestDrivers() {
        return beanMappingService.mapTo(driverService.listTestDrivers(), DriverDTO.class);
    }

    @Override
    public DriverDTO getDriverByID(Long id) {
        Driver d = driverService.findDriverById(id);
        if(d == null){
            return null;
        }
        return beanMappingService.mapTo(d, DriverDTO.class);
    }

    @Override
    public DriverDTO getDriverByName(String name, String surname) {
        Driver d = driverService.findDriverByName(name, surname);
        if(d == null){
            return null;
        }
        return beanMappingService.mapTo(d, DriverDTO.class);
    }

    @Override
    public DriverDTO getTestDriverByName(String name, String surname) {
        Driver d = driverService.findTestDriver(name, surname);
        if(d == null){
            return null;
        }
        return beanMappingService.mapTo(d, DriverDTO.class);
    }

    @Override
    public void createDriver(DriverCreateDTO driver) {
        Driver d = beanMappingService.mapTo(driver, Driver.class);
        driverService.addDriver(d);
    }

    @Override
    public void updateDriver(DriverDTO driver) {
        Driver d = beanMappingService.mapTo(driver, Driver.class);
        driverService.updateDriver(d);
    }

    @Override
    public void deleteDriver(DriverDTO driver) {
        Driver d = beanMappingService.mapTo(driver, Driver.class);
        driverService.deleteDriver(d);
    }
    
}