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
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

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

    private Map<Long, Car> cars = new HashMap<>();

    @BeforeClass
    public void mockitoSetup(){
        MockitoAnnotations.initMocks(this);

        when(carDao.addCar(any(Car.class))).then(invoke -> {
            Car mockedCar = invoke.getArgumentAt(0, Car.class);
            if (mockedCar == null) throw new IllegalArgumentException("Car is null");
            if (mockedCar.getId() != null) throw new IllegalArgumentException("Id must be null");
            if (mockedCar.getEngine() == null
                    || mockedCar.getAerodynamics() == null
                    || mockedCar.getBrakes() == null
                    || mockedCar.getTransmission() == null
                    || mockedCar.getSuspension() == null
                    || mockedCar.getDriver() == null) throw new IllegalArgumentException("Car attribute is null");

            mockedCar.setId(Long.valueOf(cars.size()));

            cars.put(mockedCar.getId(), mockedCar);
            return mockedCar;
        });

        when(carDao.listAllCars()).then(invoke -> Collections.unmodifiableList(new ArrayList<>(cars.values())));

        when(carDao.deleteCar(any(Car.class))).then(invoke -> {
            Car mockedCar = invoke.getArgumentAt(0, Car.class);
            if (mockedCar == null) throw new IllegalArgumentException("Car is null!");
            if (mockedCar.getId() == null
                    || mockedCar.getSuspension() == null
                    || mockedCar.getTransmission() == null
                    || mockedCar.getBrakes() == null
                    || mockedCar.getAerodynamics() == null
                    || mockedCar.getEngine() == null) throw new IllegalArgumentException("Car attributes cannot be null!");

            cars.remove(mockedCar.getId(), mockedCar);
            return true;
        });

        when(carDao.findCarById(anyLong())).then(invoke -> {
            Long id = invoke.getArgumentAt(0, Long.class);
            if (id == null) throw new IllegalArgumentException("Id is null");
            return cars.get(id);
        });

        when(carDao.updateCar(any(Car.class))).then(invoke -> {
            Car mockedCar = invoke.getArgumentAt(0, Car.class);
            if (mockedCar.getId() == null
                    || mockedCar.getSuspension() == null
                    || mockedCar.getTransmission() == null
                    || mockedCar.getBrakes() == null
                    || mockedCar.getAerodynamics() == null
                    || mockedCar.getEngine() == null) throw new IllegalArgumentException("Car attributes cannot be null!");


            cars.replace(mockedCar.getId(), mockedCar);
            return mockedCar;
        });
    }

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

//    @AfterMethod
//    public void tearDown(){
//        for (Car car : cars.values()) {
//            try {
//                carDao.deleteCar(car);
//            } catch (Exception e){
//                throw new RuntimeException("fail delete tear down", e);
//            }
//        }
//    }

    @Test
    public void createCarTest(){
        Car new_car = new Car(ricciardo, engineV10, aero, suspension, transmission, brake);
        carService.createCar(new_car);
        assertThat(cars.values()).hasSize(1).contains(new_car);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullCar(){
        carService.createCar(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void createCarNullDriverTest(){
        redbullF1.setDriver(null);
        carService.createCar(redbullF1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createCarNullEngineTest(){
        redbullF1.setEngine(null);
        carService.createCar(redbullF1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createCarNullTransmissionTest(){
        redbullF1.setTransmission(null);
        carService.createCar(redbullF1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createCarNullSuspensionTest(){
        redbullF1.setSuspension(null);
        carService.createCar(redbullF1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createCarNullBrakesTest(){
        redbullF1.setBrakes(null);
        carService.createCar(redbullF1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
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
        carService.createCar(ferrari);
        assertThat(cars.values()).hasSize(1).contains(ferrari);
        carService.deleteCar(ferrari);
        assertThat(cars.values()).hasSize(0);
        assertThat(carService.findCarById(ferrari.getId())).isNull();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullCar(){
        carService.deleteCar(marussia);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateWithNullDriver(){
        carService.createCar(redbullF1);
        redbullF1.setDriver(verstappen);
        carService.updateCar(redbullF1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateWithNullTransmission(){
        carService.createCar(redbullF1);
        redbullF1.setTransmission(null);
        carService.updateCar(redbullF1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateWithNullEngine(){
        carService.createCar(redbullF1);
        redbullF1.setEngine(null);
        carService.updateCar(redbullF1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateWithNullBrakes(){
        carService.createCar(redbullF1);
        redbullF1.setBrakes(null);
        carService.updateCar(redbullF1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateWithNullAerodynamics(){
        carService.createCar(redbullF1);
        redbullF1.setAerodynamics(null);
        carService.updateCar(redbullF1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateWithNullSuspension(){
        carService.createCar(redbullF1);
        redbullF1.setSuspension(null);
        carService.updateCar(redbullF1);
    }

    @Test
    public void listCarsByComponentNameTest(){
        carService.createCar(ferrari);
        carService.createCar(redbullF1);
        carService.createCar(jordan);

        assertThat(carService.listCarsByComponentName("V12")).containsExactly(ferrari);
        assertThat(carService.listCarsByComponentName("suspension")).containsExactlyInAnyOrder(redbullF1, ferrari, jordan);
    }
}
