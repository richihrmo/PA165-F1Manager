package cz.muni.fi.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lucie Kureckova, 445264
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason="The resource already exists.")
public class ResourceAlreadyExistingException extends RuntimeException {
    
} 
