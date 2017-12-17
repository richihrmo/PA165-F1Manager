package cz.muni.fi.mvc.filter;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Lucie Kureckova, 445264
 */
public class DrivingSkillFilter {
    
    @Getter @Setter private MainDrivingSkillFilter skill = MainDrivingSkillFilter.NONE;
    
    public enum MainDrivingSkillFilter{
        NONE("NONE", "none"),
        DRIVING_ON_WET("DRIVING_ON_WET", "Driving on wet"),
        POWER_SLIDING("POWER_SLIDING", "Power sliding"), 
        EXTREME_REFLEXES("EXTREME_REFLEXES", "Extreme reflexes");

        @Getter private String name;
        @Getter private String urlAnnotation;

        MainDrivingSkillFilter(String name, String urlAnnotation) {
            this.name = name;
            this.urlAnnotation = urlAnnotation;
        }
    }
}
