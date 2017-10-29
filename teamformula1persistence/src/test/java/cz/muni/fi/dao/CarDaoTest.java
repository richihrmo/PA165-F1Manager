package cz.muni.fi.dao;

import cz.muni.fi.PersistenceApplicationContext;
import cz.muni.fi.dao.CarDaoImpl;
import cz.muni.fi.entities.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 * @author Richard Hrmo
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class CarDaoTest extends AbstractTestNGSpringContextTests{
     @PersistenceContext
     private EntityManager em;

     @PersistenceUnit
     private EntityManagerFactory emf;

     @Autowired
     private CarDaoImpl carDao = new CarDaoImpl();

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

          babyDriver = new Driver();
          ferrari.setDriver(babyDriver);

          em.getTransaction().begin();
          em.persist(ferrari);
          em.persist(bmw);
          em.persist(skoda);
          em.getTransaction().commit();
          em.close();
     }

     @Test
     public void listAllCarsTest(){
          assertThat(carDao.listAllCars()).containsExactly(ferrari,bmw,skoda);
     }

     @Test
     public void findCarTest(){
          assertThat(carDao.findCar(ferrari.getId)).isEqualTo(ferrari);
     }

     @Test
     public void findCarTest(){
          assertThat(carDao.findCar(babyDriver.getId)).isEqualTo(ferrari);
     }

     @Test
     public void addCarTest(){
          Car bestCar = new Car();
          assertThat(carDao.findCar(bestCar.getId)).isNull();

          em.getTransaction().begin();
          em.persist(bestCar);
          em.getTransaction().commit();
          em.close();

          assertThat(carDao.findCar(bestCar.getId)).isEqualTo(bestCar);
     }

     @Test
     public void updateCarTest(){
          Driver ahole = new Driver();

          assertThat(em.find(Car.class, bmw.getId)).isEqualTo(bmw);
          bmw.setDriver(ahole);
          carDao.updateCar(bmw);

          Car findBmw = em.find(Car.class, bmw.getId);
          assertThat(findBmw.getDriver).isEqualTo(ahole);
     }

     @Test
     public void deleteCarTest(){
          assertThat(carDao.findCar(skoda.getId)).isEqualTo(skoda);
          carDao.deleteCar(skoda);
          assertThat(carDao.findCar(skoda.getId)).isNull;
     }
}
