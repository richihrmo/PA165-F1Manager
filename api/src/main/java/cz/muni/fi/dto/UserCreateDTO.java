package cz.muni.fi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof UserCreateDTO)) return false;
        UserCreateDTO that = (UserCreateDTO) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(name, that.name) &&
                Objects.equals(admin, that.admin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email, name, admin);
    }
}
