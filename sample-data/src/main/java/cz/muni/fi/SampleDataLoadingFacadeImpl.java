package cz.muni.fi;

import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.entities.Team;
import cz.muni.fi.entities.User;
import cz.muni.fi.persistanceEnums.ComponentType;
import cz.muni.fi.persistanceEnums.DrivingSkill;
import cz.muni.fi.service.CarService;
import cz.muni.fi.service.ComponentService;
import cz.muni.fi.service.DriverService;
import cz.muni.fi.service.TeamService;
import cz.muni.fi.service.UserService;
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

    @Autowired
    private UserService userService;

    private Map<String, cz.muni.fi.entities.Component> components = new HashMap<>();
    private Map<String, Car> cars = new HashMap<>();
    private Map<String, Driver> drivers = new HashMap<>();
    private Map<String, User> users = new HashMap<>();
    
    @Override
    public void loadData(){
        loadComponents();
        loadDrivers();
        loadCars();
        loadTeams();
        loadUsers();
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

    private void driver(String name, String surname, String nationality, DrivingSkill skill) {
        Driver newDriver = new Driver(name, surname, nationality);
        newDriver.setSpecialSkill(skill);
        driverService.addDriver(newDriver);
        drivers.put(surname, newDriver);
    }

    private void user(String password, String name, String email, boolean admin) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAdmin(admin);
        userService.addUser(user, password);
        users.put(name.toLowerCase(), user);
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
        driver("John", "Doe", "us", DrivingSkill.DRIVING_ON_WET);
        driver("James", "Bond", "uk", DrivingSkill.POWER_SLIDING);
        driver("Michael", "Schumacher", "Germany", DrivingSkill.EXTREME_REFLEXES);
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
        component("V8", ComponentType.ENGINE);
        component("Carbon-Ceramic", ComponentType.BRAKES);
        component("8-speed", ComponentType.TRANSMISSION);
        component("Electro-Magnetic", ComponentType.SUSPENSION);
        component("GroundEffect", ComponentType.AERODYNAMICS);
    }

    private void loadUsers() throws IOException
    {
        user("admin-password", "Admin", "admin@admin.com", true);
        user("user-password", "Basic user",  "user@user.com",  false);
    }
}