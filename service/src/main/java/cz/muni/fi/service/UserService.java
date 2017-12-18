package cz.muni.fi.service;

import cz.muni.fi.entities.User;
import cz.muni.fi.service.exception.ServiceDataAccessException;

import java.util.Collection;

/**
 * @author Robert Tamas
 */
public interface UserService {

    /**
     * Get all users
     *
     * @return collection of users
     * @throws ServiceDataAccessException in case of exception on dao or api layer
     */
    Collection<User> findAllUsers();

    /**
     * Find user by email
     *
     * @param email email
     * @return found user or null
     * @throws IllegalArgumentException if email is null
     * @throws ServiceDataAccessException in case of exception on dao or api layer
     */
    User findUserByEmail(String email);

    /**
     * Find user by id
     *
     * @param id user id
     * @return found user
     * @throws IllegalArgumentException if id is null
     * @throws ServiceDataAccessException in case of exception on dao or api layer
     */
    User findUserById(Long id);

    /**
     * Add new user to DB
     *
     * @param user user
     * @param password password
     * @return new user added to db
     * @throws IllegalArgumentException if user or password is null
     * @throws ServiceDataAccessException in case of exception on dao or api layer
     */
    User addUser(User user, String password);

    /**
     * Authenticate existing user
     *
     * @param user user
     * @param password password
     * @return true or false
     * @throws IllegalArgumentException if user or password is null
     * @throws ServiceDataAccessException in case of exception on dao or api layer
     */
    boolean authenticate(User user, String password);
}
