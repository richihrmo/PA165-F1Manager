package cz.muni.fi.facade;

import cz.muni.fi.dto.UserCreateDTO;
import cz.muni.fi.dto.UserDTO;

import java.util.Collection;

/**
 * @author Robert Tamas
 */
public interface UserFacade {

    /**
     * Get all users
     *
     * @return collection of all users
     */
    Collection<UserDTO> findAllUsers();

    /**
     * Find user by email
     *
     * @param email user email
     * @return found user
     */
    UserDTO findUserByEmail(String email);

    /**
     * Add new user to DB
     *
     * @param user new user
     * @return created user
     */
    UserDTO addUser(UserCreateDTO user);

    /**
     * Authenticate user
     *
     * @param email email
     * @param password password
     * @return true or false
     */
    boolean authenticate(String email, String password);
}
