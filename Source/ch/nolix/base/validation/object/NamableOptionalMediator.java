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

  /**
   * @param argument
   * @param <T2>     the type of the value of the given argument
   * @return a new {@link NamableOptionalMediator} for the given argument
   */
  public static <T2> NamableOptionalMediator<T2> forArgument(
    final Optional<T2> argument // NOSONAR: An Optional is the argument of an ExtendedOptionalMediator.
  ) {
    return new NamableOptionalMediator<>(argument);
  }

  /**
   * @param type
   * @return a new {@link OptionalMediator} for the argument of the current
   *         {@link NamableOptionalMediator} with the name of the given type
   */
  public OptionalMediator<T> thatIsNamed(final Class<?> type) {
    final var argumentName = type.getSimpleName();

    return OptionalMediator.forArgumentAndArgumentName(getStoredArgument(), argumentName);
  }

  /**
   * @param argumentName
   * @return a new {@link OptionalMediator} for the argument of the current
   *         {@link NamableOptionalMediator} with the given argumentName
   */
  public OptionalMediator<T> thatIsNamed(final String argumentName) {
    return OptionalMediator.forArgumentAndArgumentName(getStoredArgument(), argumentName);
  }
}
