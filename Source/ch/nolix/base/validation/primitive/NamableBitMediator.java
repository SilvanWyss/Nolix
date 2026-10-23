/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.primitive;

/**
 * @author Silvan Wyss
 */
public final class NamableBitMediator extends AbstractBitMediator {
  private final boolean argument;

  private NamableBitMediator(boolean argument) {
    super(argument);

    this.argument = argument;
  }

  /**
   * @param argument
   * @return a new {@link NamableBitMediator} for the given argument
   */
  public static NamableBitMediator forArgument(final boolean argument) {
    return new NamableBitMediator(argument);
  }

  /**
   * @param argumentName
   * @return a new {@link BitMediator} for the argument of the current
   *         {@link NamableBitMediator} with the given argumentName
   */
  public BitMediator thatIsNamed(final String argumentName) {
    return BitMediator.forArgumentAndArgumentName(argument, argumentName);
  }
}
