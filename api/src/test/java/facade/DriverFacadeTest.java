package facade;

import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.enums.DrivingSkill;
import cz.muni.fi.facade.DriverFacade;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import cz.muni.fi.service.DriverService;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import cz.muni.fi.entities.Driver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import org.testng.annotations.AfterMethod;

/**
 * @author Lucie Kureckova, 445264
 */
public class DriverFacadeTest{
    
    @Mock
    private DriverService driverService;
    
    @InjectMocks
    private DriverFacade driverFacade;
    
    @BeforeClass
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }
    
    private Driver driver;
    private DriverDTO driverDTO;
    private Driver testDriver;
    private DriverDTO testDriverDTO;
    private List<DriverDTO> result;
    
    @BeforeMethod
    public void prepareDriver(){
        driver = new Driver();
        driver.setAsMainDriver();
        driver.setName("Anakin");
        driver.setSurname("Skywalker");
        driver.setSpecialSkill(DrivingSkill.EXTREME_REFLEXES);
        
        driverDTO = new DriverDTO();
        driverDTO.setMainDriver(true);
        driverDTO.setName("Anakin");
        driverDTO.setSurname("Skywalker");
        driverDTO.setSpecialSkill(DrivingSkill.EXTREME_REFLEXES);
        
        testDriver = new Driver();
        testDriver.setAsTestDriver();
        testDriver.setName("Harry");
        testDriver.setSurname("Potter");
        testDriver.setSpecialSkill(DrivingSkill.POWER_SLIDING);
        
        testDriverDTO = new DriverDTO();
        testDriverDTO.setMainDriver(false);
        testDriverDTO.setName("Harry");
        testDriverDTO.setSurname("Potter");
        testDriverDTO.setSpecialSkill(DrivingSkill.POWER_SLIDING);
        
        result.add(driver);
        result.add(testDriver);
    }
    
    @Test
    public void getAllDriversTest(){
        when(driverService.listDrivers()).thenReturn(result);
        assertThat(driverFacade.getAllDrivers()).containsExactlyInAnyOrder(driverDTO, testDriverDTO);
    }
    
    @Test
    public void getAllTestDriversTest(){
        result.remove(driver);
        when(driverService.listTestDrivers()).thenReturn(result);
        assertThat(driverFacade.getAllTestDrivers()).containsExactlyInAnyOrder(testDriver);
    }
    
    @Test
    public void getDriverByIDTest(){
        when(driverService.findDriverById(1L)).thenReturn(driver);
        assertThat(driverFacade.getDriverByID(1L)).isEqualTo(driverDTO);
    }
    
    @Test
    public void getDriverByNameTest(){
        when(driverService.findDriverByName(driver.getName(), driver.getSurname())).thenReturn(driver);
        assertThat(driverFacade.getDriverByName(driverDTO.getName(), driverDTO.getSurname())).isEqualTo(driverDTO);
    }
    
    @Test
    public void getTestDriverByNameTest(){
        when(driverService.findTestDriver(testDriver.getName(), testDriver.getSurname())).thenReturn(testDriver);
        assertThat(driverFacade.getTestDriverByName(testDriverDTO.getName(), testDriverDTO.getSurname())).isEqualTo(testDriverDTO);
    }
    
    @AfterMethod
    public void tearDown() {
        result.clear();
    }
}
