package cz.muni.fi.dao;

import cz.muni.fi.PersistenceApplicationContext;
import cz.muni.fi.entities.Component;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Robert Tamas
 */
@Transactional
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class ComponentDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    private ComponentDao componentManager;

    private Component engine;
    private Component aerodynamic;
    private Component transmission;

    @BeforeMethod
    public void setup() {
        transmission = new Component();
        transmission.setName("Transmission");
        transmission.setComponentType(ComponentType.TRANSMISSION);
        transmission.setAvailability(true);

        engine = new Component();
        engine.setName("Engine");
        engine.setComponentType(ComponentType.ENGINE);
        engine.setAvailability(false);

        aerodynamic = new Component();
        aerodynamic.setName("Aerodynamic");
        aerodynamic.setComponentType(ComponentType.AERODYNAMICS);
        aerodynamic.setAvailability(true);

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(transmission);
        entityManager.persist(engine);
        entityManager.persist(aerodynamic);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void listAvailableComponentsTest() {
        List<Component> foundAvailableComponents = componentManager.listAvailableComponents();
        assertThat(foundAvailableComponents).containsExactlyInAnyOrder(transmission, aerodynamic);
    }

    @Test
    public void listAllComponentsTest() {
        List<Component> foundComponents = componentManager.listAllComponents();
        assertThat(foundComponents).containsExactlyInAnyOrder(transmission, engine, aerodynamic);
    }

    @Test
    public void findComponentByNameTest() {
        Component foundComponent = componentManager.findComponentByName(engine.getName());
        assertThat(foundComponent).isEqualTo(engine);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findComponentByNullNameTest(){
        componentManager.findComponentByName(null);
    }

    @Test
    public void findComponentByIDTest() {
        Component foundComponent = componentManager.findComponentById(aerodynamic.getId());
        assertThat(foundComponent).isEqualTo(aerodynamic);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findComponentByNullIdTest(){
        componentManager.findComponentById(null);
    }

    @Test
    public void addComponentTest() {
        Component breaks = new Component();
        breaks.setName("Breaks");
        breaks.setComponentType(ComponentType.BRAKES);
        breaks.setAvailability(true);

        componentManager.addComponent(breaks);
        assertThat(em.find(Component.class, breaks.getId())).isEqualTo(breaks);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addNullComponentTest(){
        componentManager.addComponent(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addComponentWithNullAttributesTest(){
        Component breaks = new Component();
        componentManager.addComponent(breaks);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addComponentWithNotNullIdTest(){
        componentManager.addComponent(engine);
    }


    @Test
    public void updateComponent() {
        assertThat(em.find(Component.class, engine.getId())).isEqualTo(engine);

        engine.setName("Engine with edited name");
        engine.setAvailability(true);
        componentManager.updateComponent(engine);

        assertThat(em.find(Component.class, engine.getId())).isEqualTo(engine);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullComponentTest(){
        componentManager.updateComponent(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateComponentWithNullAttributesTest(){
        engine.setId(null);
        componentManager.updateComponent(engine);
    }

    @Test
    public void deleteComponentTest() {
        assertThat(em.find(Component.class, engine.getId())).isEqualTo(engine);
        componentManager.deleteComponent(engine);
        assertThat(em.find(Component.class, engine.getId())).isNull();
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullComponentTest(){
        componentManager.deleteComponent(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteComponentWithNullAttributesTest(){
        engine.setId(null);
        componentManager.deleteComponent(engine);
    }

    @AfterMethod
    public void tearDown() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Component ").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
