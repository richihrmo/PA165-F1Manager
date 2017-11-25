package cz.muni.fi.dto;

import cz.muni.fi.enums.DrivingSkill;
import java.util.Objects;
<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;
=======
>>>>>>> 4529a2f05a1aa044f1af86f5d47ecfaf548685b8

/**
 * @author Lucie Kureckova, 445264
 */
public class DriverDTO {
    
    @Getter @Setter private Long id;

    @Getter @Setter private String name;
    
    @Getter @Setter private String surname;
    
    @Getter @Setter private String nationality;
    
    @Getter @Setter private boolean isMainDriver;
    
    @Getter @Setter private DrivingSkill specialSkill;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.surname);
        hash = 97 * hash + Objects.hashCode(this.nationality);
        hash = 97 * hash + (this.isMainDriver ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.specialSkill);
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
        final DriverDTO other = (DriverDTO) obj;
        if (this.isMainDriver != other.isMainDriver) {
            return false;
        }
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
