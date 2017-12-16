package cz.muni.fi.enums;

import lombok.Getter;

/**
 * @author Lucie Kureckova, 445264
 */
public enum DrivingSkill {
    DRIVING_ON_WET("DRIVING_ON_WET", "Driving on wet"),
    POWER_SLIDING("POWER_SLIDING", "Power sliding"), 
    EXTREME_REFLEXES("EXTREME_REFLEXES", "Extreme reflexes");
    
    @Getter private String name;
    @Getter private String urlAnnotation;

    DrivingSkill(String name, String urlAnnotation) {
        this.name = name;
        this.urlAnnotation = urlAnnotation;
    }

    public static DrivingSkill parse(String annotation) {
        for (DrivingSkill skill : values()) {
            if (skill.getUrlAnnotation().equals(annotation)) return skill;
        }
        return null;
    }
}