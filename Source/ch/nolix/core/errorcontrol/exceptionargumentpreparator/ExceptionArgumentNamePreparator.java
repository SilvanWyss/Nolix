package ch.nolix.core.errorcontrol.exceptionargumentpreparator;

import ch.nolix.coreapi.errorcontrol.exceptionargumentpreparator.IExceptionArgumentNamePreparator;

/**
 * @author Silvan Wyss
 * @version 2025-04-04
 */
public final class ExceptionArgumentNamePreparator implements IExceptionArgumentNamePreparator {

  private static final String DEFAULT_ARGUMENT_NAME = "argument";

  /**
   * {@inheritDoc}
   */
  @Override
  public String getNameOfArgument(final Object argument) {

    if (argument != null) {
      return getNameOfArgumentThatIsInstanceOfClass(argument.getClass());
    }

    return DEFAULT_ARGUMENT_NAME;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getValidatedArgumentNameFromArgumentName(final String argumentName) {

    if (argumentName == null) {
      throw new IllegalArgumentException("The given argument name is null.");
    }

    if (argumentName.isBlank()) {
      throw new IllegalArgumentException("The given argument name is blank.");
    }

    return argumentName;
  }

  /**
   * @param paramClass
   * @return a name of an argument that is an instance of the given paramClass.
   * @throws IllegalArgumentException if the given paramClass is null.
   */
  private static String getNameOfArgumentThatIsInstanceOfClass(final Class<?> paramClass) {

    if (paramClass == null) {
      throw new IllegalArgumentException("The given class is null.");
    }

    final var argumentName = paramClass.getSimpleName();

    if (argumentName != null && !argumentName.isEmpty()) {
      return argumentName;
    }

    return DEFAULT_ARGUMENT_NAME;
  }
}
