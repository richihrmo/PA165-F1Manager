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
     private Driver ferrariDriver;

     @BeforeMethod
     public void setup() {
          ferrariDriver = new Driver("Jan", "Sikmy", "France");
          Component aerodynamicsFerrari = new Component("Aerodynamics Ferrari", true, ComponentType.AERODYNAMICS);
          Component brakesFerrari = new Component("Brakes Ferrari", true, ComponentType.BRAKES);
          Component engineFerrari = new Component("Engine Ferrari", true, ComponentType.ENGINE);
          Component suspensionFerrari = new Component("Suspension Ferrari", true, ComponentType.SUSPENSION);
          Component transmissionFerrari = new Component("Transmission Ferrari", true, ComponentType.TRANSMISSION);
          ferrari = new Car(ferrariDriver, engineFerrari, aerodynamicsFerrari, suspensionFerrari, transmissionFerrari, brakesFerrari);

          Driver bmwDriver = new Driver("Peter", "Dan", "Dansko");
          Component aerodynamicsBmw = new Component("Aerodynamics Bmw", true, ComponentType.AERODYNAMICS);
          Component brakesBmw = new Component("Brakes Bmw", true, ComponentType.BRAKES);
          Component engineBmw = new Component("Engine Bmw", true, ComponentType.ENGINE);
          Component suspensionBmw = new Component("Suspension Bmw", true, ComponentType.SUSPENSION);
          Component transmissionBmw = new Component("Transmission Bmw", true, ComponentType.TRANSMISSION);
          bmw = new Car(bmwDriver, engineBmw, aerodynamicsBmw, suspensionBmw, transmissionBmw, brakesBmw);

          Driver randomDriverB = new Driver("Dominika", "Hrozna", "Holandsko");
          Component aerodynamicsSkoda = new Component("Aerodynamics Skoda", true, ComponentType.AERODYNAMICS);
          Component brakesSkoda = new Component("Brakes Skoda", true, ComponentType.BRAKES);
          Component engineSkoda = new Component("Engine Skoda", true, ComponentType.ENGINE);
          Component suspensionSkoda = new Component("Suspension Skoda", true, ComponentType.SUSPENSION);
          Component transSkoda = new Component("Transmission Skoda", true, ComponentType.TRANSMISSION);
          skoda = new Car(randomDriverB, engineSkoda, aerodynamicsSkoda, suspensionSkoda, transSkoda, brakesSkoda);

          EntityManager entityManager = emf.createEntityManager();
          entityManager.getTransaction().begin();

          entityManager.persist(aerodynamicsFerrari);
          entityManager.persist(brakesFerrari);
          entityManager.persist(engineFerrari);
          entityManager.persist(suspensionFerrari);
          entityManager.persist(transmissionFerrari);

          entityManager.persist(aerodynamicsBmw);
          entityManager.persist(brakesBmw);
          entityManager.persist(engineBmw);
          entityManager.persist(suspensionBmw);
          entityManager.persist(transmissionBmw);

          entityManager.persist(aerodynamicsSkoda);
          entityManager.persist(brakesSkoda);
          entityManager.persist(engineSkoda);
          entityManager.persist(suspensionSkoda);
          entityManager.persist(transSkoda);

          entityManager.persist(bmwDriver);
          entityManager.persist(randomDriverB);
          entityManager.persist(ferrariDriver);

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
          assertThat(carDao.findCar(ferrariDriver)).isEqualTo(ferrari);
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
          entityManager.createQuery("delete from Driver").executeUpdate();
          entityManager.getTransaction().commit();
          entityManager.close();
     }
}
