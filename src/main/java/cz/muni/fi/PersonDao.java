package cz.muni.fi;

import java.util.List;

/**
 * Author: Richard Hrmo
 */
public interface PersonDao {

    /**
     * @return list of all drivers
     */
    public List<Person> listDrivers();

    /**
     * @return list of drivers not listed as main drivers
     */
    public List<Person> listTestDrivers();

    /**
     * find person by ID
     * @param id
     * @return person
     */
    public Person findPerson(long id);

    /**
     * find person-driver based on name
     * @param name
     * @param surname
     * @return
     */
    public Person findDriver(String name, String surname);

    /**
     * find person-test driver based on name
     * @param name
     * @param surname
     * @return
     */
    public Person findTestDriver(String name, String surname);

    public void addPerson(Person person);

    public void updatePerson(Person person);

    public void deletePerson(Person person);

}
