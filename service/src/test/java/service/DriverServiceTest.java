package service;

import cz.muni.fi.dao.DriverDao;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.enums.DrivingSkill;
import cz.muni.fi.service.DriverService;
import cz.muni.fi.service.config.ServiceConfiguration;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Lucie Kureckova, 445264
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class DriverServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Mock
    private DriverDao driverDao;
    
    @Autowired
    @InjectMocks
    private DriverService driverService;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    private Driver driver;
    private Driver testDriver;
    private List<Driver> result;
    
    @BeforeMethod
    public void prepareDriver(){
        driver = new Driver();
        driver.setAsMainDriver();
        driver.setName("Anakin");
        driver.setSurname("Skywalker");
        driver.setSpecialSkill(DrivingSkill.EXTREME_REFLEXES);
        
        testDriver = new Driver();
        testDriver.setAsTestDriver();
        testDriver.setName("Harry");
        testDriver.setSurname("Potter");
        testDriver.setSpecialSkill(DrivingSkill.POWER_SLIDING);
        
        result.add(driver);
        result.add(testDriver);
    }
    
    @Test
    public void listDriversTest(){
        when(driverDao.listDrivers()).thenReturn(result);
        assertThat(driverService.listDrivers()).containsExactlyInAnyOrder(driver, testDriver);
        assertThat(driverService.listDrivers()).doesNotContain(new Driver());
    }
    
    @Test
    public void listNoDriversTest(){
        when(driverDao.listDrivers()).thenReturn(new ArrayList<Driver>());
        assertThat(driverService.listDrivers()).doesNotContain(driver);
        assertThat(driverService.listDrivers()).isEmpty();
    }
    
    @Test
    public void listTestDriversTest(){
        result.remove(driver);
        when(driverDao.listTestDrivers()).thenReturn(result);
        assertThat(driverService.listTestDrivers()).containsExactlyInAnyOrder(testDriver);
        assertThat(driverService.listTestDrivers()).doesNotContain(driver);
    }
    
    @Test
    public void listNoTestDriversTest(){
        when(driverDao.listTestDrivers()).thenReturn(new ArrayList<Driver>());
        assertThat(driverService.listTestDrivers()).isEmpty();
    }
    
    @Test
    public void findDriverByIdTest(){
        when(driverDao.findDriverById(1L)).thenReturn(testDriver);
        when(driverDao.findDriverById(2L)).thenReturn(null);
        assertThat(driverService.findDriverById(1L)).isEqualTo(testDriver);
        assertThat(driverService.findDriverById(2L)).isNull();
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void findDriverByNullIdTest(){
        when(driverDao.findDriverById(null)).thenThrow(new IllegalArgumentException());
        driverService.findDriverById(null);
    }
    
    @Test
    public void findDriverByNameTest(){
        when(driverDao.findDriverByName(driver.getName(), driver.getSurname())).thenReturn(driver);
        when(driverDao.findDriverByName("lola", "neni")).thenReturn(null);
        assertThat(driverService.findDriverByName(driver.getName(), driver.getSurname())).isEqualTo(driver);
        assertThat(driverService.findDriverByName("lola", "neni")).isNull();
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void findDriverByNullNameTest(){
        when(driverDao.findDriverByName(null, null)).thenThrow(new IllegalArgumentException());
        driverService.findDriverByName(null, null);
    }
    
    @Test
    public void findTestDriverTest(){
        when(driverDao.findTestDriver(testDriver.getName(), testDriver.getSurname())).thenReturn(testDriver);
        when(driverDao.findTestDriver("lola", "neni")).thenReturn(null);
        assertThat(driverService.findTestDriver(testDriver.getName(), testDriver.getSurname())).isEqualTo(testDriver);
        assertThat(driverService.findTestDriver("lola", "neni")).isNull();
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void findNullTestDriverTest(){
        when(driverDao.findTestDriver(null, null)).thenThrow(new IllegalArgumentException());
        driverService.findTestDriver(null, null);
    }
    
    @AfterMethod
    public void tearDown() {
        result.clear();
    }
}
