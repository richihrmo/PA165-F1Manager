package cz.muni.fi.dao;

import cz.muni.fi.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

/**
 * @author Robert Tamas
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Collection<User> findAllUsers() {
        System.out.println(em.getProperties().toString());
        return em.createQuery("select u from Users u", User.class)
                .getResultList();
    }

    @Override
    public User findUserByEmail(String email) {
        if (email == null) throw new IllegalArgumentException("find: email is null");

        try {
            return em.createQuery("select u from Users u where u.email = :email", User.class)
                    .setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User findUserById(Long id) {
        if (id == null) throw new IllegalArgumentException("find: email is null");

        try {
            return em.createQuery("select u from Users u where u.id = :id", User.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User addUser(User user) {
        validate(user);
        em.persist(user);
        return user;
    }


    private void validate(User user) {
        if (user == null) throw new IllegalArgumentException("user is null");
        if (user.getId() != null) throw new IllegalArgumentException("id must be null");
        if (user.getName() == null) throw new IllegalArgumentException("name is null");
        if (user.getPasswordHash() == null) throw new IllegalArgumentException("password hash is null");
    }
}
