package cz.muni.fi.dto;

import cz.muni.fi.enums.ComponentType;
import cz.muni.fi.exceptions.WrongComponentException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Richard Hrmo
 */
public class CarCreateDTO {

    @Getter @Setter private Long id;
    @Getter @Setter private Long driverId;

    @Getter @Setter private Long engineId;
    @Getter @Setter private Long aerodynamicsId;
    @Getter @Setter private Long suspensionId;
    @Getter @Setter private Long transmissionId;
    @Getter @Setter private Long brakesId;

    public CarCreateDTO(){}

    public CarCreateDTO(Long id, Long driverId, Long engineId, Long aerodynamicsId,
                        Long suspensionId, Long transmissionId, Long brakesId) {
        this.id = id;
        this.driverId = driverId;
        this.engineId = engineId;
        this.aerodynamicsId = aerodynamicsId;
        this.suspensionId = suspensionId;
        this.transmissionId = transmissionId;
        this.brakesId = brakesId;
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || !(o instanceof CarDTO)) return false;
//
//        CarCreateDTO carDTO = (CarCreateDTO) o;
//
//        if (!driver.equals(carDTO.driver)) return false;
//        if (!engine.equals(carDTO.engine)) return false;
//        if (!transmission.equals(carDTO.transmission)) return false;
//        if (!brakes.equals(carDTO.brakes)) return false;
//        if (!suspension.equals(carDTO.suspension)) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = getDriver().hashCode();
//        result = 31 * result + getEngine().hashCode();
//        result = 31 * result + getAerodynamics().hashCode();
//        result = 31 * result + getSuspension().hashCode();
//        result = 31 * result + getTransmission().hashCode();
//        result = 31 * result + getBrakes().hashCode();
//        return result;
//    }
}
