package cz.muni.fi.entities;

/**
 * Class represents car
 * @author Lucie Kureckova, 445264
 */

@Entity
public class Car {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Person driver;
    @OneToOne
    private Component engine;
    @OneToOne
    private Component aerodynamics;
    @OneToOne
    private Component suspension;
    @OneToOne
    private Component transmission;
    @OneToOne
    private Component brakes;

    public Car(Person driver, Component engine, Component aerodynamics, Component transmission, Component brakes) {
        this.driver = driver;
        this.engine = engine;
        this.aerodynamics = aerodynamics;
        this.transmission = transmission;
        this.brakes = brakes;
    }

    public Person getDriver() {
        return driver;
    }

    public void setDriver(Person driver) {
        this.driver = driver;
    }

    public Component getEngine() {
        return engine;
    }

    public void setEngine(Component engine) {
        this.engine = engine;
    }

    public Component getAerodynamics() {
        return aerodynamics;
    }

    public void setAerodynamics(Component aerodynamics) {
        this.aerodynamics = aerodynamics;
    }

    public Component getSuspension() {
        return suspension;
    }

    public void setSuspension(Component suspension) {
        this.suspension = suspension;
    }

    public Component getTransmission() {
        return transmission;
    }

    public void setTransmission(Component transmission) {
        this.transmission = transmission;
    }

    public Component getBrakes() {
        return brakes;
    }

    public void setBrakes(Component brakes) {
        this.brakes = brakes;
    }
    
}
