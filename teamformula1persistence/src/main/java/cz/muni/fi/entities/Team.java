package cz.muni.fi.entities;

import com.sun.istack.internal.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Matus Macko
 */
@Entity
@EqualsAndHashCode
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter @NotNull private String name;
    @Getter @Setter @NotNull @OneToOne private Car carOne;
    @Getter @Setter @NotNull @OneToOne private Car carTwo;

    public Team(String name, Car carOne, Car carTwo) {
        this.name = name;
        this.carOne = carOne;
        this.carTwo = carTwo;
    }

    public Team() {}
}
