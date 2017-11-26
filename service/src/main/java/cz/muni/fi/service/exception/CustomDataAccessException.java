package cz.muni.fi.service.exception;

import org.springframework.dao.DataAccessException;

/**
 * Exception thrown by services when there is problem with access to lower level data.
 *
 * @author Richard Hrmo
 */
public class CustomDataAccessException extends DataAccessException {
    public CustomDataAccessException(String msg) {
        super(msg);
    }

    public CustomDataAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
