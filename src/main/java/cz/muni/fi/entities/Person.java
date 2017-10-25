package cz.muni.fi.entities;

import cz.muni.fi.enums.EDrivingSkill;

import java.util.EnumSet;
import java.util.Set;

import lombok.Getter;

/**
 * Created by Robert on 25/10/2017.
 */
public class Person {

    @Getter private String name;
    @Getter private String surname;
    @Getter private String nationality;
    @Getter private Set<EDrivingSkill> drivingSkills = EnumSet.allOf(EDrivingSkill.class);
    @Getter boolean isMainDriver = false;

    public Person(String name, String surname, String nationality) {
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
    }

    public void setDrivingSkills(EDrivingSkill drivingSkill, int skillLevel) {
        for (EDrivingSkill ds : drivingSkills) {
            if (ds.equals(drivingSkill)) {
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
