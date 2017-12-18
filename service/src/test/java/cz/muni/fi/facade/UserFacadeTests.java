package cz.muni.fi.facade;

import cz.muni.fi.dto.UserCreateDTO;
import cz.muni.fi.dto.UserDTO;
import cz.muni.fi.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Richard Hrmo
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserFacadeTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserFacade userFacade;

    private UserDTO admin;
    private UserDTO randomUser;
    private UserDTO engineer;
    private UserCreateDTO createRandomUser;

    @BeforeClass
    public void setup(){
        UserCreateDTO createAdmin = new UserCreateDTO();
        createAdmin.setName("admin");
        createAdmin.setPassword("admin");
        createAdmin.setEmail("admin@f1racing.com");
        createAdmin.setAdmin(true);
        admin = userFacade.addUser(createAdmin);

        createRandomUser = new UserCreateDTO();
        createRandomUser.setName("PussyDestroyer420");
        createRandomUser.setPassword("1488");
        createRandomUser.setEmail("pussydestroyer@f1racing.com");

        UserCreateDTO createEngineer = new UserCreateDTO();
        createEngineer.setName("engineer");
        createEngineer.setPassword("nbu123");
        createEngineer.setEmail("engineer@f1racing.com");
        engineer = userFacade.addUser(createEngineer);
    }

    @Test
    public void addUserTest(){
        randomUser = userFacade.addUser(createRandomUser);
        assertThat(userFacade.findUserByEmail(randomUser.getEmail())).isEqualTo(randomUser);
    }

    @Test
    public void authenticateTest(){
        assertThat(userFacade.authenticate(admin.getEmail(), "")).isEqualTo(false);
        assertThat(userFacade.authenticate(admin.getEmail(), "admin")).isEqualTo(true);
    }

    @Test
    public void findUserByEmailTest(){
        assertThat(userFacade.findUserByEmail(engineer.getEmail())).isEqualTo(engineer);
    }

    @Test
    public void findAllUsers(){
        List<UserDTO> users = userFacade.findAllUsers();
        assertThat(users).contains(admin, engineer, randomUser);
    }
}
