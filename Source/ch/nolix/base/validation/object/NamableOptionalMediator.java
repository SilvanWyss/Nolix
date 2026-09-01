/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

import java.util.Optional;

/**
 * @author Silvan Wyss
 * @param <T> the type of the element of the {@link Optional} of a
 *            {@link NamableOptionalMediator}.
 */
public final class NamableOptionalMediator<T> extends AbstractOptionalMediator<T> {
  private NamableOptionalMediator(
    final Optional<T> argument // NOSONAR: An Optional is the argument of an ExtendedOptionalMediator.
  ) {
    super(argument);
  }

  public static <T2> NamableOptionalMediator<T2> forArgument(
    final Optional<T2> argument // NOSONAR: An Optional is the argument of an ExtendedOptionalMediator.
  ) {
    return new NamableOptionalMediator<>(argument);
  }

  public OptionalMediator<T> thatIsNamed(final Class<?> type) {
    final var argumentName = type.getSimpleName();

    return OptionalMediator.forArgumentAndArgumentName(getStoredArgument(), argumentName);
  }

  public OptionalMediator<T> thatIsNamed(final String argumentName) {
    return OptionalMediator.forArgumentAndArgumentName(getStoredArgument(), argumentName);
  }
}
