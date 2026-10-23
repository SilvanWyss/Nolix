/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

/**
 * @author Silvan Wyss
 * @param <T> the type a {@link NamableTypeMediator} is for.
 */
public final class NamableTypeMediator<T> extends AbstractTypeMediator<T> {
  private NamableTypeMediator(final Class<T> argument) {
    super(argument);
  }

  /**
   * @param argument
   * @param <T2>     the type the given argument represents
   * @return a new {@link NamableTypeMediator} for the given argument
   */
  public static <T2> NamableTypeMediator<T2> forArgument(final Class<T2> argument) {
    return new NamableTypeMediator<>(argument);
  }

  /**
   * @param arguemtName
   * @return a new {@link TypeMediator} for the argument of the current
   *         {@link NamableTypeMediator} with the given arguemtName
   */
  public TypeMediator<T> thatIsNamed(final String arguemtName) {
    return TypeMediator.forArgumentAndArgumentName(getStoredArgument(), arguemtName);
  }
}
