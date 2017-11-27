package cz.muni.fi.service;


import cz.muni.fi.dao.ComponentDao;
import cz.muni.fi.dao.DriverDao;
import cz.muni.fi.dto.CarDTO;
import cz.muni.fi.dto.ComponentDTO;
import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Component;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.facade.CarFacade;
import cz.muni.fi.persistanceEnums.ComponentType;
import cz.muni.fi.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Richard Hrmo
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class CarFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CarFacade carFacade;

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private ComponentDao componentDao;

    @Autowired
    private BeanMappingService beanMappingService;

    private CarDTO redbullF1;
    private CarDTO jordan;
    private CarDTO ferrari;

    private DriverDTO ricciardo = new DriverDTO();
    private DriverDTO alonso = new DriverDTO();
    private DriverDTO vettel = new DriverDTO();
    private DriverDTO rookie = new DriverDTO();

    private ComponentDTO transmissionDTO;
    private ComponentDTO suspensionDTO;
    private ComponentDTO aeroDTO;
    private ComponentDTO brakeDTO;
    private ComponentDTO engineV8DTO;

    @BeforeClass
    public void setup(){
        List<CarDTO> carsList = new ArrayList<>();

        Component transmission = new Component("transmission", true, ComponentType.TRANSMISSION);
        componentDao.addComponent(transmission);
        transmissionDTO = beanMappingService.mapTo(transmission, ComponentDTO.class);

        Component suspension = new Component("suspension", true, ComponentType.SUSPENSION);
        componentDao.addComponent(suspension);
        suspensionDTO = beanMappingService.mapTo(suspension, ComponentDTO.class);

        Component aero = new Component("aero", true, ComponentType.AERODYNAMICS);
        componentDao.addComponent(aero);
        aeroDTO = beanMappingService.mapTo(aero, ComponentDTO.class);

        Component brake = new Component("brake", true, ComponentType.BRAKES);
        componentDao.addComponent(brake);
        brakeDTO = beanMappingService.mapTo(brake, ComponentDTO.class);

        Component engineV8 = new Component("V8", true, ComponentType.ENGINE);
        componentDao.addComponent(engineV8);
        engineV8DTO = beanMappingService.mapTo(engineV8, ComponentDTO.class);


        Driver rookieEntity = new Driver("Rookie", "Rukovic", "RUS");
        driverDao.addDriver(rookieEntity);
        Driver ricciardoEntity = new Driver("Daniel", "Ricciardo", "AUS");
        driverDao.addDriver(ricciardoEntity);
        Driver alonsoEntity = new Driver("Fernando", "Alonso", "ESP");
        driverDao.addDriver(alonsoEntity);
        Driver vettelEntity = new Driver("Sebastian", "Vettel", "GER");
        driverDao.addDriver(vettelEntity);

        ricciardo = beanMappingService.mapTo(ricciardoEntity, DriverDTO.class);
        rookie = beanMappingService.mapTo(rookieEntity, DriverDTO.class);
        alonso = beanMappingService.mapTo(alonsoEntity, DriverDTO.class);
        vettel = beanMappingService.mapTo(vettelEntity, DriverDTO.class);

        Car redbullF1Car = new Car(ricciardoEntity, engineV8, aero, suspension, transmission, brake);
        redbullF1 = beanMappingService.mapTo(redbullF1Car, CarDTO.class);

        Car ferrariCar = new Car(vettelEntity, engineV8, aero, suspension, transmission, brake);
        ferrari = beanMappingService.mapTo(ferrariCar, CarDTO.class);

        Car jordanCar = new Car(alonsoEntity, engineV8, aero, suspension, transmission, brake);
        jordan = beanMappingService.mapTo(jordanCar, CarDTO.class);


        carsList.add(redbullF1);
        carsList.add(jordan);
        carsList.add(ferrari);
        redbullF1.setDriver(ricciardo);
        jordan.setDriver(alonso);
        ferrari.setDriver(vettel);

        for (CarDTO carDTO : carsList) {
            carFacade.createCar(carDTO);
            assertThat(carFacade.findCarById(carDTO.getId())).isEqualTo(carDTO);
        }
    }

    @Test
    public void createCarTest(){
        CarDTO new_car = new CarDTO(ricciardo, engineV8DTO, aeroDTO, suspensionDTO, transmissionDTO, brakeDTO);
        carFacade.createCar(new_car);
        assertThat(carFacade.findCarById(new_car.getId())).isEqualTo(new_car);
    }

    @Test
    public void updateCarTest(){
        ferrari.setDriver(rookie);
        carFacade.updateCar(ferrari);
        CarDTO updated_car = carFacade.findCarById(ferrari.getId());
        assertThat(updated_car).isEqualTo(ferrari);
    }

    @Test
    public void listAllCarsTest(){
        assertThat(carFacade.listAllCars()).containsExactlyInAnyOrder(redbullF1, ferrari, jordan);
    }

    @Test
    public void findCarByDriverTest(){
        assertThat(carFacade.findCarByDriver(vettel)).isEqualTo(ferrari);
    }

    @Test
    public void findCarByIdTest(){
        assertThat(carFacade.findCarById(jordan.getId())).isEqualTo(jordan);
    }

    @Test
    public void deleteCarTest(){
        carFacade.deleteCar(redbullF1);
        assertThat(carFacade.findCarById(redbullF1.getId())).isNull();
    }

    @Test
    public void listCarsByComponentNameTest(){
        assertThat(carFacade.listCarsByComponentName("V8")).containsExactlyInAnyOrder(redbullF1, ferrari, jordan);
    }
}

