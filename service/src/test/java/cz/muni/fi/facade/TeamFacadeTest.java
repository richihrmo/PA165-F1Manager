package cz.muni.fi.facade;

import cz.muni.fi.UtilityClass;
import cz.muni.fi.dto.NewTeamDTO;
import cz.muni.fi.dto.TeamDTO;
import cz.muni.fi.entities.Team;
import cz.muni.fi.service.BeanMappingService;
import cz.muni.fi.service.TeamService;
import cz.muni.fi.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TeamFacadeTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    private TeamService teamService;

    @Autowired
    @InjectMocks
    private TeamFacade teamFacade;

    @Autowired
    private BeanMappingService beanMappingService;

    private Team blueTeam;
    private TeamDTO blueTeamDTO;
    private NewTeamDTO newBlueTeamDTO;


    @BeforeClass
    public void setUp() throws ServiceException {
        MockitoAnnotations.initMocks(this);

        blueTeam = new Team("Red team", UtilityClass.createSampleCar("James", "Bond"), UtilityClass.createSampleCar("Bruce", "Wayne"));;
        newBlueTeamDTO = beanMappingService.mapTo(blueTeam, NewTeamDTO.class);

        blueTeam.setId(1L);
        blueTeamDTO = beanMappingService.mapTo(blueTeam, TeamDTO.class);
    }

    @Test
    public void getAllTeamsTest() {
        when(teamService.findAllTeams()).then(invoke -> Collections.unmodifiableList(new ArrayList<>(Arrays.asList(blueTeam))));
        List<TeamDTO>results = teamFacade.getAllTeams();
        assertThat(results.size()).isEqualTo(1);
    }

    @Test
    public void getTeamByIdTest() {
        when(teamService.findTeamById(1L)).then(invoke -> blueTeam);
        assertThat(teamFacade.getTeamById(1L)).isEqualTo(blueTeamDTO);
    }

    @Test
    public void createTeamTest() {
        teamFacade.createTeam(newBlueTeamDTO);
    }

    @Test
    public void updateTeamTest() {
        teamFacade.updateTeam(blueTeamDTO);
    }

    @Test
    public void deleteTeamTest() {
        teamFacade.deleteTeam(blueTeamDTO);
    }

    @Test
    public void getAllTeamCarDriversTest() {
        assertThat(teamFacade.getAllTeamCarDrivers().size()).isEqualTo(2);
    }

}