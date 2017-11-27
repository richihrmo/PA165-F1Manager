package cz.muni.fi.dto;

import cz.muni.fi.enums.ComponentType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Robert Tamas
 */
public class ComponentDTO {

    @Getter @Setter private Long id;
    @Getter @Setter private String name;
    @Getter @Setter private ComponentType type;
    @Getter @Setter private Boolean availability;

    public ComponentDTO () {}

    public ComponentDTO(String name, Boolean isAvailable, ComponentType type) {
        this.name = name;
        this.type = type;
        this.availability = isAvailable;
    }

    @Override
    public String toString() {
        return "ComponentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof ComponentDTO)) return false;

        ComponentDTO that = (ComponentDTO) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getType() == that.getType();
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }
}