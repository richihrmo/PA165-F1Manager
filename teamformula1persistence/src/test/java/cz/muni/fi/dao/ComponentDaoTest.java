package cz.muni.fi.dao;

import cz.muni.fi.PersistenceApplicationContext;
import cz.muni.fi.entities.Component;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Robert Tamas
 */
@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
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
        transmission.setAvailable(true);

        engine = new Component();
        engine.setName("Engine");
        engine.setComponentType(ComponentType.ENGINE);
        engine.setAvailable(false);

        aerodynamic = new Component();
        aerodynamic.setName("Aerodynamic");
        aerodynamic.setComponentType(ComponentType.AERODYNAMICS);
        aerodynamic.setAvailable(true);

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
        assertThat(foundAvailableComponents).containsExactlyInAnyOrder(engine, aerodynamic);
    }

    @Test
    public void listAllComponentsTest() {
        List<Component> foundComponents = componentManager.listAllComponents();
        assertThat(foundComponents).containsExactlyInAnyOrder(transmission, engine, aerodynamic);
    }

    @Test
    public void findComponentByNameTest() {
        Component foundComponent = componentManager.findComponent(engine.getName());
        assertThat(foundComponent).isEqualTo(engine);
    }

    @Test
    public void findComponentByIDTest() {
        Component foundComponent = componentManager.findComponent(aerodynamic.getId());
        assertThat(foundComponent).isEqualTo(aerodynamic);
    }

    @Test
    public void addComponentTest() {
        Component breaks = new Component();
        breaks.setName("Breaks");
        breaks.setComponentType(ComponentType.BRAKES);
        breaks.setAvailable(true);

        componentManager.addComponent(breaks);
        assertThat(em.find(Component.class, breaks.getId())).isEqualTo(breaks);
    }

    @Test
    public void updateComponent() {
        assertThat(em.find(Component.class, engine.getId())).isEqualTo(engine);

        engine.setName("Engine with edited name");
        engine.setAvailable(true);
        componentManager.updateComponent(engine);

        assertThat(em.find(Component.class, engine.getId())).isEqualTo(engine);
    }

    @Test
    public void deleteComponentTest() {
        assertThat(em.find(Component.class, engine.getId())).isEqualTo(engine);
        componentManager.deleteComponent(engine);
        assertThat(em.find(Component.class, engine.getId())).isNull();
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
