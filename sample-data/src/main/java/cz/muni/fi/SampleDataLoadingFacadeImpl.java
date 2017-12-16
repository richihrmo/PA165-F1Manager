package cz.muni.fi;


import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.entities.Team;
import cz.muni.fi.persistanceEnums.ComponentType;
import cz.muni.fi.service.CarService;
import cz.muni.fi.service.ComponentService;
import cz.muni.fi.service.DriverService;
import cz.muni.fi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {
    @Autowired
    private ComponentService componentService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private CarService carService;

    @Autowired
    private TeamService teamService;

    private Map<String, cz.muni.fi.entities.Component> components = new HashMap<>();
    private Map<String, Car> cars = new HashMap<>();
    private Map<String, Driver> drivers = new HashMap<>();

    @Override
    public void loadData() throws IOException {
        loadComponents();
        loadDrivers();
        loadCars();
        loadTeams();
    }

    private void component(String name, ComponentType type){
        cz.muni.fi.entities.Component newComponent = new cz.muni.fi.entities.Component(name, true, type);
        componentService.createComponent(newComponent);
        components.put(newComponent.getName(), newComponent);
    }

    private void car(
            String name,
            Driver driver,
            cz.muni.fi.entities.Component engine,
            cz.muni.fi.entities.Component aerodynamics,
            cz.muni.fi.entities.Component suspension,
            cz.muni.fi.entities.Component transmission,
            cz.muni.fi.entities.Component brakes
    ) {
        Car newCar = new Car(driver, engine, aerodynamics, suspension, transmission, brakes);
        carService.createCar(newCar);
        cars.put(name, newCar);
    }

    private void driver(String name, String surname, String nationality) {
        Driver newDriver = new Driver(name, surname, nationality);
        driverService.addDriver(newDriver);
        drivers.put(surname, newDriver);
    }

    private void loadCars() {
        car(
                "redCar",
                drivers.get("Doe"),
                components.get("redEngine"),
                components.get("redAerodynamics"),
                components.get("redSuspension"),
                components.get("redTransmission"),
                components.get("redBrakes")
        );
        car(
                "blueCar",
                drivers.get("Bond"),
                components.get("blueEngine"),
                components.get("blueAerodynamics"),
                components.get("blueSuspension"),
                components.get("blueTransmission"),
                components.get("blueBrakes")
        );
    }

    private void loadTeams() {
        Team newTeam = new Team("newTeam", cars.get("redCar"), cars.get("blueCar"));
        teamService.createTeam(newTeam);
    }

    private void loadDrivers() {
        driver("John", "Doe", "us");
        driver("James", "Bond", "uk");
    }

    private void loadComponents() {
        component("redBrakes", ComponentType.BRAKES);
        component("redEngine", ComponentType.ENGINE);
        component("redTransmission", ComponentType.TRANSMISSION);
        component("redAerodynamics", ComponentType.AERODYNAMICS);
        component("redSuspension", ComponentType.SUSPENSION);
        component("blueBrakes", ComponentType.BRAKES);
        component("blueEngine", ComponentType.ENGINE);
        component("blueTransmission", ComponentType.TRANSMISSION);
        component("blueAerodynamics", ComponentType.AERODYNAMICS);
        component("blueSuspension", ComponentType.SUSPENSION);
    }
}