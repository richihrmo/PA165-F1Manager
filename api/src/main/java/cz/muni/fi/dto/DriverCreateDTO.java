package cz.muni.fi.dto;

import cz.muni.fi.enums.DrivingSkill;
import java.util.Objects;

/**
 * @author Lucie Kureckova, 445264
 */
public class DriverCreateDTO {
    
    @Getter @Setter private String name;
    
    @Getter @Setter private String surname;
    
    @Getter @Setter private String nationality;
    
    @Getter @Setter private DrivingSkill specialSkill;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.surname);
        hash = 89 * hash + Objects.hashCode(this.nationality);
        hash = 89 * hash + Objects.hashCode(this.specialSkill);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DriverDTO)) {
            return false;
        }
        final DriverCreateDTO other = (DriverCreateDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.nationality, other.nationality)) {
            return false;
        }
        if (this.specialSkill != other.specialSkill) {
            return false;
        }
        return true;
    }
    
}
