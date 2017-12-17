package cz.muni.fi.filter;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Robert Tamas
 */
public class ComponentFilter {

    @Getter @Setter private Boolean available = false;
    @Getter @Setter private ComponentFilterType type = ComponentFilterType.NONE;

    public enum ComponentFilterType {
        NONE("NONE", "none"),
        ENGINE("ENGINE", "engine"),
        TRANSMISSION("TRANSMISSION", "transmission"),
        BRAKES("BRAKES", "brakes"),
        AERODYNAMICS("AERODYNAMICS", "aerodynamics"),
        SUSPENSION("SUSPENSION", "suspension");

        @Getter private String name;
        @Getter private String urlAnnotation;

        ComponentFilterType(String name, String urlAnnotation) {
            this.name = name;
            this.urlAnnotation = urlAnnotation;
        }
    }
}
