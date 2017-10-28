package cz.muni.fi.entities;

import com.sun.istack.internal.NotNull;
import cz.muni.fi.enums.EDrivingSkill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author Robert Tamas
 */
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter @NotNull private String name;
    @Getter @Setter @NotNull private String surname;
    @Getter @Setter @NotNull private String nationality;
    @Getter boolean isMainDriver = false;

    @Enumerated
    @Getter private Set<EDrivingSkill> drivingSkills = EnumSet.allOf(EDrivingSkill.class);


    public Driver() {
    }

    public Driver(String name, String surname, String nationality) {
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
    }

    public void setDrivingSkills(EDrivingSkill drivingSkill, int skillLevel) {
        for (EDrivingSkill ds : drivingSkills) {
            if (ds == drivingSkill) {
                ds.setSkillLevel(skillLevel);
            }
        }
    }

    public void setAsMainDriver() {
        isMainDriver = true;
    }

    public void setAsTestDriver() {
        isMainDriver = false;
    }
}
