package cz.muni.fi.dao;

import cz.muni.fi.PersistenceApplicationContext;
import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Component;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.enums.ComponentType;
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

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Richard Hrmo
 */
@Transactional
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
     private Driver driver;

     @BeforeMethod
     public void setup() {
          em = emf.createEntityManager();
          ferrari = new Car();
          bmw = new Car();
          skoda = new Car();

          driver = new Driver("Jan", "Sikmy", "France");
          ferrari.setDriver(driver);
          Component aeroA = new Component("Aero A", true, ComponentType.AERODYNAMICS);
          Component brakesA = new Component("Brakes A", true, ComponentType.BRAKES);
          Component engineA = new Component("Engine A", true, ComponentType.ENGINE);
          Component suspeA = new Component("Suspe A", true, ComponentType.SUSPENSION);
          Component transA = new Component("Trans A", true, ComponentType.TRANSMISSION);
          ferrari.setAerodynamics(aeroA);
          ferrari.setBrakes(brakesA);
          ferrari.setEngine(engineA);
          ferrari.setSuspension(suspeA);
          ferrari.setTransmission(transA);

          Driver randomDriverA = new Driver("Peter", "Dan", "Dansko");
          bmw.setDriver(randomDriverA);
          Component aeroB = new Component("Aero B", true, ComponentType.AERODYNAMICS);
          Component brakesB = new Component("Brakes B", true, ComponentType.BRAKES);
          Component engineB = new Component("Engine B", true, ComponentType.ENGINE);
          Component suspeB = new Component("Suspe B", true, ComponentType.SUSPENSION);
          Component transB = new Component("Trans B", true, ComponentType.TRANSMISSION);
          bmw.setAerodynamics(aeroB);
          bmw.setBrakes(brakesB);
          bmw.setEngine(engineB);
          bmw.setSuspension(suspeB);
          bmw.setTransmission(transB);

          Driver randomDriverB = new Driver("Dominika", "Hrozna", "Holandsko");
          skoda.setDriver(randomDriverB);
          Component aeroC = new Component("Aero C", true, ComponentType.AERODYNAMICS);
          Component brakesC = new Component("Brakes C", true, ComponentType.BRAKES);
          Component engineC = new Component("Engine C", true, ComponentType.ENGINE);
          Component suspeC = new Component("Suspe C", true, ComponentType.SUSPENSION);
          Component transC = new Component("Trans C", true, ComponentType.TRANSMISSION);
          skoda.setAerodynamics(aeroC);
          skoda.setBrakes(brakesC);
          skoda.setEngine(engineC);
          skoda.setSuspension(suspeC);
          skoda.setTransmission(transC);

          EntityManager entityManager = emf.createEntityManager();
          entityManager.getTransaction().begin();
          entityManager.persist(aeroA);
          entityManager.persist(brakesA);
          entityManager.persist(engineA);
          entityManager.persist(suspeA);
          entityManager.persist(transA);
          entityManager.persist(aeroB);
          entityManager.persist(brakesB);
          entityManager.persist(engineB);
          entityManager.persist(suspeB);
          entityManager.persist(transB);
          entityManager.persist(aeroC);
          entityManager.persist(brakesC);
          entityManager.persist(engineC);
          entityManager.persist(suspeC);
          entityManager.persist(transC);
          entityManager.persist(randomDriverA);
          entityManager.persist(randomDriverB);
          entityManager.persist(driver);
          entityManager.persist(ferrari);
          entityManager.persist(bmw);
          entityManager.getTransaction().commit();
          entityManager.close();
     }

     @Test
     public void listAllCarsTest(){
          assertThat(carDao.listAllCars()).containsExactly(ferrari,bmw);
     }

     @Test
     public void findCarByIdTest(){
          assertThat(carDao.findCar(ferrari.getId())).isEqualTo(ferrari);
     }

     @Test
     public void findCarByDriverTest(){
          assertThat(carDao.findCar(driver)).isEqualTo(ferrari);
     }

     @Test
     public void addCarTest(){
          carDao.addCar(skoda);
          assertThat(em.find(Car.class, skoda.getId())).isEqualTo(skoda);
     }

     @Test
     public void updateCarTest(){
          Driver updatedDriver = new Driver("Jan", "Desny", "Japan");
          EntityManager entityManager = emf.createEntityManager();
          entityManager.getTransaction().begin();
          entityManager.persist(updatedDriver);
          entityManager.getTransaction().commit();
          entityManager.close();

          assertThat(em.find(Car.class, bmw.getId())).isEqualTo(bmw);
          bmw.setDriver(updatedDriver);
          carDao.updateCar(bmw);
          assertThat(em.find(Car.class, bmw.getId())).isEqualTo(bmw);
     }

     @Test
     public void deleteCarTest(){
          assertThat(em.find(Car.class, ferrari.getId())).isEqualTo(ferrari);
          carDao.deleteCar(ferrari);
          assertThat(em.find(Car.class, ferrari.getId())).isNull();
     }

     @AfterMethod
     public void tearDown() {
          EntityManager entityManager = emf.createEntityManager();
          entityManager.getTransaction().begin();
          entityManager.createQuery("delete from Car").executeUpdate();
          entityManager.getTransaction().commit();
          entityManager.close();
     }

     private Driver getNewRandomDriver() {
          ArrayList<String> names = new ArrayList<String>() {{
               add("Jan");
               add("Jozef");
               add("Milan");
          }};
          ArrayList<String> surnames = new ArrayList<String>() {{
               add("Maly");
               add("Velky");
               add("Silny");
          }};
          ArrayList<String> nationalities = new ArrayList<String>() {{
               add("Germany");
               add("Slovak");
               add("Italy");
          }};
          Driver driver = new Driver();
          driver.setName(names.get(new Random().nextInt(names.size())));
          driver.setSurname(surnames.get(new Random().nextInt(names.size())));
          driver.setNationality(nationalities.get(new Random().nextInt(names.size())));
          driver.setAsMainDriver();

          EntityManager entityManager = emf.createEntityManager();
          entityManager.getTransaction().begin();
          entityManager.persist(driver);
          entityManager.getTransaction().commit();
          entityManager.close();

          return driver;
     }

     private Car getNewCar() {
          Car car = new Car();

          Component aerodynamics = new Component("Aerodynamics", true, ComponentType.AERODYNAMICS);
          Component brakes = new Component("Brakes", true, ComponentType.BRAKES);
          Component engine = new Component("Engine", true, ComponentType.ENGINE);
          Component suspension = new Component("Suspension", true, ComponentType.SUSPENSION);
          Component transmission = new Component("Transmission", true, ComponentType.TRANSMISSION);
          car.setAerodynamics(aerodynamics);
          car.setBrakes(brakes);
          car.setEngine(engine);
          car.setSuspension(suspension);
          car.setTransmission(transmission);
          car.setDriver(getNewRandomDriver());

          EntityManager entityManager = emf.createEntityManager();
          entityManager.getTransaction().begin();
          entityManager.persist(aerodynamics);
          entityManager.persist(brakes);
          entityManager.persist(engine);
          entityManager.persist(suspension);
          entityManager.getTransaction().commit();
          entityManager.close();

          return car;
     }
}
