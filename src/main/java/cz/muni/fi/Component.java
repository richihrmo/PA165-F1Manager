package cz.muni.fi;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;
import java.util.EnumSet;

/**
 * @author Richard Hrmo
 */
@EqualsAndHashCode
@Entity
public class Component{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @NonNull private Long id;

    @Getter @Setter @NonNull private String name;

    @Getter @Setter private boolean isAvailable = false;

    @Enumerated
    @Getter @NonNull private ComponentType componentType;

    public Component(){}

    public Component(String name, boolean isAvailable, ComponentType componentType){
        this.name = name;
        this.isAvailable = isAvailable;
        this.componentType = componentType;
    }

    public void setComponentType(ComponentType componentType) {
        if (EnumSet.allOf(ComponentType.class).contains(componentType)){
            throw new IllegalArgumentException("component type not supported");
        }
        this.componentType = componentType;
    }
}
