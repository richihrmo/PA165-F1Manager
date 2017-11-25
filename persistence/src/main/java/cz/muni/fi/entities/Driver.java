package cz.muni.fi.entities;

import cz.muni.fi.persistanceEnums.DrivingSkill;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Robert Tamas
 */
@Entity
@EqualsAndHashCode
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter @NotNull private String name;
    @Getter @Setter @NotNull private String surname;
    @Getter @Setter @NotNull private String nationality;
    @Getter private boolean isMainDriver = false;
    @Enumerated @Getter @Setter private DrivingSkill specialSkill;


    public Driver() {}

    public Driver(String name, String surname, String nationality) {
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
    }

    public void setAsMainDriver() {
        isMainDriver = Boolean.TRUE;
    }

    public void setAsTestDriver() {
        isMainDriver = Boolean.FALSE;
    }
}
