/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

import java.util.Optional;

/**
 * @author Silvan Wyss
 * @param <T> the type of the element of the {@link Optional} of a
 *            {@link OptionalMediator}.
 */
public final class OptionalMediator<T> extends AbstractOptionalMediator<T> {
  private OptionalMediator(
    final Optional<T> argument, // NOSONAR: An Optional is the argument of a OptionalMediator.
    final String argumentName) {
    super(argument, argumentName);
  }

  public static <T2> OptionalMediator<T2> forArgumentAndArgumentName(
    final Optional<T2> argument, // NOSONAR: An Optional is the argument of a OptionalMediator.
    final String argumentName) {
    return new OptionalMediator<>(argument, argumentName);
  }
}
