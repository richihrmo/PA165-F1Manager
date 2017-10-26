package cz.muni.fi;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Author: Richard Hrmo
 */
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager em;

    public List<Person> listDrivers() {
        return em.createQuery("select p from Person p", Person.class)
                .getResultList();
    }

    public List<Person> listTestDrivers() {
        return em.createQuery("select p from Person p where p.istest = false", Person.class)
                .getResultList();
    }

    public Person findPerson(long id) {
        return em.find(Person.class, id);
    }

    public Person findDriver(String name, String surname) {
        return em.createQuery("select p from Person p where name = :name and surname = :surname", Person.class)
                .setParameter(":name", name)
                .setParameter(":surname", surname)
                .getSingleResult();
    }

    public Person findTestDriver(String name, String surname) {
        return em.createQuery("select p from Person p where p.istest = false and name = :name and surname = :surname", Person.class)
                .setParameter(":name", name)
                .setParameter(":surname", surname)
                .getSingleResult();
    }

    public void addPerson(Person person) {
        em.persist(person);
    }

    public void updatePerson(Person person) {
        em.merge(person);
    }

    public void deletePerson(Person person) {
        em.remove(person);
    }
}
