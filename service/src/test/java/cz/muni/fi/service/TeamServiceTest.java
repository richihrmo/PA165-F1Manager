package cz.muni.fi.service;

import cz.muni.fi.UtilityClass;
import cz.muni.fi.dao.TeamDao;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.entities.Team;
import cz.muni.fi.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @author Matus Macko
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TeamServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    private TeamDao teamDao;

    @Autowired
    @InjectMocks
    private TeamService teamService;

    private Team blueTeam;
    private Team redTeam;
    private Map<Long, Team> teams = new HashMap<>();

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(teamDao.addTeam(any(Team.class))).then(invoke -> {
            Team mockedTeam = invoke.getArgumentAt(0, Team.class);
            if (mockedTeam == null) {
                throw new IllegalArgumentException("Team is null!");
            }

            if (mockedTeam.getId() != null) {
                throw new IllegalArgumentException("Id must be null!");
            }

            if (mockedTeam.getName() == null || mockedTeam.getCarTwo() == null || mockedTeam.getCarOne() == null) {
                throw new IllegalArgumentException("Team attributes can't be null!");
            }

            mockedTeam.setId(Long.valueOf(teams.size()));

            teams.put(mockedTeam.getId(), mockedTeam);
            return mockedTeam;
        });

        when(teamDao.listTeams()).then(invoke -> Collections.unmodifiableList(new ArrayList<>(teams.values())));

        when(teamDao.deleteTeam(any(Team.class))).then(invoke -> {
            Team mockedTeam = invoke.getArgumentAt(0, Team.class);
            if (mockedTeam == null) {
                throw new IllegalArgumentException("Team is null!");
            }

            if (mockedTeam.getId() == null || mockedTeam.getName() == null || mockedTeam.getCarOne() == null || mockedTeam.getCarTwo() == null) {
                throw new IllegalArgumentException("Tema attributes cannot be null!");
            }

            teams.remove(mockedTeam.getId(), mockedTeam);
            return true;
        });

        when(teamDao.findTeamById(anyLong())).then(invoke -> {
            Long id = invoke.getArgumentAt(0, Long.class);
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null!");
            }
            return teams.get(id);
        });

        when(teamDao.updateTeam(any(Team.class))).then(invoke -> {
            Team mockedTeam = invoke.getArgumentAt(0, Team.class);
            if (mockedTeam.getId() == null) {
                throw new IllegalArgumentException("Id cannot be null!");
            }
            if (mockedTeam.getName() == null || mockedTeam.getCarOne() == null || mockedTeam.getCarTwo() == null) {
                throw new IllegalArgumentException("Team attributes cannot be null!");
            }

            teams.replace(mockedTeam.getId(), mockedTeam);
            return mockedTeam;
        });
    }

    @BeforeMethod
    public void prepareTestTeam() {
        blueTeam = new Team("Blue team", UtilityClass.createSampleCar("John", "Smith"), UtilityClass.createSampleCar("Jane", "Doe"));
        blueTeam.setId(1L);
        teams.put(blueTeam.getId(), blueTeam);

        redTeam = new Team("Red team", UtilityClass.createSampleCar("James", "Bond"), UtilityClass.createSampleCar("Bruce", "Wayne"));
    }

    @Test
    public void createTeamTest() {
        teamService.createTeam(redTeam);
        assertThat(teams.values()).hasSize(1).contains(redTeam);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullTeamTest() {
        teamService.createTeam(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void createNotNullIdTeamTest() {
        teamService.createTeam(blueTeam);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void createTeamWithNullAttributesTest() {
        redTeam.setName(null);
        redTeam.setCarOne(null);
        redTeam.setCarTwo(null);
        teamService.createTeam(redTeam);
    }

    @Test
    public void findAllTeamsTest() {
        assertThat(teamService.findAllTeams()).containsExactlyInAnyOrder(blueTeam);
    }

    @Test
    public void updateTeamTest(){
        blueTeam.setName("Updated Team");
        teamService.updateTeam(blueTeam);
        Team updatedTeam = teams.get(blueTeam.getId());
        assertThat(updatedTeam).isEqualToComparingFieldByField(blueTeam);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullTeamTest() {
        teamService.updateTeam(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateTeamWithNullAttributeTest() {
        blueTeam.setId(null);
        teamService.updateTeam(blueTeam);
    }

    @Test
    public void findTeamByIdTest() {
        assertThat(teamService.findTeamById(blueTeam.getId())).isEqualTo(blueTeam);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findTeamByNullIdTest() {
        teamService.findTeamById(null);
    }

    @Test
    public void deleteTeamTest() {
        teamService.deleteTeam(blueTeam);
        assertThat(teams.values()).isEmpty();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullTeamTest() {
        teamService.deleteTeam(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void deleteTeamWithNullAttributesTest() {
        blueTeam.setId(null);
        teamService.deleteTeam(blueTeam);
    }

    @Test
    public void findAllTeamCarDriversTest() {
        List<Driver> drivers = teamService.findAllTeamCarDrivers();
        assertThat(drivers).hasSize(2);
        assertThat(drivers.get(0).getName()).isEqualTo("John");
        assertThat(drivers.get(0).getSurname()).isEqualTo("Smith");
        assertThat(drivers.get(1).getName()).isEqualTo("Jane");
        assertThat(drivers.get(1).getSurname()).isEqualTo("Doe");
    }

    @Test
    public void findAllTeamCarDriversEmptyTest() {
        teams.clear();
        assertThat(teamService.findAllTeamCarDrivers()).hasSize(0);
    }
}