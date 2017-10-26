package cz.muni.fi.dao;

import cz.muni.fi.entities.Car;

/**
 * 
 * @author Lucie Kureckova, 445264
 */
public interface TeamDao {
    
    /**
     * 
     * @param car 
     */
    public void changeFirstCar(Car car);
    
    public void changeSecondCar(Car car);
    
}
