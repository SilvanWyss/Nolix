/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

/**
 * A {@link StringMediator} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class StringMediator extends AbstractStringMediator {
  private StringMediator(final String argument, final String argumentName) {
    super(argument, argumentName);
  }

  /**
   * @param argument
   * @param argumentName
   * @return a new {@link StringMediator} for the given argument with the given
   *         argumentName
   */
  public static StringMediator forArgumentAndArgumentName(final String argument, final String argumentName) {
    return new StringMediator(argument, argumentName);
  }
}
