package cz.muni.fi.dao;

import cz.muni.fi.entities.Person;

/**
 * Created by Robert on 25/10/2017.
 */
public interface CarDao {

    public List<Car> listAllCars();

    public Car findCar(Person person);

    public Car findCar(Long id);

    public void addCar(Car car);

    public void updateCar(Car car);

    public void deleteCar(Car car);
}
