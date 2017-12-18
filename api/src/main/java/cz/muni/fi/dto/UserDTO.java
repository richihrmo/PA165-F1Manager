package cz.muni.fi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author Robert Tamas
 */
public class UserDTO {
    @Getter @Setter private Long id;
    @Getter @Setter private String passwordHash;
    @Getter @Setter private String email;
    @Getter @Setter private String name;
    @Getter @Setter private Boolean admin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(email, userDTO.email) &&
                Objects.equals(name, userDTO.name) &&
                Objects.equals(admin, userDTO.admin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email, name, admin);
    }
}
