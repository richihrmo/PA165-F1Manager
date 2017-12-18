package cz.muni.fi.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Richard Hrmo
 */
@ResponseStatus(value = HttpStatus.NOT_MODIFIED, reason="The requested resource was not modified")
public class ResourceNotModifiedException extends RuntimeException {

}