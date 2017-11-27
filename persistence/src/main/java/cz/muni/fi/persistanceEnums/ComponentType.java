package cz.muni.fi.persistanceEnums;

/**
 * @author Richard Hrmo
 */
public enum ComponentType{
    ENGINE(cz.muni.fi.enums.ComponentType.ENGINE),
    TRANSMISSION(cz.muni.fi.enums.ComponentType.TRANSMISSION),
    BRAKES(cz.muni.fi.enums.ComponentType.BRAKES),
    AERODYNAMICS(cz.muni.fi.enums.ComponentType.AERODYNAMICS),
    SUSPENSION(cz.muni.fi.enums.ComponentType.SUSPENSION);

    private cz.muni.fi.enums.ComponentType DTOType;

    ComponentType(cz.muni.fi.enums.ComponentType type) {
        this.DTOType = type;
    }

    public static ComponentType getDTOType(cz.muni.fi.enums.ComponentType type) {
        for (ComponentType componentType : values()) {
            if (componentType.DTOType == type) return componentType;
        }
        return null;
    }
}
