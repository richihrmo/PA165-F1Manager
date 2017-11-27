package cz.muni.fi.dao;

import cz.muni.fi.PersistenceApplicationContext;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.persistanceEnums.DrivingSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
 * @author Lucie Kureckova, 445264
 */
@Transactional
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class DriverDaoTest extends AbstractTestNGSpringContextTests {
    
    @PersistenceContext
    private EntityManager em;
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    @Autowired
    private DriverDao driverManager;
    
    private Driver mainDriver1;
    private Driver testDriver1;
    private Driver testDriver2;
    
    
    @BeforeMethod
    public void setUp(){
        mainDriver1 = new Driver();
        mainDriver1.setAsMainDriver();
        mainDriver1.setName("Michael");
        mainDriver1.setSurname("Schumacher");
        mainDriver1.setNationality("germany");
        mainDriver1.setSpecialSkill(DrivingSkill.DRIVING_ON_WET);

        testDriver1 = new Driver();
        testDriver1.setName("Alan");
        testDriver1.setSurname("Rickman");
        testDriver1.setNationality("english");
        testDriver1.setSpecialSkill(DrivingSkill.POWER_SLIDING);

        testDriver2 = new Driver();
        testDriver2.setName("Anakin");
        testDriver2.setSurname("Skywalker");
        testDriver2.setNationality("unknown");
        testDriver1.setSpecialSkill(DrivingSkill.EXTREME_REFLEXES);

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(mainDriver1);
        entityManager.persist(testDriver1);
        entityManager.persist(testDriver2);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    @Test
    public void listDriversTest(){
        assertThat(driverManager.listDrivers()).containsExactlyInAnyOrder(mainDriver1, testDriver1, testDriver2);
    }
    
    @Test
    public void listTestDriversTest(){
        assertThat(driverManager.listTestDrivers()).containsExactlyInAnyOrder(testDriver1, testDriver2);
    }
    
    @Test
    public void findDriverByIdTest(){
        assertThat(driverManager.findDriverById(testDriver1.getId())).isEqualTo(testDriver1);
    }
    
    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void findDriverByNullIdTest(){
        driverManager.findDriverById(null);
    }
    
    @Test
    public void findDriverByNameTest(){
        assertThat(driverManager.findDriverByName(mainDriver1.getName(), mainDriver1.getSurname())).isEqualTo(mainDriver1);
        assertThat(driverManager.findDriverByName("sasa", "sasula")).isNull();
    }
    
    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void findDriverByNullNameTest(){
        driverManager.findDriverByName(null, "Rich");
    }
    
    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void findDriverByNullName2Test(){
        driverManager.findDriverByName("Richard", null);
    }
    
    @Test
    public void findDeveloperTest(){
        assertThat(driverManager.findTestDriver(mainDriver1.getName(), mainDriver1.getSurname())).isNull();
        assertThat(driverManager.findTestDriver(testDriver1.getName(), testDriver1.getSurname())).isEqualTo(testDriver1);
    }
    
    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void findNullDeveloperTest(){
        driverManager.findDriverByName("Richard", null);
    }
    
    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void findNullDeveloper2Test(){
        driverManager.findDriverByName(null, "Rich");
    }
    
    @Test
    public void addDriverTest(){
        Driver driver1 = new Driver();
        driver1.setName("Albus Percival Wulfric Brian");
        driver1.setSurname("Dumbledor");
        driver1.setNationality("english");
        driverManager.addDriver(driver1);
        assertThat(em.find(Driver.class, driver1.getId())).isEqualTo(driver1);
    }
    
    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void addNullDriverTest(){
        driverManager.addDriver(null);
    }
    
    @Test
    public void updateDriverTest(){
        Driver driver2 = new Driver();
        driver2.setName("Tom");
        driver2.setSurname("Riddle");
        driver2.setNationality("english");

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(driver2);
        entityManager.getTransaction().commit();
        entityManager.close();

        driver2.setName("Harry");
        driver2.setSurname("Potter");
        driverManager.updateDriver(driver2);
        assertThat(em.find(Driver.class, driver2.getId())).isEqualTo(driver2);
    }
    
    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void updateNullDriverTest(){
        driverManager.updateDriver(null);
    }
    
    @Test
    public void deleteDriverTest(){
        Driver driver3 = new Driver();
        driver3.setName("Tom");
        driver3.setSurname("Riddle");
        driver3.setNationality("english");

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(driver3);
        entityManager.getTransaction().commit();
        entityManager.close();

        driverManager.deleteDriver(driver3);
        assertThat(em.find(Driver.class, driver3.getId())).isNull();
    }
    
    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void deleteNullDriverTest(){
        driverManager.deleteDriver(null);
    }
    
    @AfterMethod
    public void tearDown() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Driver").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
