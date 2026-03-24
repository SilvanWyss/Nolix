/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.primitive;

/**
 * @author Silvan Wyss
 */
public final class BitMediator extends AbstractBitMediator {
  private BitMediator(final boolean argument, final String argumentName) {
    super(argument, argumentName);
  }

  public static BitMediator forArgumentAndArgumentName(final boolean argument, final String argumentName) {
    return new BitMediator(argument, argumentName);
  }
}
