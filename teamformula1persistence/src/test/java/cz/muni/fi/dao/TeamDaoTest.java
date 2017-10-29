package cz.muni.fi.dao;

import cz.muni.fi.PersistenceApplicationContext;
import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Component;
import cz.muni.fi.entities.Team;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.enums.ComponentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Matus Macko
 */
@Transactional
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class TeamDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    private TeamDao teamManager;

    private Team blueTeam;

    @BeforeMethod
    public void setup() {
        Driver blackCarDriver = new Driver("John", "Does", "US");
        Component blackEngine = new Component("Engine", false, ComponentType.ENGINE);
        Component blackAero = new Component("Aero", false, ComponentType.AERODYNAMICS);
        Component blackBreaks = new Component("Breaks", false, ComponentType.BRAKES);
        Component blackSuspension = new Component("Purpl", false, ComponentType.SUSPENSION);
        Component blackTransmision = new Component("Purpl", false, ComponentType.TRANSMISSION);

        Driver silverCarDriver = new Driver("James", "Bond", "SK");
        Component silverEngine = new Component("Engine", false, ComponentType.ENGINE);
        Component silverAero = new Component("Aero", false, ComponentType.AERODYNAMICS);
        Component silverBreaks = new Component("Breaks", false, ComponentType.BRAKES);
        Component silverSuspension = new Component("Purpl", false, ComponentType.SUSPENSION);
        Component silverTransmision = new Component("Purpl", false, ComponentType.TRANSMISSION);
        Driver goldCarDriver = new Driver("Jiri", "Novak", "CZ");
        Driver purpleCarDriver = new Driver("Martin", "King", "EN");

        Car blackCar = new Car(blackCarDriver, blackEngine, blackAero, blackSuspension, blackTransmision, blackBreaks);
        Car silverCar = new Car(silverCarDriver, silverEngine, silverAero, silverSuspension, silverTransmision, silverBreaks);

        blueTeam = new Team("Blue team", blackCar, silverCar);

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(blackEngine);
        entityManager.persist(blackAero);
        entityManager.persist(blackBreaks);
        entityManager.persist(blackSuspension);
        entityManager.persist(blackTransmision);
        entityManager.persist(blackCarDriver);

        entityManager.persist(silverEngine);
        entityManager.persist(silverAero);
        entityManager.persist(silverBreaks);
        entityManager.persist(silverSuspension);
        entityManager.persist(silverTransmision);
        entityManager.persist(silverCarDriver);

        entityManager.persist(blackCar);
        entityManager.persist(silverCar);

        entityManager.persist(blueTeam);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void listallTeamsTest() {
        assertThat(teamManager.listTeams()).containsExactlyInAnyOrder(blueTeam);
    }

    @Test
    public void findTeamByNameTest() {
        assertThat(teamManager.findTeam(blueTeam.getName())).isEqualTo(blueTeam);
    }

    @Test
    public void findTeamByIdTest() {
        assertThat(teamManager.findTeam(blueTeam.getId())).isEqualTo(blueTeam);
    }

    @Test
    public void updateTeamTest() {
        assertThat(em.find(Team.class, blueTeam.getId())).isEqualTo(blueTeam);

        blueTeam.setName("New team name");
        teamManager.updateTeam(blueTeam);

        assertThat(em.find(Team.class, blueTeam.getId())).isEqualTo(blueTeam);
    }

    @Test
    public void addTeamTest() {
        Car yellowCar = new Car();
        Car greenCar = new Car();

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(yellowCar);
        entityManager.persist(greenCar);
        entityManager.getTransaction().commit();
        entityManager.close();

        Team newTeam = new Team("New team", yellowCar, greenCar);

        assertThat(em.find(Team.class, newTeam.getName())).isNull();
        teamManager.addTeam(newTeam);
        assertThat(em.find(Team.class, newTeam.getId())).isEqualTo(newTeam);
    }

    @Test
    public void deleteTeamTest() {
        assertThat(em.find(Team.class, blueTeam.getId())).isEqualTo(blueTeam);
        teamManager.deleteTeam(blueTeam);
        assertThat(em.find(Team.class, blueTeam.getId())).isNull();
    }

    @AfterMethod
    public void tearDown() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Team").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}