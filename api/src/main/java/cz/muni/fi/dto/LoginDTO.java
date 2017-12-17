package cz.muni.fi.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Richard Hrmo
 */
public class LoginDTO {
    @Getter @NonNull @Setter private String login;
    @Getter @NonNull @Setter private String password;
}
