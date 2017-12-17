package cz.muni.fi.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Robert Tamas
 */
public class UserDTO {

    @Getter @Setter private Long id;
    @Getter @Setter private String passwordHash;
    @Getter @Setter private String email;
    @Getter @Setter private String name;
    @Getter @Setter private Boolean admin;

    public UserDTO() {}

    public UserDTO(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
