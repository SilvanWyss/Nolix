package ch.nolix.core.errorcontrol.exceptionargumentpreparator;

import ch.nolix.coreapi.errorcontrolapi.exceptionargumentpreparatorapi.IExceptionCausePreparator;

/**
 * @author Silvan Wyss
 * @version 2025-04-05
 */
public final class ExceptionCausePreparator implements IExceptionCausePreparator {

  /**
   * {@inheritDoc}
   */
  @Override
  public Throwable getValidatedCauseFromCause(final Throwable cause) {

    if (cause == null) {
      throw new IllegalArgumentException("The given cause is null.");
    }

    return cause;
  }
}
