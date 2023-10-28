//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//own imports
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
/**
 * A {@link InvalidPortException} is a {@link InvalidArgumentException} that is
 * supposed to be thrown when a given port is not valid.
 * 
 * @author Silvan Wyss
 * @date 2021-07-16
 */
@SuppressWarnings("serial")
public final class InvalidPortException extends InvalidArgumentException {

  //constant
  private static final String ARGUMENT_NAME = LowerCaseCatalogue.PORT;

  //constant
  private static final String ERROR_PREDICATE = "is not valid";

  //constructor
  private InvalidPortException(final long port) {

    //Calls constructor of the base class.
    super(ARGUMENT_NAME, port, ERROR_PREDICATE);
  }

  //static method
  public static InvalidPortException forPort(final long port) {
    return new InvalidPortException(port);
  }
}
