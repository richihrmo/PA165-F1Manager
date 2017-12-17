package cz.muni.fi.service;

import cz.muni.fi.entities.User;

import java.util.Collection;

/**
 * @author Robert Tamas
 */
public interface UserService {

    Collection<User> findAllUsers();

    User findUserByEmail(String email);

    User findUserById(Long id);

    void deleteUser(User user);

    User updateUser(User user);

    User addUser(User user, String password);

    boolean authenticate(User user, String password);
}
