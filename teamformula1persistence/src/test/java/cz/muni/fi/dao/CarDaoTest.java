package cz.muni.fi.dao;

import cz.muni.fi.PersistenceApplicationContext;
import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Component;
import cz.muni.fi.entities.Driver;
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
 * @author Richard Hrmo
 */
@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class CarDaoTest extends AbstractTestNGSpringContextTests{

     @PersistenceContext
     private EntityManager em;

     @PersistenceUnit
     private EntityManagerFactory emf;

     @Autowired
     private CarDao carDao;

     private Car ferrari;
     private Car bmw;
     private Car skoda;
     private Driver babyDriver;

     @BeforeMethod
     public void setup() {
          em = emf.createEntityManager();
          ferrari = new Car();
          bmw = new Car();
          skoda = new Car();

          //TODO: nenastavuje vsetky atributy pada na anotacii notnull

          babyDriver = new Driver();
          babyDriver.setName("Baby");
          babyDriver.setSurname("Driver");
          babyDriver.setNationality("Slovak");
          babyDriver.setAsMainDriver();
          ferrari.setDriver(babyDriver);

          EntityManager entityManager = emf.createEntityManager();
          entityManager.getTransaction().begin();
          entityManager.persist(babyDriver);
          entityManager.persist(ferrari);
          entityManager.persist(bmw);
          entityManager.persist(skoda);
          entityManager.getTransaction().commit();
          entityManager.close();
     }

     @Test
     public void listAllCarsTest(){
          assertThat(carDao.listAllCars()).containsExactly(ferrari,bmw,skoda);
     }

     @Test
     public void findCarByIdTest(){
          assertThat(carDao.findCar(ferrari.getId())).isEqualTo(ferrari);
     }

     @Test
     public void findCarByDriverTest(){
          assertThat(carDao.findCar(babyDriver.getId())).isEqualTo(ferrari);
     }

     @Test
     public void addCarTest(){
          Car bestCar = new Car();
          assertThat(carDao.findCar(bestCar.getId())).isNull();

          em.getTransaction().begin();
          em.persist(bestCar);
          em.getTransaction().commit();
          em.close();

          assertThat(carDao.findCar(bestCar.getId())).isEqualTo(bestCar);
     }

     @Test
     public void updateCarTest(){
          Driver ahole = new Driver();

          assertThat(em.find(Car.class, bmw.getId())).isEqualTo(bmw);
          bmw.setDriver(ahole);
          carDao.updateCar(bmw);

          Car findBmw = em.find(Car.class, bmw.getId());
          assertThat(findBmw.getDriver()).isEqualTo(ahole);
     }

     @Test
     public void deleteCarTest(){
          assertThat(carDao.findCar(skoda.getId())).isEqualTo(skoda);
          carDao.deleteCar(skoda);
          assertThat(carDao.findCar(skoda.getId())).isNull();
     }

     @AfterMethod
     public void tearDown() {
          EntityManager entityManager = emf.createEntityManager();
          entityManager.getTransaction().begin();
          entityManager.createQuery("delete from Car").executeUpdate();
          entityManager.getTransaction().commit();
          entityManager.close();
     }
}
