package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * A {@link InvalidPortException} is a {@link InvalidArgumentException} that is
 * supposed to be thrown when a given port is not valid.
 * 
 * @author Silvan Wyss
 * @version 2021-07-16
 */
@SuppressWarnings("serial")
public final class InvalidPortException extends InvalidArgumentException {

  private static final String ARGUMENT_NAME = LowerCaseVariableCatalog.PORT;

  private static final String ERROR_PREDICATE = "is not valid";

  private InvalidPortException(final long port) {

    //Calls constructor of the base class.
    super(ARGUMENT_NAME, port, ERROR_PREDICATE);
  }

  public static InvalidPortException forPort(final long port) {
    return new InvalidPortException(port);
  }
}
