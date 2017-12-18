package cz.muni.fi.dao;

import cz.muni.fi.entities.User;

import java.util.Collection;

/**
 * @author Robert Tamas
 */
public interface UserDao {
    /**
     * Find all users in DB
     *
     * @return collection of all users
     */
    Collection<User> findAllUsers();

    /**
     * Find user byt email
     *
     * @param email user email
     * @return found user
     * @throws IllegalArgumentException if email is null
     */
    User findUserByEmail(String email);

    /**
     * Find user by id
     *
     * @param id user id
     * @return found user
     * @throws IllegalArgumentException if id is null
     */
    User findUserById(Long id);

    /**
     * Add new user to db
     *
     * @param user new user
     * @return created user
     * @throws IllegalArgumentException if user is null or user id is not null,
     *         or other user attributes are null
     */
    User addUser(User user);
}
