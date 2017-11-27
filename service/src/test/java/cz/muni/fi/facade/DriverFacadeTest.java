package cz.muni.fi.facade;

import cz.muni.fi.dao.DriverDao;
import cz.muni.fi.dto.DriverCreateDTO;
import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.persistanceEnums.DrivingSkill;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.service.BeanMappingService;
import cz.muni.fi.service.config.ServiceConfiguration;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;

/**
 * @author Lucie Kureckova, 445264
 */
@DirtiesContext
@ContextConfiguration(classes = ServiceConfiguration.class)
public class DriverFacadeTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Autowired
    private DriverFacade driverFacade;
    
    @Autowired
    private DriverDao driverDao;

    @Autowired
    private BeanMappingService beanMappingService;
    
    private Driver driver;
    private DriverDTO driverDTO;
    private Driver testDriver;
    private DriverDTO testDriverDTO;
    
    @BeforeMethod
    public void prepareDriver(){
        driver = new Driver();
        driver.setAsMainDriver();
        driver.setName("Anakin");
        driver.setSurname("Skywalker");
        driver.setNationality("unknown");
        driver.setSpecialSkill(DrivingSkill.EXTREME_REFLEXES);
        
        driverDao.addDriver(driver);
        driverDTO = beanMappingService.mapTo(driverDao.findDriverByName(driver.getName(), driver.getSurname()), DriverDTO.class);
        
        testDriver = new Driver();
        testDriver.setAsTestDriver();
        testDriver.setName("Harry");
        testDriver.setSurname("Potter");
        testDriver.setNationality("unknown");
        testDriver.setSpecialSkill(DrivingSkill.POWER_SLIDING);
        
        driverDao.addDriver(testDriver);
        testDriverDTO = beanMappingService.mapTo(driverDao.findDriverByName(testDriver.getName(), testDriver.getSurname()), DriverDTO.class);
    }
    
    @Test
    public void getAllDriversTest(){
        assertThat(driverFacade.getAllDrivers()).containsExactlyInAnyOrder(driverDTO, testDriverDTO);
    }
    
    @Test
    public void getAllTestDriversTest(){
        assertThat(driverFacade.getAllTestDrivers()).containsExactlyInAnyOrder(testDriverDTO);
    }
    
    @Test
    public void getDriverByIDTest(){
        assertThat(driverFacade.getDriverByID(1L)).isEqualTo(driverDTO);
    }
    
    @Test
    public void getDriverByNameTest(){
        assertThat(driverFacade.getDriverByName(driverDTO.getName(), driverDTO.getSurname())).isEqualTo(driverDTO);
    }
    
    @Test
    public void getTestDriverByNameTest(){
        assertThat(driverFacade.getTestDriverByName(testDriverDTO.getName(), testDriverDTO.getSurname())).isEqualTo(testDriverDTO);
    }
    
    @Test
    public void createDriverTest(){
        DriverCreateDTO driver1 = new DriverCreateDTO();
        driver1.setName("Main");
        driver1.setSurname("Driver");
        driver1.setNationality("unknown");
        
        driverFacade.createDriver(driver1);
        assertThat(driverFacade.getDriverByName(driver1.getName(), driver1.getSurname())).isEqualToIgnoringGivenFields(driver1, "id", "mainDriver");
    }
    
    @Test
    public void updateDriverTest(){
        driverDTO.setName("Jax");
        driverFacade.updateDriver(driverDTO);
        assertThat(driverFacade.getDriverByID(driverDTO.getId())).isEqualTo(driverDTO);
    }
    
    @Test
    public void deleteDriverTest(){
        Long id = testDriverDTO.getId();
        driverFacade.deleteDriver(testDriverDTO);
        assertThat(driverFacade.getAllDrivers()).doesNotContain(testDriverDTO);
    }
}
