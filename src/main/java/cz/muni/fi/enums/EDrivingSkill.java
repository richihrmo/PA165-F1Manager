package cz.muni.fi.enums;

import lombok.Getter;

/**
 * @author Robert Tamas
 */
public enum EDrivingSkill {

    DRIVING_ON_WET("Driving on wet asphalt"),
    POWER_SLIDING("Power sliding"),
    REFLEXES("Reflexes")
    ;

    private String skill;
    @Getter private int skillLevel = 0;

    EDrivingSkill(String skill) {
        this.skill = skill;
    }

    public void setSkillLevel(int skillLevel) {
        if (skillLevel < 0) {
            this.skillLevel = 0;
        } else if (skillLevel <= 10) {
            this.skillLevel = skillLevel;
        } else {
            this.skillLevel = 10;
        }
    }
}
