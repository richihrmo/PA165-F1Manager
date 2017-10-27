package cz.muni.fi.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Lucie Kureckova, 445264
 */

@EqualsAndHashCode
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter private Long id;
    
    @OneToOne
    @Setter @Getter private Driver driver;
    
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
    
    //@OneToMany 
    //Setter @Getter List<Driver> developers = new ArrayList<Person>(); ??

    public Car(Driver driver, Component engine, Component aerodynamics, Component suspension, Component transmission, Component brakes, /*List<Driver> developers*/) {
        this.driver = driver;
        setEngine(engine);
        setAerodynamics(aerodynamics);
        setSuspension(suspension);
        setTransmission(transmission);
        setBrakes(brakes);
        /*this.developers = developers;*/
    }
    
    public Car(){}
    
    public void setEngine(Component engine) {
        if(engine.getType() != EComponentType.ENGINE){
            throw new IllegalArgumentException("type of component is not ENGINE");
        }
        this.engine = engine;
    }

    public void setAerodynamics(Component aerodynamics) {
        if(aerodynamics.getType() != EComponentType.AERODYNAMICS){
            throw new IllegalArgumentException("type of component is not AERODYNAMICS");
        }
        this.aerodynamics = aerodynamics;
    }

    public void setSuspension(Component suspension) {
        if(suspension.getType() != EComponentType.SUSPENSION){
            throw new IllegalArgumentException("type of component is not SUSPENSION");
        }
        this.suspension = suspension;
    }

    public void setTransmission(Component transmission) {
        if(transmission.getType() != EComponentType.TRANSMISSION){
            throw new IllegalArgumentException("type of component is not TRANSMISSION");
        }
        this.transmission = transmission;
    }

    public void setBrakes(Component brakes) {
        if(brakes.getType() != EComponentType.BRAKES){
            throw new IllegalArgumentException("type of component is not BRAKES");
        }
        this.brakes = brakes;
    }
}
