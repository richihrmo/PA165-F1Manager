package cz.muni.fi;

import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Component;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.persistanceEnums.ComponentType;

public class UtilityClass {
    public static Car createSampleCar(String driverName, String driverSurname) {
        Driver driver = new Driver(driverName, driverSurname, "US");
        Component engine = new Component("Engine Black", false, ComponentType.ENGINE);
        Component aerodynamics = new Component("Aero Black", false, ComponentType.AERODYNAMICS);
        Component breaks = new Component("Breaks Black", false, ComponentType.BRAKES);
        Component suspension = new Component("Susp Black", false, ComponentType.SUSPENSION);
        Component transmission = new Component("Transmission Black", false, ComponentType.TRANSMISSION);
        return new Car(driver, engine, aerodynamics, suspension, transmission, breaks);
    }
}
