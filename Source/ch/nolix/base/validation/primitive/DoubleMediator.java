/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.primitive;

/**
 * A {@link DoubleMediator} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class DoubleMediator extends AbstractDoubleMediator {
  /**
   * Creates a new {@link DoubleMediator} for the given argument and argumentName.
   * 
   * @param argument
   * @param argumentName
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  private DoubleMediator(final double argument, final String argumentName) {
    super(argument, argumentName);
  }

  /**
   * 
   * @param argument
   * @param argumentName
   * @return a new {@link DoubleMediator} for the given argument and argumentName
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public static DoubleMediator forArgumentAndArgumentName(final double argument, final String argumentName) {
    return new DoubleMediator(argument, argumentName);
  }
}
