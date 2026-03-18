/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.primitivetype;

/**
 * @author Silvan Wyss
 */
public final class ExtendedBitMediator extends BitMediator {
  private final boolean argument;

  public ExtendedBitMediator(boolean argument) {
    super(argument);

    this.argument = argument;
  }

  public BitMediator thatIsNamed(final String argumentName) {
    return new BitMediator(argumentName, argument);
  }
}
