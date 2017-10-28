import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.dao.DriverDao;
import cz.muni.fi.dao.DriverDaoImpl;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * @author Lucie Kureckova, 445264
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class DriverDaoTest extends AbstractTestNGSpringContextTests {
    
    @PersistenceContext
    private EntityManager em;
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    @Autowired
    private DriverDao driverManager = new DriverDaoImpl();
    
    private Driver mainDriver1 = new Driver();
    private Driver testDriver1 = new Driver();
    private Driver testDriver2 = new Driver();
    
    
    @BeforeMethod
    public void setUp(){
        mainDriver1.setAsMainDriver();
        mainDriver1.setName("Michael");
        mainDriver1.setSurname("Schumacher");
        mainDriver1.setNationality("germany");
        
        testDriver1.setName("Alan");
        testDriver1.setSurname("Rickman");
        
        em.getTransaction().begin();
        em.persist(mainDriver1);
        em.persist(testDriver1);
        em.persist(testDriver2);
        em.getTransaction().commit();
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
    
    @Test
    public void findDriverByNameTest(){
        assertThat(driverManager.findDriverByName(mainDriver1.getName(), mainDriver1.getSurname())).isEqualTo(mainDriver1);
        assertThat(driverManager.findDriverByName("sasa", "sasula")).isNull();
    }
    
    @Test
    public void findDeveloperTest(){
        assertThat(driverManager.findDeveloper(mainDriver1.getName(), mainDriver1.getSurname())).isNull();
        assertThat(driverManager.findDeveloper(testDriver1.getName(), testDriver1.getSurname())).isEqualTo(testDriver1);
    }
    
    @Test
    public void addDriverTest(){
        Driver driver1 = new Driver();
        driver1.setName("Albus Percival Wulfric Brian");
        driverManager.addDriver(driver1);
        assertThat(em.find(Driver.class, driver1.getId())).isEqualTo(driver1);
    }
    
    @Test
    public void updateDriverTest(){
        Driver driver2 = new Driver();
        driver2.setName("James");
        em.getTransaction().begin();
        em.persist(driver2);
        em.getTransaction().commit();
        driver2.setName("Harry");
        driver2.setSurname("Potter");
        driverManager.updateDriver(driver2);
        assertThat(em.find(Driver.class, driver2.getId())).isEqualTo(driver2);
    }
    
    @Test
    public void deleteDriverTest(){
        Driver driver3 = new Driver();
        em.getTransaction().begin();
        em.persist(driver3);
        em.getTransaction().commit();
        driverManager.deleteDriver(driver3);
        assertThat(em.find(Driver.class, driver3.getId())).isNull();
    }
    
    @AfterMethod
    public void tearDown() {
        em.getTransaction().begin();
        em.createQuery("delete from Driver").executeUpdate();
        em.getTransaction().commit();
    }
}
