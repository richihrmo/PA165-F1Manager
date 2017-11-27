package cz.muni.fi.entities;

import javax.validation.constraints.NotNull;
import cz.muni.fi.persistanceEnums.ComponentType;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;

/**
 * @author Richard Hrmo
 */
@Entity
@EqualsAndHashCode
public class Component{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter @NotNull private String name;

    @Getter @Setter private boolean availability = false;

    @Enumerated
    @Getter @NotNull private ComponentType componentType;

    public Component(){}

    public Component(String name, boolean isAvailable, ComponentType componentType){
        this.name = name;
        this.availability = isAvailable;
        this.componentType = componentType;
    }

    @Override
    public String toString() {
        return "Component{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", componentType=" + componentType +
                '}';
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }
}
