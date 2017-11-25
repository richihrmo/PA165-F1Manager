package dto;

import java.util.Objects;
import exceptions.WrongComponentException;

/**
 * Car DTO class for Car entity
 *
 * @author Richard Hrmo
 */
public class CarDTO {

    private Long id;

    private DriverDTO driver;

    private ComponentDTO engine;
    private ComponentDTO aerodynamics;
    private ComponentDTO suspension;
    private ComponentDTO transmission;
    private ComponentDTO brakes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DriverDTO getDriver() {
        return driver;
    }

    public void setDriver(DriverDTO driverDTO) {
        this.driver = driver;
    }

    public ComponentDTO getEngine() {
        return engine;
    }

    public void setEngine(ComponentDTO engine) {
        if(engine.getComponentType() != ComponentType.ENGINE){
            throw new WrongComponentException("type of component is not ENGINE");
        }
        this.engine = engine;
    }

    public ComponentDTO getAerodynamics() {
        return aerodynamics;
    }

    public void setAerodynamics(ComponentDTO aerodynamics) {
        if(aerodynamics.getComponentType() != ComponentType.AERODYNAMICS){
            throw new WrongComponentException("type of component is not AERODYNAMICS");
        }
        this.aerodynamics = aerodynamics;
    }

    public ComponentDTO getSuspension() {
        return suspension;
    }

    public void setSuspension(ComponentDTO suspension) {
        if(suspension.getComponentType() != ComponentType.SUSPENSION){
            throw new WrongComponentException("type of component is not SUSPENSION");
        }
        this.suspension = suspension;
    }

    public ComponentDTO getTransmission() {
        return transmission;
    }

    public void setTransmission(ComponentDTO transmission) {
        if(transmission.getComponentType() != ComponentType.TRANSMISSION){
            throw new WrongComponentException("type of component is not TRANSMISSION");
        }
        this.transmission = transmission;
    }

    public ComponentDTO getBrakes() {
        return brakes;
    }

    public void setBrakes(ComponentDTO brakes) {
        if(brakes.getComponentType() != ComponentType.BRAKES){
            throw new WrongComponentException("type of component is not BRAKES");
        }
        this.brakes = brakes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof CarDTO)) return false;

        CarDTO carDTO = (CarDTO) o;

        return Objects.equals(getId(), carDTO.id);
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getDriver().hashCode();
        return result;
    }
}
