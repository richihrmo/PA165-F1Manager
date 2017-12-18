package cz.muni.fi.service.facade;

import cz.muni.fi.dto.UserCreateDTO;
import cz.muni.fi.dto.UserDTO;
import cz.muni.fi.entities.User;
import cz.muni.fi.facade.UserFacade;
import cz.muni.fi.service.BeanMappingService;
import cz.muni.fi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @author Robert Tamas
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public List<UserDTO> findAllUsers() {
        return beanMappingService.mapTo(userService.findAllUsers(), UserDTO.class);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User user = userService.findUserByEmail(email);
        if (user == null) {
            return null;
        }
        return beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public UserDTO addUser(UserCreateDTO userDTO) {
        User user = beanMappingService.mapTo(userDTO, User.class);
        user = userService.addUser(user, userDTO.getPassword());
        return beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public boolean authenticate(String email, String password) {
        User user = userService.findUserByEmail(email);
        return user != null && userService.authenticate(user, password);
    }
}
