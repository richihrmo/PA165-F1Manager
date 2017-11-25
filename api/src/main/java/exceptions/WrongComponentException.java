package exceptions;

/**
 * @author Richard Hrmo
 */
public class WrongComponentException extends IllegalArgumentException{
    public WrongComponentException(String s) {
        super(s);
    }
}
