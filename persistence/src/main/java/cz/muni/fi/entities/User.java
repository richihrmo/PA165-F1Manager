package cz.muni.fi.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Robert Tamas
 */
@Entity(name = "Users")
@EqualsAndHashCode(exclude={"id"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private String passwordHash;

    @NotNull
    @Column(nullable = false, unique = true)
    @Pattern(regexp = ".+@.+\\....?")
    @Getter @Setter private String email;

    @Getter @Setter private Boolean admin = false;
    @NotNull
    @Getter @Setter private String name;

    public User() {}

    public User(@NotNull String email, @NotNull String name) {
        this.email = email;
        this.name = name;
    }
}
