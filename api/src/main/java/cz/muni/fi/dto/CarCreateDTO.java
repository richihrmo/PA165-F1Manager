package cz.muni.fi.dto;

import cz.muni.fi.enums.ComponentType;
import cz.muni.fi.exceptions.WrongComponentException;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof CarCreateDTO)) return false;
        CarCreateDTO that = (CarCreateDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(driverId, that.driverId) &&
                Objects.equals(engineId, that.engineId) &&
                Objects.equals(aerodynamicsId, that.aerodynamicsId) &&
                Objects.equals(suspensionId, that.suspensionId) &&
                Objects.equals(transmissionId, that.transmissionId) &&
                Objects.equals(brakesId, that.brakesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, driverId, engineId, aerodynamicsId, suspensionId, transmissionId, brakesId);
    }
}
