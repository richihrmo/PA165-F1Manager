package cz.muni.fi.dto;

import cz.muni.fi.enums.ComponentType;
import cz.muni.fi.exceptions.WrongComponentException;
import cz.muni.fi.facade.CarFacade;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Richard Hrmo
 */
public class CarDTO {

    @Getter @Setter private Long id;

    @Getter @Setter private DriverDTO driver;

    @Getter private ComponentDTO engine;
    @Getter private ComponentDTO aerodynamics;
    @Getter private ComponentDTO suspension;
    @Getter private ComponentDTO transmission;
    @Getter private ComponentDTO brakes;

    public CarDTO(){}

    public CarDTO(DriverDTO driver, ComponentDTO engine, ComponentDTO aerodynamics,
                  ComponentDTO suspension, ComponentDTO transmission, ComponentDTO brakes){
        this.driver = driver;
        this.engine = engine;
        this.aerodynamics = aerodynamics;
        this.suspension = suspension;
        this.transmission = transmission;
        this.brakes = brakes;
    }

    public void setEngine(ComponentDTO engine) {
        if (engine.getType() != ComponentType.ENGINE){
            throw new WrongComponentException("type of component is not ENGINE");
        }
        this.engine = engine;
    }

    public void setAerodynamics(ComponentDTO aerodynamics) {
        if (aerodynamics.getType() != ComponentType.AERODYNAMICS){
            throw new WrongComponentException("type of component is not AERODYNAMICS");
        }
        this.aerodynamics = aerodynamics;
    }

    public void setSuspension(ComponentDTO suspension) {
        if (suspension.getType() != ComponentType.SUSPENSION){
            throw new WrongComponentException("type of component is not SUSPENSION");
        }
        this.suspension = suspension;
    }

    public void setTransmission(ComponentDTO transmission) {
        if (transmission.getType() != ComponentType.TRANSMISSION){
            throw new WrongComponentException("type of component is not TRANSMISSION");
        }
        this.transmission = transmission;
    }

    public void setBrakes(ComponentDTO brakes) {
        if (brakes.getType() != ComponentType.BRAKES){
            throw new WrongComponentException("type of component is not BRAKES");
        }
        this.brakes = brakes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof CarDTO)) return false;

        CarDTO carDTO = (CarDTO) o;

        if (!driver.equals(carDTO.driver)) return false;
        if (!engine.equals(carDTO.engine)) return false;
        if (!transmission.equals(carDTO.transmission)) return false;
        if (!brakes.equals(carDTO.brakes)) return false;
        if (!suspension.equals(carDTO.suspension)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getDriver().hashCode();
        result = 31 * result + getEngine().hashCode();
        result = 31 * result + getAerodynamics().hashCode();
        result = 31 * result + getSuspension().hashCode();
        result = 31 * result + getTransmission().hashCode();
        result = 31 * result + getBrakes().hashCode();
        return result;
    }
}
