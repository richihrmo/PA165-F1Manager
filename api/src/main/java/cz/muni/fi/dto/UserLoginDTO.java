package cz.muni.fi.dto;

import lombok.Setter;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Rober Tamas
 */
public class UserLoginDTO {
    @NotNull
    @Size(min = 3, max = 150)
    @Getter @Setter private String email;
    @NotNull
    @Size(min = 5, max = 150)
    @Getter @Setter private String password;

    public UserLoginDTO() {}

    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
