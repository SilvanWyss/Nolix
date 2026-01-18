/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.errorcontrol.validator;

import java.util.Optional;

/**
 * @author Silvan Wyss
 * @param <T> is the type of the element of the {@link Optional} of a
 *            {@link ExtendedOptionalMediator}.
 */
public final class ExtendedOptionalMediator<T> extends OptionalMediator<T> {
  private ExtendedOptionalMediator(
    final Optional<T> argument //NOSONAR: An Optional is the argument of an ExtendedOptionalMediator.
  ) {
    super(argument);
  }

  public static <T2> ExtendedOptionalMediator<T2> forArgument(
    final Optional<T2> argument //NOSONAR: An Optional is the argument of an ExtendedOptionalMediator.
  ) {
    return new ExtendedOptionalMediator<>(argument);
  }

  public OptionalMediator<T> thatIsNamed(final Class<?> type) {
    return OptionalMediator.forArgumentNameAndArgument(type.getSimpleName(), getStoredArgument());
  }

  public OptionalMediator<T> thatIsNamed(final String argumentName) {
    return OptionalMediator.forArgumentNameAndArgument(argumentName, getStoredArgument());
  }
}
