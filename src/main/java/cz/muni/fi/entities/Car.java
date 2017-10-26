package cz.muni.fi.entities;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Class represents car
 * @author Lucie Kureckova, 445264
 */

@Entity
public class Car {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter private Long id;
    @OneToOne
    @Setter @Getter private Person driver;
    @OneToOne
    @Getter private Component engine;
    @OneToOne
    @Getter private Component aerodynamics;
    @OneToOne
    @Getter private Component suspension;
    @OneToOne
    @Getter private Component transmission;
    @OneToOne
    @Getter private Component brakes;
    @OneToMany
    @Setter @Getter List<Person> developers = new ArrayList<Person>();

    public Car(Person driver, Component engine, Component aerodynamics, Component suspension, Component transmission, Component brakes, List<Person> developers) {
        this.driver = driver;
        setEngine(engine);
        setAerodynamics(aerodynamics);
        setSuspension(suspension);
        setTransmission(transmission);
        setBrakes(brakes);
        this.developers = developers;
    }
    
    public Car(){}

    public void setEngine(Component engine) {
        if(engine.getType() != ComponentType.ENGINE){
            throw new IllegalArgumentException("type of component is not ENGINE");
        }
        this.engine = engine;
    }

    public void setAerodynamics(Component aerodynamics) {
        if(aerodynamics.getType() != ComponentType.AERODYNAMICS){
            throw new IllegalArgumentException("type of component is not AERODYNAMICS");
        }
        this.aerodynamics = aerodynamics;
    }

    public void setSuspension(Component suspension) {
        if(suspension.getType() != ComponentType.SUSPENSION){
            throw new IllegalArgumentException("type of component is not SUSPENSION");
        }
        this.suspension = suspension;
    }

    public void setTransmission(Component transmission) {
        if(transmission.getType() != ComponentType.TRANSMISSION){
            throw new IllegalArgumentException("type of component is not TRANSMISSION");
        }
        this.transmission = transmission;
    }

    public void setBrakes(Component brakes) {
        if(brakes.getType() != ComponentType.BRAKES){
            throw new IllegalArgumentException("type of component is not BRAKES");
        }
        this.brakes = brakes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 97 * hash + (this.driver != null ? this.driver.hashCode() : 0);
        hash = 97 * hash + (this.engine != null ? this.engine.hashCode() : 0);
        hash = 97 * hash + (this.aerodynamics != null ? this.aerodynamics.hashCode() : 0);
        hash = 97 * hash + (this.suspension != null ? this.suspension.hashCode() : 0);
        hash = 97 * hash + (this.transmission != null ? this.transmission.hashCode() : 0);
        hash = 97 * hash + (this.brakes != null ? this.brakes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Car)) {
            return false;
        }
        final Car other = (Car) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.driver != other.driver && (this.driver == null || !this.driver.equals(other.driver))) {
            return false;
        }
        if (this.engine != other.engine && (this.engine == null || !this.engine.equals(other.engine))) {
            return false;
        }
        if (this.aerodynamics != other.aerodynamics && (this.aerodynamics == null || !this.aerodynamics.equals(other.aerodynamics))) {
            return false;
        }
        if (this.suspension != other.suspension && (this.suspension == null || !this.suspension.equals(other.suspension))) {
            return false;
        }
        if (this.transmission != other.transmission && (this.transmission == null || !this.transmission.equals(other.transmission))) {
            return false;
        }
        if (this.brakes != other.brakes && (this.brakes == null || !this.brakes.equals(other.brakes))) {
            return false;
        }
        return true;
    }
 
}
