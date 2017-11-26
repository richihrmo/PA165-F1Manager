package cz.muni.fi.service;

import cz.muni.fi.dto.CarDTO;
import cz.muni.fi.dto.ComponentDTO;
import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Component;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.persistanceEnums.ComponentType;
import cz.muni.fi.persistanceEnums.DrivingSkill;
import cz.muni.fi.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class BeanMappingServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BeanMappingService mapper;

    private List<Component> components;
    private Car car;

    @BeforeClass
    public void setUp() {
        components = new ArrayList<>();
        Component brakes = new Component();
        brakes.setName("Brakes");
        brakes.setComponentType(ComponentType.BRAKES);
        brakes.setAvailable(false);
        components.add(brakes);

        Component engine = new Component();
        engine.setName("Engine");
        engine.setComponentType(ComponentType.ENGINE);
        engine.setAvailable(false);
        components.add(engine);

        Component aerodynamics = new Component();
        aerodynamics.setName("Aerodynamics");
        aerodynamics.setComponentType(ComponentType.AERODYNAMICS);
        aerodynamics.setAvailable(false);
        components.add(aerodynamics);

        Component suspension = new Component();
        suspension.setName("Suspension");
        suspension.setComponentType(ComponentType.SUSPENSION);
        suspension.setAvailable(false);
        components.add(suspension);

        Component transmission = new Component();
        transmission.setName("Transmission");
        transmission.setComponentType(ComponentType.TRANSMISSION);
        suspension.setAvailable(true);
        components.add(transmission);

        Driver driver = new Driver();
        driver.setName("Brano");
        driver.setSurname("Mojsej");
        driver.setNationality("Svk");
        driver.setSpecialSkill(DrivingSkill.DRIVING_ON_WET);
        driver.setAsMainDriver();

        car = new Car();
        car.setBrakes(brakes);
        car.setEngine(engine);
        car.setSuspension(suspension);
        car.setTransmission(transmission);
        car.setAerodynamics(aerodynamics);
        car.setDriver(driver);
    }


    @Test
    public void shouldMapInnerAttributeInListTest() {
        List<ComponentDTO> mappedComponents = mapper.mapTo(components, ComponentDTO.class);
        for (ComponentDTO componentDTO : mappedComponents) {
            assertThat(componentDTO.getType().getClass()).isEqualTo(cz.muni.fi.enums.ComponentType.class);
        }
    }

    @Test
    public void shouldMapInnerAttributesTest() {
        CarDTO carDTO = mapper.mapTo(car, CarDTO.class);

        assertThat(carDTO.getBrakes().getClass()).isEqualTo(ComponentDTO.class);
        assertThat(carDTO.getBrakes().getType().getClass()).isEqualTo(cz.muni.fi.enums.ComponentType.class);

        assertThat(carDTO.getEngine().getClass()).isEqualTo(ComponentDTO.class);
        assertThat(carDTO.getEngine().getType().getClass()).isEqualTo(cz.muni.fi.enums.ComponentType.class);

        assertThat(carDTO.getSuspension().getClass()).isEqualTo(ComponentDTO.class);
        assertThat(carDTO.getSuspension().getType().getClass()).isEqualTo(cz.muni.fi.enums.ComponentType.class);

        assertThat(carDTO.getAerodynamics().getClass()).isEqualTo(ComponentDTO.class);
        assertThat(carDTO.getAerodynamics().getType().getClass()).isEqualTo(cz.muni.fi.enums.ComponentType.class);

        assertThat(carDTO.getTransmission().getClass()).isEqualTo(ComponentDTO.class);
        assertThat(carDTO.getTransmission().getType().getClass()).isEqualTo(cz.muni.fi.enums.ComponentType.class);

        assertThat(carDTO.getDriver().getClass()).isEqualTo(DriverDTO.class);
        assertThat(carDTO.getDriver().getSpecialSkill().getClass()).isEqualTo(cz.muni.fi.enums.DrivingSkill.class);
    }

    @Test
    public void shouldMapCorrectlyTest() {
        CarDTO carDTO = mapper.mapTo(car, CarDTO.class);

        assertThat(carDTO.getBrakes().getName()).isEqualTo("Brakes");
        assertThat(carDTO.getBrakes().getType()).isEqualTo(cz.muni.fi.enums.ComponentType.BRAKES);

        assertThat(carDTO.getEngine().getName()).isEqualTo("Engine");
        assertThat(carDTO.getEngine().getType()).isEqualTo(cz.muni.fi.enums.ComponentType.ENGINE);

        assertThat(carDTO.getSuspension().getName()).isEqualTo("Suspension");
        assertThat(carDTO.getSuspension().getType()).isEqualTo(cz.muni.fi.enums.ComponentType.SUSPENSION);

        assertThat(carDTO.getTransmission().getName()).isEqualTo("Transmission");
        assertThat(carDTO.getTransmission().getType()).isEqualTo(cz.muni.fi.enums.ComponentType.TRANSMISSION);

        assertThat(carDTO.getAerodynamics().getName()).isEqualTo("Aerodynamics");
        assertThat(carDTO.getAerodynamics().getType()).isEqualTo(cz.muni.fi.enums.ComponentType.AERODYNAMICS);

        assertThat(carDTO.getDriver().getName()).isEqualTo("Brano");
        assertThat(carDTO.getDriver().getSurname()).isEqualTo("Mojsej");
        assertThat(carDTO.getDriver().getNationality()).isEqualTo("Svk");
        assertThat(carDTO.getDriver().getSpecialSkill()).isEqualTo(cz.muni.fi.enums.DrivingSkill.DRIVING_ON_WET);
    }
}
