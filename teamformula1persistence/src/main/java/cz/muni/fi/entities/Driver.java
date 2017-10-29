package cz.muni.fi.entities;

import cz.muni.fi.enums.DrivingSkill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Enumerated(EnumType.ORDINAL)
    @Getter private Set<DrivingSkill> drivingSkills = EnumSet.allOf(DrivingSkill.class);


    public Driver() {
    }

    public Driver(String name, String surname, String nationality) {
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
    }

    public void setDrivingSkills(DrivingSkill drivingSkill, int skillLevel) {
        for (DrivingSkill ds : drivingSkills) {
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
