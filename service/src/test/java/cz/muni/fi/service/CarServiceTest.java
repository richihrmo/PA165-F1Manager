package cz.muni.fi.service;

import cz.muni.fi.dao.CarDao;
import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Component;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.persistanceEnums.ComponentType;
import cz.muni.fi.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.mockito.*;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Richard Hrmo
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CarServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private CarDao carDao;

    @Autowired
    @InjectMocks
    private CarService carService = new CarServiceImpl();

    private Car redbullF1 = new Car();
    private Car marussia = null;
    private Car jordan = new Car();
    private Car ferrari = new Car();

    private Driver ricciardo;
    private Driver verstappen;
    private Driver alonso;
    private Driver vettel;

    private Component transmission;
    private Component suspension;
    private Component aero;
    private Component brake;
    private Component engineV8;
    private Component engineV10;
    private Component engineV12;

    @BeforeMethod
    public void setup(){
        ricciardo = new Driver("Daniel", "Ricciardo","AUS");
        alonso = new Driver("Fernando", "Alonso","ESP");
        vettel = new Driver("Sebastian", "Vettel","GER");
        verstappen = null;

        transmission = new Component("transmission", true, ComponentType.TRANSMISSION);
        suspension = new Component("suspension", true, ComponentType.SUSPENSION);
        aero = new Component("aero", true, ComponentType.AERODYNAMICS);
        brake = new Component("brake", true, ComponentType.BRAKES);
        engineV8 = new Component("V8", true, ComponentType.ENGINE);
        engineV10 = new Component("V10", true, ComponentType.ENGINE);
        engineV12 = new Component("V12", true, ComponentType.ENGINE);

        redbullF1.setDriver(ricciardo);
        redbullF1.setTransmission(transmission);
        redbullF1.setSuspension(suspension);
        redbullF1.setBrakes(brake);
        redbullF1.setAerodynamics(aero);
        redbullF1.setEngine(engineV8);

        jordan.setDriver(alonso);
        jordan.setSuspension(suspension);
        jordan.setAerodynamics(aero);
        jordan.setBrakes(brake);
        jordan.setTransmission(transmission);
        jordan.setEngine(engineV10);

        ferrari.setDriver(vettel);
        ferrari.setSuspension(suspension);
        ferrari.setAerodynamics(aero);
        ferrari.setBrakes(brake);
        ferrari.setTransmission(transmission);
        ferrari.setEngine(engineV12);

    }

    @Test
    public void createCarTest(){
        carService.createCar(redbullF1);
        assertThat(carService.findCarById(redbullF1.getId())).isEqualTo(redbullF1);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void createNullCar(){
        carService.createCar(marussia);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void createCarNullDriverTest(){
        redbullF1.setDriver(verstappen);
        carService.createCar(redbullF1);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void createCarNullEngineTest(){
        redbullF1.setEngine(null);
        carService.createCar(redbullF1);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void createCarNullTransmissionTest(){
        redbullF1.setTransmission(null);
        carService.createCar(redbullF1);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void createCarNullSuspensionTest(){
        redbullF1.setSuspension(null);
        carService.createCar(redbullF1);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void createCarNullBrakesTest(){
        redbullF1.setBrakes(null);
        carService.createCar(redbullF1);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void createCarNullAerodynamicsTest(){
        redbullF1.setAerodynamics(null);
        carService.createCar(redbullF1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findCarByNullId(){
        carService.findCarById(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findCarByNullDriver(){
        carService.findCarByDriver(null);
    }

    @Test
    public void deleteCarTest(){
        carService.createCar(redbullF1);
        assertThat(carService.findCarById(redbullF1.getId())).isEqualTo(redbullF1);
        carService.deleteCar(redbullF1);
        assertThat(carService.findCarById(redbullF1.getId())).isNull();
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void deleteNullCar(){
        carService.deleteCar(marussia);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateWithNullDriver(){
        carService.createCar(redbullF1);
        redbullF1.setDriver(verstappen);
        carService.updateCar(redbullF1);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateWithNullTransmission(){
        carService.createCar(redbullF1);
        redbullF1.setTransmission(null);
        carService.updateCar(redbullF1);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateWithNullEngine(){
        carService.createCar(redbullF1);
        redbullF1.setEngine(null);
        carService.updateCar(redbullF1);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateWithNullBrakes(){
        carService.createCar(redbullF1);
        redbullF1.setBrakes(null);
        carService.updateCar(redbullF1);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateWithNullAerodynamics(){
        carService.createCar(redbullF1);
        redbullF1.setAerodynamics(null);
        carService.updateCar(redbullF1);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateWithNullSuspension(){
        carService.createCar(redbullF1);
        redbullF1.setSuspension(null);
        carService.updateCar(redbullF1);
    }

    @Test
    public void listCarsByComponentNameTest(){
        carService.createCar(redbullF1);
        carService.createCar(ferrari);
        carService.createCar(jordan);

        assertThat(carService.listCarsByComponentName("V8")).containsExactly(redbullF1);
        assertThat(carService.listCarsByComponentName("suspension")).containsExactly(redbullF1, ferrari, jordan);
    }
}
