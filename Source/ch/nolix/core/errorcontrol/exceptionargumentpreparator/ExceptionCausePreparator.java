/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.errorcontrol.exceptionargumentpreparator;

import ch.nolix.coreapi.errorcontrol.exceptionargumentpreparator.IExceptionCausePreparator;

/**
 * @author Silvan Wyss
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
