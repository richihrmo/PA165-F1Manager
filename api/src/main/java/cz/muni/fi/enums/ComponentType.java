package cz.muni.fi.enums;

import lombok.Getter;

/**
 * @author Robert Tamas
 */
public enum ComponentType {
    ENGINE("ENGINE", "engine"),
    TRANSMISSION("TRANSMISSION", "transmission"),
    BRAKES("BRAKES", "brakes"),
    AERODYNAMICS("AERODYNAMICS", "aerodynamics"),
    SUSPENSION("SUSPENSION", "suspension");

    @Getter private String name;
    @Getter private String urlAnnotation;

    ComponentType(String name, String urlAnnotation) {
        this.name = name;
        this.urlAnnotation = urlAnnotation;
    }

    public static ComponentType parse(String annotation) {
        for (ComponentType type : values()) {
            if (type.getUrlAnnotation().equals(annotation)) return type;
        }
        return null;
    }
}
