package cz.muni.fi.dao;

import cz.muni.fi.PersistenceApplicationContext;
import cz.muni.fi.entities.User;
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
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Matus Macko
 */
@Transactional
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    private UserDao userManager;

    private User newUserOne;
    private User newUserTwo;

    @BeforeMethod
    public void setup() {
        newUserOne = new User();
        newUserOne.setName("Random");
        newUserOne.setEmail("random@random.com");
        newUserOne.setPasswordHash("12345");
        
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(newUserOne);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void findAllUsersTest() {
        Collection<User> foundAvailableComponents = userManager.findAllUsers();
        assertThat(foundAvailableComponents).contains(newUserOne);
    }

    @Test
    public void findUserByEmailTest() {
        User foundUser = userManager.findUserByEmail("random@random.com");
        assertThat(foundUser).isEqualTo(newUserOne);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findUserByNullEmailTest(){
        userManager.findUserByEmail(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findUserByNullIdTest(){
        userManager.findUserById(null);
    }

    @Test
    public void findUserByIdTest() {
        User foundUser = userManager.findUserById(newUserOne.getId());
        assertThat(foundUser).isEqualTo(newUserOne);
    }

    @Test
    public void addUserTest() {
        User newUserThree = new User();
        newUserThree.setName("New name");
        newUserThree.setEmail("random@ewqeq.com");
        newUserThree.setPasswordHash("12345");
        newUserThree.setAdmin(false);

        userManager.addUser(newUserThree);
        assertThat(em.find(User.class, newUserThree.getId())).isEqualTo(newUserThree);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addNullUserTest() {
        userManager.addUser(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addUserWithNotNullIdTest() {
        userManager.addUser(newUserOne);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addUserWithNullEmailTest() {
        User newUserThree = new User();
        newUserThree.setEmail(null);
        newUserThree.setName("New name");
        newUserThree.setPasswordHash("12345");
        userManager.addUser(newUserOne);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addUserWithNullPasswordTest() {
        User newUserThree = new User();
        newUserThree.setEmail("email");
        newUserThree.setName("New name");
        newUserThree.setPasswordHash(null);
        userManager.addUser(newUserOne);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addUserWithNullNameTest() {
        User newUserThree = new User();
        newUserThree.setEmail("email");
        newUserThree.setName(null);
        newUserThree.setPasswordHash("dasda");
        userManager.addUser(newUserOne);
    }

    @AfterMethod
    public void tearDown() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Users").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
