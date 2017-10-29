package cz.muni.fi;

import java.util.List;

/**
 * @author Richard Hrmo
 */
public interface DriverDao{
    /**
     * @return list of all drivers
     */
    public List<Driver> listDrivers();

    /**
     * @return list of drivers not listed as main drivers
     */
    public List<Driver> listTestDrivers();

    /**
     * find person by ID
     * @param id
     * @return person
     */
    public Driver findDriver(long id);

    /**
     * find person-driver based on name
     * @param name
     * @param surname
     * @return
     */
    public Driver findDriver(String name, String surname);

    /**
     * find person-test driver based on name
     * @param name
     * @param surname
     * @return
     */
    public Driver findTestDriver(String name, String surname);

    /**
     * add driver
     * @param driver
     */
    public void addDriver(Driver driver);

    /**
     * update driver
     * @param driver
     */
    public void updateDriver(Driver driver);

    /**
     * delete driver
     * @param driver
     */
    public void deleteDriver(Driver driver);

}
