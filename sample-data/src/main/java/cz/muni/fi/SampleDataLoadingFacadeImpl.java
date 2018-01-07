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
                "Ferrari #1",
                drivers.get("Schumacher"),
                components.get("carbonEngine"),
                components.get("carbonAerodynamics"),
                components.get("carbonSuspension"),
                components.get("carbonTransmission"),
                components.get("carbonBrakes")
        );
        car(
                "Ferrari #2",
                drivers.get("Vettel"),
                components.get("titanEngine"),
                components.get("titanAerodynamics"),
                components.get("titanSuspension"),
                components.get("titanTransmission"),
                components.get("titanBrakes")
        );
    }

    private void loadTeams() {
        Team newTeam = new Team("Ferrari", cars.get("Ferrari #1"), cars.get("Ferrari #2"));
        teamService.createTeam(newTeam);
    }

    private void loadDrivers() {
        driver("Lewis", "Hamilton", "GB", DrivingSkill.DRIVING_ON_WET);
        driver("James", "Hunt", "GB", DrivingSkill.POWER_SLIDING);
        driver("Michael", "Schumacher", "DE", DrivingSkill.EXTREME_REFLEXES);
        driver("Sebastian", "Vettel", "DE", DrivingSkill.DRIVING_ON_WET);
        driver("Daniel", "Ricciardo", "AU", DrivingSkill.POWER_SLIDING);
        driver("Niki", "Lauda", "AT", DrivingSkill.DRIVING_ON_WET);
        driver("Giuseppe", "Farina", "IT", DrivingSkill.EXTREME_REFLEXES);
    }

    private void loadComponents() {
        component("carbonBrakes", ComponentType.BRAKES);
        component("carbonEngine", ComponentType.ENGINE);
        component("carbonTransmission", ComponentType.TRANSMISSION);
        component("carbonAerodynamics", ComponentType.AERODYNAMICS);
        component("carbonSuspension", ComponentType.SUSPENSION);
        component("titanBrakes", ComponentType.BRAKES);
        component("titanEngine", ComponentType.ENGINE);
        component("titanTransmission", ComponentType.TRANSMISSION);
        component("titanAerodynamics", ComponentType.AERODYNAMICS);
        component("titanSuspension", ComponentType.SUSPENSION);
        component("V8", ComponentType.ENGINE);
        component("Carbon-Ceramic", ComponentType.BRAKES);
        component("8-speed", ComponentType.TRANSMISSION);
        component("Electro-Magnetic", ComponentType.SUSPENSION);
        component("GroundEffect", ComponentType.AERODYNAMICS);
        component("V8-Turbo", ComponentType.ENGINE);
        component("Carbon-Ceramic gen. II", ComponentType.BRAKES);
        component("8-speed dual-clutch", ComponentType.TRANSMISSION);
        component("Hydraulic", ComponentType.SUSPENSION);
        component("F-Duct", ComponentType.AERODYNAMICS);
        component("V12", ComponentType.ENGINE);
        component("Brake-by-wire", ComponentType.BRAKES);
        component("7-speed single clutch", ComponentType.TRANSMISSION);
        component("Push-rod", ComponentType.SUSPENSION);
        component("Double Diffuser", ComponentType.AERODYNAMICS);
        component("V10", ComponentType.ENGINE);
        component("Carbon-Ceramic gen. III", ComponentType.BRAKES);
        component("9-speed dual clutch", ComponentType.TRANSMISSION);
        component("Pull-rod", ComponentType.SUSPENSION);
        component("Shark fin", ComponentType.AERODYNAMICS);
    }

    private void loadUsers() {
        user("admin-password", "Admin", "admin@admin.com", true);
        user("user-password", "Basic user",  "user@user.com",  false);
    }
}