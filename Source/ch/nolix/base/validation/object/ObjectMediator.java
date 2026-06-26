/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

/**
 * A {@link ObjectMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @param <A> the type of the argument of an {@link ObjectMediator}.
 */
public final class ObjectMediator<A> extends AbstractObjectMediator<A> {
  /**
   * Creates a new {@link ObjectMediator} for the given argument and argumentName.
   * 
   * @param argument
   * @param argumentName
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  private ObjectMediator(final A argument, final String argumentName) {
    super(argument, argumentName);
  }

  /**
   * @param argument
   * @param argumentName
   * @param <T>          is the type of the given argument.
   * @return a new {@link ObjectMediator} for the given argument and argumentName.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public static <T> ObjectMediator<T> forArgumentAndArgumentName(final T argument, final String argumentName) {
    return new ObjectMediator<>(argument, argumentName);
  }
}
