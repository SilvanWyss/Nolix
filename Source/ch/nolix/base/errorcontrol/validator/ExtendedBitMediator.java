/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.errorcontrol.validator;

/**
 * @author Silvan Wyss
 */
public final class ExtendedBitMediator extends BitMediator {
  private final boolean argument;

  ExtendedBitMediator(boolean argument) {
    super(argument);

    this.argument = argument;
  }

  public BitMediator thatIsNamed(final String argumentName) {
    return new BitMediator(argumentName, argument);
  }
}
