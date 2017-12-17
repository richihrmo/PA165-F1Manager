package cz.muni.fi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Robert Tamas
 */
public class UserCreateDTO {

    @NotNull
    @Size(min = 5, max = 150)
    @Getter @Setter private String password;
    @NotNull
    @Size(min = 3, max = 150)
    @Getter @Setter private String email;
    @NotNull
    @Size(min = 3, max = 50)
    @Getter @Setter String name;
    @NotNull
    @Getter @Setter Boolean admin = false;
}
