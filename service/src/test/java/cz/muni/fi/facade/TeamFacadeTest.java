package cz.muni.fi.facade;

import cz.muni.fi.dao.CarDao;
import cz.muni.fi.dao.ComponentDao;
import cz.muni.fi.dao.DriverDao;
import cz.muni.fi.dao.TeamDao;
import cz.muni.fi.dto.CarDTO;
import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.dto.TeamDTO;
import cz.muni.fi.dto.TeamEditDTO;
import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Component;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.entities.Team;
import cz.muni.fi.persistanceEnums.ComponentType;
import cz.muni.fi.service.BeanMappingService;
import cz.muni.fi.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Matus Macko
 */
@DirtiesContext
@ContextConfiguration(classes = ServiceConfiguration.class)
public class TeamFacadeTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private TeamFacade teamFacade;

    @Autowired
    private CarDao carDao;

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private ComponentDao componentDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private BeanMappingService beanMappingService;

    private Car carOne;
    private Car carTwo;
    private TeamDTO redTeam;
    private List<DriverDTO> drivers = new ArrayList<>();
    private List<Component> carOneComponents = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        Driver carOneDriver = new Driver("John", "Does", "US");
        Driver carTwoDriver = new Driver("Jane", "Doe", "US");
        driverDao.addDriver(carOneDriver);
        driverDao.addDriver(carTwoDriver);

        drivers.add(beanMappingService.mapTo(carOneDriver, DriverDTO.class));
        drivers.add(beanMappingService.mapTo(carTwoDriver, DriverDTO.class));


        carOneComponents.add(new Component("Engine Black", false, ComponentType.ENGINE));
        carOneComponents.add(new Component("Aero Black", false, ComponentType.AERODYNAMICS));
        carOneComponents.add(new Component("Susp Black", false, ComponentType.SUSPENSION));
        carOneComponents.add(new Component("Transmission Black", false, ComponentType.TRANSMISSION));
        carOneComponents.add(new Component("Breaks Black", false, ComponentType.BRAKES));

        for (Component component : carOneComponents) {
            componentDao.addComponent(component);
        }

        carOne = new Car(carOneDriver, carOneComponents.get(0), carOneComponents.get(1), carOneComponents.get(2), carOneComponents.get(3),carOneComponents.get(4));
        carTwo = new Car(carTwoDriver, carOneComponents.get(0), carOneComponents.get(1), carOneComponents.get(2), carOneComponents.get(3),carOneComponents.get(4));

        carDao.addCar(carOne);
        carDao.addCar(carTwo);

        redTeam = beanMappingService.mapTo(teamDao.addTeam(new Team("Red team", carOne, carTwo)), TeamDTO.class);
    }

    @Test
    public void getAllTeamsTest() {
        assertThat(teamFacade.getAllTeams()).containsExactly(redTeam);
    }

    @Test
    public void getTeamByIdTest() {
        assertThat(teamFacade.getTeamById(redTeam.getId())).isEqualTo(redTeam);
    }

    @Test
    public void createTeamTest() {
        TeamEditDTO blueTeam = new TeamEditDTO("Blue team", carOne.getId(), carTwo.getId());
        TeamDTO createdTeam = teamFacade.createTeam(blueTeam);
        assertThat(teamFacade.getTeamById(createdTeam.getId())).isEqualTo(createdTeam);
    }


    @Test
    public void deleteTeamTest() {
        teamFacade.deleteTeam(redTeam);
        assertThat(teamFacade.getAllTeams()).isEmpty();
    }

    @Test
    public void updateTeamTest() {
        redTeam.setName("Updated name");

        TeamEditDTO editRedTeam = new TeamEditDTO(
                redTeam.getId(),
                "Updated name",
                redTeam.getCarOne().getId(),
                redTeam.getCarTwo().getId()
        );

        teamFacade.updateTeam(editRedTeam);
        redTeam.getCarOne().getDriver().setMainDriver(true);
        redTeam.getCarTwo().getDriver().setMainDriver(true);
        assertThat(teamFacade.getTeamById(editRedTeam.getId())).isEqualTo(redTeam);
    }

    @Test
    public void getAllTeamCarDriversTest() {
        assertThat(teamFacade.getAllTeamCarDrivers()).containsAll(drivers);
    }

    @Test
    public void getAllTeamCarsTest(){
        assertThat(teamFacade.getAllTeamCars())
                .hasSize(2)
                .contains(beanMappingService.mapTo(carOne, CarDTO.class), beanMappingService.mapTo(carTwo, CarDTO.class));
    }

    @AfterClass
    public void tearDown() {
        teamDao.deleteTeam(beanMappingService.mapTo(redTeam, Team.class));

        carDao.deleteCar(carOne);
        carDao.deleteCar(carTwo);

        for (Component component : carOneComponents) {
            componentDao.deleteComponent(component);
        }

        for (DriverDTO driver : drivers) {
            driverDao.deleteDriver(beanMappingService.mapTo(driver, Driver.class));
        }
    }
}