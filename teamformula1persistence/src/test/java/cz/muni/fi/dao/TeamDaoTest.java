package cz.muni.fi.dao;

import cz.muni.fi.PersistenceApplicationContext;
import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Team;
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
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class TeamDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    private TeamDao teamManager;

    private Team blueTeam;
    private Team redTeam;

    //TODO: to iste ako cardao

    @BeforeMethod
    public void setup() {
        Car blackCar = new Car();
        Car silverCar = new Car();
        Car goldCar = new Car();
        Car purpleCar = new Car();

        blueTeam = new Team("Blue team", blackCar, silverCar);
        redTeam = new Team("Red team", goldCar, purpleCar);

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(blackCar);
        entityManager.persist(silverCar);
        entityManager.persist(goldCar);
        entityManager.persist(purpleCar);
        entityManager.persist(blueTeam);
        entityManager.persist(redTeam);
        entityManager.getTransaction().commit();
        entityManager.close();
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
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Team").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}