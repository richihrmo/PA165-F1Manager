package cz.muni.fi.facade;

import cz.muni.fi.dto.UserCreateDTO;
import cz.muni.fi.dto.UserDTO;

import java.util.Collection;

/**
 * @author Robert Tamas
 */
public interface UserFacade {

    Collection<UserDTO> findAllUsers();

    UserDTO findUserByEmail(String email);

    UserDTO findUserById(Long id);

    void deleteUser(UserDTO user);

    UserDTO updateUser(UserDTO user);

    UserDTO addUser(UserCreateDTO user);

    boolean authenticate(String email, String password);
}
