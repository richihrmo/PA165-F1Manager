package cz.muni.fi.dao;

import cz.muni.fi.PersistenceApplicationContext;
import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Matus Macko
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class TeamDaoTest extends AbstractTestNGSpringContextTests {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TeamDaoImpl teamManager = new TeamDaoImpl();

    private Team blueTeam;
    private Team redTeam;

    @BeforeMethod
    public void setup() {
        Car blackCar = new Car();
        Car silverCar = new Car();
        Car goldCar = new Car();
        Car purpleCar = new Car();

        blueTeam = new Team("Blue team", blackCar, silverCar);
        redTeam = new Team("Red team", goldCar, purpleCar);

        em.getTransaction().begin();
        em.persist(blackCar);
        em.persist(silverCar);
        em.persist(goldCar);
        em.persist(purpleCar);

        em.persist(blueTeam);
        em.persist(redTeam);
        em.getTransaction().commit();
    }

    @Test
    public void listallTeamsTest() {
        assertThat(teamManager.listTeams()).containsExactlyInAnyOrder(blueTeam, redTeam);
    }

    @Test
    public void findTeamByNameTest() {
        assertThat(teamManager.findTeam(blueTeam.getName())).isEqualTo(blueTeam);
    }

    @Test
    public void findTeamByIdTest() {
        assertThat(teamManager.findTeam(redTeam.getId())).isEqualTo(redTeam);
    }

    @Test
    public void updateTeamTest() {
        assertThat(em.find(Team.class, redTeam.getId())).isEqualTo(redTeam);

        redTeam.setName("New team name");
        teamManager.updateTeam(redTeam);

        assertThat(em.find(Team.class, redTeam.getId())).isEqualTo(redTeam);
    }

    @Test
    public void addTeamTest() {
        Car yellowCar = new Car();
        Car greenCar = new Car();
        em.getTransaction().begin();
        em.persist(yellowCar);
        em.persist(greenCar);
        em.getTransaction().commit();

        Team newTeam = new Team("New team", yellowCar, greenCar);

        assertThat(em.find(Team.class, newTeam.getName())).isNull();
        teamManager.addTeam(newTeam);
        assertThat(em.find(Team.class, newTeam.getId())).isEqualTo(newTeam);
    }

    @Test
    public void deleteTeamTest() {
        assertThat(em.find(Team.class, redTeam.getId())).isEqualTo(redTeam);
        teamManager.deleteTeam(redTeam);
        assertThat(em.find(Team.class, redTeam.getId())).isNull();
    }

    @AfterMethod
    public void tearDown() {
        em.getTransaction().begin();
        em.createQuery("delete from Team").executeUpdate();
        em.getTransaction().commit();
    }
}