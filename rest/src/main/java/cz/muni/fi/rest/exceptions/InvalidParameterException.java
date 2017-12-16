package cz.muni.fi.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Richard Hrmo
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class InvalidParameterException extends RuntimeException {

}

