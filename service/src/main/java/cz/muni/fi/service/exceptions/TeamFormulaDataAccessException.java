package cz.muni.fi.service.exceptions;

import org.springframework.dao.DataAccessException;

public class TeamFormulaDataAccessException extends DataAccessException{
    public TeamFormulaDataAccessException(String msg) {
        super(msg);
    }

    public TeamFormulaDataAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }

}