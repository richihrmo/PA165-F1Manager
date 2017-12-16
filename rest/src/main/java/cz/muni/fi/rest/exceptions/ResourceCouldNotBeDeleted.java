package cz.muni.fi.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lucie Kureckova, 445264
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason="The resource could not be deleted.")
public class ResourceCouldNotBeDeleted extends RuntimeException {
    
} 
