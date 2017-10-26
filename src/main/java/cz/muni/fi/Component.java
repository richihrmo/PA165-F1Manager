
package cz.muni.fi;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Author: Richard Hrmo
 */
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter private String name;

    @Getter private boolean isAvailable = false;

    @Enumerated
    @Getter private ComponentType componentType;

    public Component(){}

    public Component(String name, boolean isAvailable, ComponentType componentType){
        this.name = name;
        this.isAvailable = isAvailable;
        this.componentType = componentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        if (!componentType.getDeclaringClass().isInstance(ComponentType.class)){
            throw new IllegalArgumentException("component type not supported");
        }
        this.componentType = componentType;
    }
}
