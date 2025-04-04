package ch.nolix.coreapi.errorcontrolapi.exceptionargumentpreparatorapi;

/**
 * A {@link IExceptionArgumentNamePreparator} provides method to prepare
 * argument names for {@link Exception}s.
 * 
 * @author Silvan Wyss
 * @version 2025-04-04
 */
public interface IExceptionArgumentNamePreparator {

  /**
   * @param argumentName
   * @return a validated argument name from the given argumentName.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  String getValidatedArgumentNameOfArgumentName(final String argumentName);
}
