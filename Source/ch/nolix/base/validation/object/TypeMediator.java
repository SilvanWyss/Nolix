/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

/**
 * @author Silvan Wyss
 * @param <T> the type a {@link TypeMediator} is for
 */
public final class TypeMediator<T> extends AbstractTypeMediator<T> {
  private TypeMediator(Class<T> argument, String argumentName) {
    super(argument, argumentName);
  }

  public static <U> TypeMediator<U> forArgumentAndArgumentName(final Class<U> argument, final String argumentName) {
    return new TypeMediator<>(argument, argumentName);
  }
}
