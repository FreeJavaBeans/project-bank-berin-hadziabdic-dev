package bankingapp.Validation;

/**
 * This interface is used for an object to validate itself for invalid states.
 * I.e, If an object disocvers it is in an invalid state by invoking
 * selfvalidate it will throw an exception.
 */
public interface SelfValidator {

    public boolean SelfValidate() throws Exception;

}
