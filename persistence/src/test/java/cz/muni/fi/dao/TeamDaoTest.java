package cz.muni.fi.dao;

import cz.muni.fi.PersistenceApplicationContext;
import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Component;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.entities.Team;
import cz.muni.fi.persistanceEnums.ComponentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
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
    private Team greenTeam;

    @BeforeMethod
    public void setup() {
        Driver blackCarDriver = new Driver("John", "Does", "US");
        Component blackEngine = new Component("Engine Black", false, ComponentType.ENGINE);
        Component blackAero = new Component("Aero Black", false, ComponentType.AERODYNAMICS);
        Component blackBreaks = new Component("Breaks Black", false, ComponentType.BRAKES);
        Component blackSuspension = new Component("Susp Black", false, ComponentType.SUSPENSION);
        Component blackTransmission = new Component("Transmission Black", false, ComponentType.TRANSMISSION);

        Driver silverCarDriver = new Driver("James", "Bond", "SK");
        Component silverEngine = new Component("Engine Silver", false, ComponentType.ENGINE);
        Component silverAero = new Component("Aero Silver", false, ComponentType.AERODYNAMICS);
        Component silverBreaks = new Component("Breaks Silver", false, ComponentType.BRAKES);
        Component silverSuspension = new Component("Suspension Silver", false, ComponentType.SUSPENSION);
        Component silverTransmission = new Component("Transmission Silver", false, ComponentType.TRANSMISSION);


        Driver goldCarDriver = new Driver("Jiri", "Novak", "CZ");
        Component goldEngine = new Component("Engine Gold", false, ComponentType.ENGINE);
        Component goldAero = new Component("Aero Gold", false, ComponentType.AERODYNAMICS);
        Component goldBreaks = new Component("Breaks Gold", false, ComponentType.BRAKES);
        Component goldSuspension = new Component("Suspension Gold", false, ComponentType.SUSPENSION);
        Component goldTransmission = new Component("Transmission Gold", false, ComponentType.TRANSMISSION);

        Driver purpleCarDriver = new Driver("Martin", "King", "EN");
        Component purpleEngine = new Component("Engine Purple", false, ComponentType.ENGINE);
        Component purpleAero = new Component("Aero Purple", false, ComponentType.AERODYNAMICS);
        Component purpleBreaks = new Component("Breaks Purple", false, ComponentType.BRAKES);
        Component purpleSuspension = new Component("Suspension Purple", false, ComponentType.SUSPENSION);
        Component purpleTransmission = new Component("Transmission Purple", false, ComponentType.TRANSMISSION);

        Car blackCar = new Car(blackCarDriver, blackEngine, blackAero, blackSuspension, blackTransmission, blackBreaks);
        Car silverCar = new Car(silverCarDriver, silverEngine, silverAero, silverSuspension, silverTransmission, silverBreaks);
        Car goldCar = new Car(goldCarDriver, goldEngine, goldAero, goldSuspension, goldTransmission, goldBreaks);
        Car purpleCar = new Car(purpleCarDriver, purpleEngine, purpleAero, purpleSuspension, purpleTransmission, purpleBreaks);

        blueTeam = new Team("Blue team", blackCar, silverCar);
        greenTeam = new Team("Green team", goldCar, purpleCar);

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(blackEngine);
        entityManager.persist(blackAero);
        entityManager.persist(blackBreaks);
        entityManager.persist(blackSuspension);
        entityManager.persist(blackTransmission);
        entityManager.persist(blackCarDriver);

        entityManager.persist(silverEngine);
        entityManager.persist(silverAero);
        entityManager.persist(silverBreaks);
        entityManager.persist(silverSuspension);
        entityManager.persist(silverTransmission);
        entityManager.persist(silverCarDriver);

        entityManager.persist(goldEngine);
        entityManager.persist(goldAero);
        entityManager.persist(goldBreaks);
        entityManager.persist(goldSuspension);
        entityManager.persist(goldTransmission);
        entityManager.persist(goldCarDriver);

        entityManager.persist(purpleEngine);
        entityManager.persist(purpleAero);
        entityManager.persist(purpleBreaks);
        entityManager.persist(purpleSuspension);
        entityManager.persist(purpleTransmission);
        entityManager.persist(purpleCarDriver);

        entityManager.persist(blackCar);
        entityManager.persist(silverCar);
        entityManager.persist(goldCar);
        entityManager.persist(purpleCar);

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
        assertThat(teamManager.findTeamByName(blueTeam.getName())).isEqualTo(blueTeam);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findTeamByNullNameTest(){
        teamManager.findTeamByName(null);
    }

    @Test
    public void findTeamByIdTest() {
        assertThat(teamManager.findTeamById(blueTeam.getId())).isEqualTo(blueTeam);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findTeamByNullIdTest(){
        teamManager.findTeamById(null);
    }

    @Test
    public void updateTeamTest() {
        assertThat(em.find(Team.class, blueTeam.getId())).isEqualTo(blueTeam);

        blueTeam.setName("New team name");
        teamManager.updateTeam(blueTeam);

        assertThat(em.find(Team.class, blueTeam.getId())).isEqualTo(blueTeam);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullTeamTest(){
        teamManager.updateTeam(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateTeamWithNullAttributeTest(){
        blueTeam.setId(null);
        teamManager.updateTeam(blueTeam);
    }

    @Test
    public void addTeamTest() {
        teamManager.addTeam(greenTeam);
        assertThat(em.find(Team.class, greenTeam.getId())).isEqualTo(greenTeam);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addNullTeamTest(){
        teamManager.addTeam(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addTeamWithNullAttributeTest(){
        greenTeam.setName(null);
        teamManager.addTeam(greenTeam);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addTeamWithNotNullIdTest(){
        greenTeam.setId(5L);
        teamManager.addTeam(greenTeam);
    }

    @Test
    public void deleteTeamTest() {
        assertThat(em.find(Team.class, blueTeam.getId())).isEqualTo(blueTeam);
        teamManager.deleteTeam(blueTeam);
        assertThat(em.find(Team.class, blueTeam.getId())).isNull();
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullTeamTest(){
        teamManager.deleteTeam(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteTeamWithNullAttributeTest(){
        blueTeam.setId(null);
        teamManager.deleteTeam(null);
    }

    @AfterMethod
    public void tearDown() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Team").executeUpdate();
        entityManager.createQuery("delete from Car").executeUpdate();
        entityManager.createQuery("delete from Driver").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}