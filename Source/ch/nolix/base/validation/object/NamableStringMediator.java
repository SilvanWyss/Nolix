/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

/**
 * A {@link NamableStringMediator} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class NamableStringMediator extends AbstractStringMediator {
  /**
   * Creates a new {@link NamableStringMediator} for the given argument.
   * 
   * @param argument
   */
  private NamableStringMediator(final String argument) {
    super(argument);
  }

  /**
   * @param argument
   * @return a new {@link NamableStringMediator} for the given argument
   */
  public static NamableStringMediator forArgument(final String argument) {
    return new NamableStringMediator(argument);
  }

  /**
   * @param argumentName
   * @return a new {@link StringMediator} for the argument of the current
   *         {@link StringMediator} and the given argumentName
   * @throws RuntimeException if the given argumentName is null or blank
   */
  public StringMediator thatIsNamed(final String argumentName) {
    return StringMediator.forArgumentAndArgumentName(getStoredArgument(), argumentName);
  }
}
