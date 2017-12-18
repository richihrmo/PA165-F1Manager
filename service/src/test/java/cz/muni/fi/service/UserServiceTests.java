package cz.muni.fi.service;

import cz.muni.fi.dao.UserDao;
import cz.muni.fi.entities.User;
import cz.muni.fi.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author Richard Hrmo
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserServiceTests extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    private UserDao userDao;

    @Autowired
    @InjectMocks
    private UserService userService = new UserServiceImpl();

    private User admin = new User();
    private User randomUser = new User();
    private User engineer = new User();

    private Map<Long, User> users = new HashMap<>();

    @BeforeClass
    public void mockitoSetup(){
        MockitoAnnotations.initMocks(this);

        when(userDao.addUser(any(User.class))).then(invoke -> {
           User mockedUser = invoke.getArgumentAt(0, User.class);
           if (mockedUser == null) throw new IllegalArgumentException("User is null");
           if (mockedUser.getId() != null) throw new IllegalArgumentException("User id must be null");
           if (mockedUser.getEmail() == null
                   || mockedUser.getName() == null
                   || mockedUser.getPasswordHash() == null) throw new IllegalArgumentException("User argument is null");

           mockedUser.setId(Long.valueOf(users.size()));

           users.put(mockedUser.getId(), mockedUser);
           return mockedUser;
        });

        when(userDao.findUserById(anyLong())).then(invoke -> {
            Long id = invoke.getArgumentAt(0, Long.class);
            if (id == null) throw new IllegalArgumentException("id is null");
            return users.get(id);
        });

        when(userDao.findUserByEmail(anyString())).then(invoke -> {
            String email = invoke.getArgumentAt(0, String.class);
            if (email == null) throw new IllegalArgumentException("email is null");
            for(User user : users.values()){
                if(user.getEmail().equals(email)){
                    return user;
                }
            }
            return null;
        });

        when(userDao.findAllUsers()).then(invoke -> Collections.unmodifiableList(new ArrayList<>(users.values())));
    }

    @BeforeClass
    public void setup(){
        admin.setName("admin");
        admin.setEmail("admin@f1racing.com");
        admin.setAdmin(true);
        userService.addUser(admin, "admin");

        randomUser.setName("PussyDestroyer420");
        randomUser.setEmail("pussydestroyer@f1racing.com");
        userService.addUser(randomUser, "1488");

        engineer.setName("engineer");
        engineer.setEmail("engineer@f1racing.com");
        userService.addUser(engineer, "NikolaTesla");
    }

    @Test
    public void addUserTest(){
        User new_user = new User("newUser@f1racing.com", false, "newUser");
        userService.addUser(new_user, "idontknowmypassword");
        assertThat(users.values()).contains(new_user);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addNullUserTest(){
        userService.addUser(null, "pux");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addUserNullPasswordTest(){
        userService.addUser(admin,null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void addUserNullEmailTest(){
        User new_user = new User(null, false, "newUser");
        userService.addUser(new_user, "pux");
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void addUserNullNameTest(){
        User new_user = new User("newUser@f1racing.com", false, null);
        userService.addUser(new_user, "pux");
    }

    @Test
    public void findUserByIdTest(){
        assertThat(userService.findUserById(admin.getId())).isEqualTo(admin);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findUserByNullIdTest(){
        userService.findUserById(null);
    }

    @Test
    public void findUserByEmailTest(){
        String email = admin.getEmail();
        User user = userService.findUserByEmail(email);
        assertThat(users.get(user.getId())).isEqualTo(admin);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findUserByNullEmailTest(){
        userService.findUserByEmail(null);
    }

    @Test
    public void findAllUsersTest(){
        assertThat(userService.findAllUsers()).hasSize(users.values().size());
    }

    @Test
    public void authenticateTest(){
        assertThat(userService.authenticate(admin, "a")).isEqualTo(false);
        assertThat(userService.authenticate(admin, "admin")).isEqualTo(true);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void authenticateNullUserTest(){
        userService.authenticate(null, "");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void authenticateNullPasswordTest(){
        userService.authenticate(admin, null);
    }

}
