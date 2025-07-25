package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ArgumentNameDto;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * A {@link InvalidPortException} is a {@link AbstractInvalidArgumentException}
 * that is supposed to be thrown when a given port is not valid.
 * 
 * @author Silvan Wyss
 * @version 2021-07-16
 */
@SuppressWarnings("serial")
public final class InvalidPortException extends AbstractInvalidArgumentException {

  private static final String ARGUMENT_NAME = LowerCaseVariableCatalog.PORT;

  /**
   * Creates a new {@link InvalidPortException} for the given port.
   * 
   * @param port
   */
  private InvalidPortException(final long port) {
    super(port, new ArgumentNameDto(ARGUMENT_NAME));
  }

  /**
   * @param port
   * @return a new {@link InvalidPortException} for the given port.
   */
  public static InvalidPortException forPort(final long port) {
    return new InvalidPortException(port);
  }
}
