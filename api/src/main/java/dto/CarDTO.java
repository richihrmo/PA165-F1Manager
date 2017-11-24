package dto;

import java.util.Objects;

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
        this.engine = engine;
    }

    public ComponentDTO getAerodynamics() {
        return aerodynamics;
    }

    public void setAerodynamics(ComponentDTO aerodynamics) {
        this.aerodynamics = aerodynamics;
    }

    public ComponentDTO getSuspension() {
        return suspension;
    }

    public void setSuspension(ComponentDTO suspension) {
        this.suspension = suspension;
    }

    public ComponentDTO getTransmission() {
        return transmission;
    }

    public void setTransmission(ComponentDTO transmission) {
        this.transmission = transmission;
    }

    public ComponentDTO getBrakes() {
        return brakes;
    }

    public void setBrakes(ComponentDTO brakes) {
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
