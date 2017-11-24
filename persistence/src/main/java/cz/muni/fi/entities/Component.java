package cz.muni.fi.entities;

import javax.validation.constraints.NotNull;
import cz.muni.fi.enums.ComponentType;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;
import java.util.EnumSet;

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

    @Getter @Setter private boolean isAvailable = false;

    @Enumerated
    @Getter @NotNull private ComponentType componentType;

    public Component(){}

    public Component(String name, boolean isAvailable, ComponentType componentType){
        this.name = name;
        this.isAvailable = isAvailable;
        this.componentType = componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }
}
