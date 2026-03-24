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

  public static NamableBitMediator forArgument(final boolean argument) {
    return new NamableBitMediator(argument);
  }

  public BitMediator thatIsNamed(final String argumentName) {
    return BitMediator.forArgumentAndArgumentName(argument, argumentName);
  }
}
