package cz.muni.fi.entities;

import cz.muni.fi.enums.ComponentType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Lucie Kureckova, 445264
 */
@EqualsAndHashCode
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter private Long id;
    
    @OneToOne
    @Setter @Getter @NotNull private Driver driver;
    
    @OneToOne
    @Getter @NotNull private Component engine;
    
    @OneToOne
    @Getter @NotNull private Component aerodynamics;
    
    @OneToOne
    @Getter @NotNull private Component suspension;
    
    @OneToOne
    @Getter @NotNull private Component transmission;
    
    @OneToOne
    @Getter @NotNull private Component brakes;

    public Car(Driver driver, Component engine, Component aerodynamics, Component suspension, Component transmission, Component brakes) {
        this.driver = driver;
        setEngine(engine);
        setAerodynamics(aerodynamics);
        setSuspension(suspension);
        setTransmission(transmission);
        setBrakes(brakes);
    }
    
    public Car(){}
    
    public void setEngine(Component engine) {
        if(engine.getComponentType() != ComponentType.ENGINE){
            throw new IllegalArgumentException("type of component is not ENGINE");
        }
        this.engine = engine;
    }

    public void setAerodynamics(Component aerodynamics) {
        if(aerodynamics.getComponentType() != ComponentType.AERODYNAMICS){
            throw new IllegalArgumentException("type of component is not AERODYNAMICS");
        }
        this.aerodynamics = aerodynamics;
    }

    public void setSuspension(Component suspension) {
        if(suspension.getComponentType() != ComponentType.SUSPENSION){
            throw new IllegalArgumentException("type of component is not SUSPENSION");
        }
        this.suspension = suspension;
    }

    public void setTransmission(Component transmission) {
        if(transmission.getComponentType() != ComponentType.TRANSMISSION){
            throw new IllegalArgumentException("type of component is not TRANSMISSION");
        }
        this.transmission = transmission;
    }

    public void setBrakes(Component brakes) {
        if(brakes.getComponentType() != ComponentType.BRAKES){
            throw new IllegalArgumentException("type of component is not BRAKES");
        }
        this.brakes = brakes;
    }
}
