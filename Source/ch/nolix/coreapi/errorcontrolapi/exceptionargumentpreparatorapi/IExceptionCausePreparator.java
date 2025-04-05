package ch.nolix.coreapi.errorcontrolapi.exceptionargumentpreparatorapi;

/**
 * A {@link IExceptionCausePreparator} provides method to prepare causes for
 * {@link Exception}s.
 * 
 * @author Silvan Wyss
 * @version 2025-04-05
 */
public interface IExceptionCausePreparator {

  /**
   * @param cause
   * @return a validated cause from the given cause.
   * @throws RuntimeException if the given cause is null.
   */
  Throwable getValidatedCauseFromCause(final Throwable cause);
}
