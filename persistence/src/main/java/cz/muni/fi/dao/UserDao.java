package cz.muni.fi.dao;

import cz.muni.fi.entities.User;

import java.util.Collection;

/**
 * @author Robert Tamas
 */
public interface UserDao {

    Collection<User> findAllUsers();

    User findUserByEmail(String email);

    User findUserById(Long id);

    User deleteUser(User user);

    User updateUser(User user);

    User addUser(User user);
}
