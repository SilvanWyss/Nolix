package ch.nolix.coreapi.errorcontrolapi.exceptionargumentpreparatorapi;

/**
 * A {@link IExceptionArgumentNamePreparator} provides methods to prepare
 * argument names for {@link Exception}s.
 * 
 * @author Silvan Wyss
 * @version 2025-04-04
 */
public interface IExceptionArgumentNamePreparator {

  /**
   * @param argument - Can be null.
   * @return a name of the given argument.
   */
  String getNameOfArgument(Object argument);

  /**
   * @param argumentName
   * @return a validated argument name from the given argumentName.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  String getValidatedArgumentNameFromArgumentName(final String argumentName);
}
