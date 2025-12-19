package ch.nolix.coreapi.errorcontrol.exceptionargumentpreparator;

/**
 * A {@link IExceptionCausePreparator} provides methods to prepare causes for
 * {@link Exception}s.
 * 
 * @author Silvan Wyss
 */
public interface IExceptionCausePreparator {
  /**
   * @param cause
   * @return a validated cause from the given cause.
   * @throws RuntimeException if the given cause is null.
   */
  Throwable getValidatedCauseFromCause(final Throwable cause);
}
