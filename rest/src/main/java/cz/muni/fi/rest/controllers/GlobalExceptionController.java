package cz.muni.fi.rest.controllers;

import cz.muni.fi.rest.ApiError;
import cz.muni.fi.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.rest.exceptions.ResourceNotFoundException;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lucie Kureckova, 445264
 */
@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    ApiError handleException(ResourceAlreadyExistingException ex) {
        ApiError apiError = new ApiError();
        apiError.setErrors(Arrays.asList("the requested resource already exists"));
        return apiError;
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ApiError handleException(ResourceNotFoundException ex) {
        ApiError apiError = new ApiError();
        apiError.setErrors(Arrays.asList("the requested resource not found"));
        return apiError;
    }
}
