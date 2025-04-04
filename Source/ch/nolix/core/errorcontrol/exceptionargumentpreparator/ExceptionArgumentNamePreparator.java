package ch.nolix.core.errorcontrol.exceptionargumentpreparator;

import ch.nolix.coreapi.errorcontrolapi.exceptionargumentpreparatorapi.IExceptionArgumentNamePreparator;

/**
 * @author Silvan Wyss
 * @version 2025-04-04
 */
public final class ExceptionArgumentNamePreparator implements IExceptionArgumentNamePreparator {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getValidatedArgumentNameOfArgumentName(final String argumentName) {

    if (argumentName == null) {
      throw new IllegalArgumentException("The given argument name is null.");
    }

    if (argumentName.isBlank()) {
      throw new IllegalArgumentException("The given argument name is blank.");
    }

    return argumentName;
  }
}
