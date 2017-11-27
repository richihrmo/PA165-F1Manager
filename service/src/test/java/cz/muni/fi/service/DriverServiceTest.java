package cz.muni.fi.service;

import cz.muni.fi.dao.DriverDao;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

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
    
    private Driver driver;
    private Driver testDriver;
    private Map<Long, Driver> drivers = new HashMap<>();
    private Long counter = 20L;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
        
        when(driverDao.addDriver(any(Driver.class))).then(invoke -> {

            Driver mockedDriver = invoke.getArgumentAt(0, Driver.class);
            if(mockedDriver.getId() != null) throw new IllegalArgumentException("Driver already exists");
            if(mockedDriver.getName() == null) throw new IllegalArgumentException("Driver name cannot be null");
            if(mockedDriver.getSurname() == null) throw new IllegalArgumentException("Driver surname cannot be null");
            if(mockedDriver.getNationality() == null) throw new IllegalArgumentException("Driver nationality cannot be null");

            long index = counter;
            mockedDriver.setId(index);
            drivers.put(index, mockedDriver);
            counter++;
            return mockedDriver;
        });
        
        when(driverDao.updateDriver(any(Driver.class))).then(invoke -> {

            Driver mockedDriver = invoke.getArgumentAt(0, Driver.class);
            if(mockedDriver.getId() == null) throw new IllegalArgumentException("Driver id cannot be null!");
            if(mockedDriver.getName() == null) throw new IllegalArgumentException("Driver name cannot be null");
            if(mockedDriver.getSurname() == null) throw new IllegalArgumentException("Driver surname cannot be null");
            if(mockedDriver.getNationality() == null) throw new IllegalArgumentException("Driver nationality cannot be null");

            drivers.replace(mockedDriver.getId(), mockedDriver);
            return mockedDriver;
        });

        when(driverDao.deleteDriver(any(Driver.class))).then(invoke -> {

            Driver mockedDriver = invoke.getArgumentAt(0, Driver.class);
            if (mockedDriver == null) throw new IllegalArgumentException("Driver cannot be null!");
            if (mockedDriver.getId() == null) throw new IllegalArgumentException("Driver is not stored");

            drivers.remove(mockedDriver.getId(), mockedDriver);
            return mockedDriver;
        });
        
        when(driverDao.findDriverById(anyLong())).then(invoke -> {

            Long index = invoke.getArgumentAt(0, Long.class);
            if (index == null) throw new IllegalArgumentException("Cannot search with null ID");

            return drivers.get(index);
        });

        when(driverDao.findDriverByName(anyString(), anyString())).then(invoke -> {

            String name = invoke.getArgumentAt(0, String.class);
            String surname = invoke.getArgumentAt(1, String.class);
            
            if (name == null || surname == null) throw new IllegalArgumentException("Cannont search with null name");
            return drivers.values().stream().filter(p -> p.getName().equals(name) && p.getSurname().equals(surname)).findFirst();
        });
        
        when(driverDao.findTestDriver(anyString(), anyString())).then(invoke -> {

            String name = invoke.getArgumentAt(0, String.class);
            String surname = invoke.getArgumentAt(1, String.class);
            
            if (name == null || surname == null) throw new IllegalArgumentException("Cannont search with null name");
            return drivers.values().stream().filter(p -> p.getName().equals(name) && p.getSurname().equals(surname) && !p.isMainDriver()).findFirst();
        });

        when(driverDao.listDrivers()).then(invoke ->
                Collections.unmodifiableList(new ArrayList<>(drivers.values())));
        
        when(driverDao.listTestDrivers()).then(invoke ->
                drivers.values().stream().filter(p -> !p.isMainDriver()).collect(Collectors.toList()));

    }
    
   
    
    @BeforeMethod
    public void prepareDriver(){
        driver = new Driver();
        driver.setId(counter);
        driver.setAsMainDriver();
        driver.setName("Anakin");
        driver.setSurname("Skywalker");
        driver.setNationality("unknown");
        
        drivers.put(counter, driver);
        counter++;
        
        testDriver = new Driver();
        testDriver.setAsTestDriver();
        testDriver.setId(counter);
        testDriver.setName("Harry");
        testDriver.setSurname("Potter");
        testDriver.setNationality("english");
        
        drivers.put(counter, testDriver);
        counter++;
    }
    
    @Test
    public void listDriversTest(){
        assertThat(driverService.listDrivers()).containsExactlyInAnyOrder(driver, testDriver);
        assertThat(driverService.listDrivers()).doesNotContain(new Driver());
    }
    
    @Test
    public void listNoDriversTest(){
        drivers.clear();
        assertThat(driverService.listDrivers()).doesNotContain(driver);
        assertThat(driverService.listDrivers()).isEmpty();
    }
    
    @Test
    public void listTestDriversTest(){
        assertThat(driverService.listTestDrivers()).containsExactlyInAnyOrder(testDriver);
        assertThat(driverService.listTestDrivers()).doesNotContain(driver);
    }
    
    @Test
    public void listNoTestDriversTest(){
        drivers.clear();
        assertThat(driverService.listTestDrivers()).isEmpty();
    }
    
    @Test
    public void findDriverByIdTest(){
        assertThat(driverService.findDriverById(testDriver.getId())).isEqualTo(testDriver);
        assertThat(driverService.findDriverById(0L)).isNull();
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findDriverByNullIdTest(){
        driverService.findDriverById(null);
    }
    
    @Test
    public void findDriverByNameTest(){
        assertThat(driverService.findDriverByName(driver.getName(), driver.getSurname())).isEqualTo(driver);
        assertThat(driverService.findDriverByName("lola", "neni")).isNull();
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findDriverByNullNameTest(){
        driverService.findDriverByName(null, null);
    }
    
    @Test
    public void findTestDriverTest(){
        assertThat(driverService.findTestDriver(testDriver.getName(), testDriver.getSurname())).isEqualTo(testDriver);
        assertThat(driverService.findTestDriver("lola", "neni")).isNull();
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findNullTestDriverTest(){
        driverService.findTestDriver(null, null);
    }
    
    
    @Test
    public void addDriverTest(){
        Driver newDriver = new Driver();
        newDriver.setAsMainDriver();
        newDriver.setName("Anakin");
        newDriver.setSurname("Skywalker");
        newDriver.setNationality("unknown");
        counter++;
        int count = drivers.size();
        driverService.addDriver(newDriver);
        assertThat(drivers.size()).isEqualTo(count+1);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addNullDriverTest(){
        driverService.addDriver(null);
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void addNullAtributDriverTest(){
        Driver newDriver = new Driver();
        newDriver.setId(counter);
        counter++;
        driverService.addDriver(newDriver);
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void addExistingDriverTest(){
        driverService.addDriver(driver);
    }
    
    @Test
    public void updateDriverTest(){
        driver.setName("Adam");
        driverService.updateDriver(driver);
        assertThat(drivers.get(driver.getId())).isEqualToComparingFieldByField(driver);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullDriverTest(){
        driverService.updateDriver(null);
    }

    @Test
    public void deleteDriverTest(){
        driverService.deleteDriver(driver);
        assertThat(drivers.get(driver.getId())).isNull();
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullDriverTest(){
        driverService.deleteDriver(null);
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void deleteNonExistingDriverTest(){
        driver.setId(null);
        driverService.deleteDriver(driver);
    }
    
    @AfterMethod
    public void tearDown() {
        drivers.clear();
    }
}