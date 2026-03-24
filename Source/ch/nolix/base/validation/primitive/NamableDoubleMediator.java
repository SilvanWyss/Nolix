/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.primitive;

/**
 * A {@link NamableDoubleMediator} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class NamableDoubleMediator extends AbstractDoubleMediator {
  /**
   * Creates a new {@link NamableDoubleMediator} for the given argument.
   * 
   * @param argument
   */
  private NamableDoubleMediator(final double argument) {
    super(argument);
  }

  /**
   * @param argument
   * @return a new {@link NamableDoubleMediator} for the given argument.
   */
  public static NamableDoubleMediator forArgument(final double argument) {
    return new NamableDoubleMediator(argument);
  }

  /**
   * @param argumentName
   * @return a new {@link NamableDoubleMediator} for the argument of the current
   *         {@link NamableDoubleMediator} and the given argumentName.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public DoubleMediator thatIsNamed(final String argumentName) {
    return DoubleMediator.forArgumentAndArgumentName(getArgument(), argumentName);
  }
}
