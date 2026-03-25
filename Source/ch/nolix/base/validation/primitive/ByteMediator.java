/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.primitive;

/**
 * @author Silvan Wyss
 */
public final class ByteMediator extends AbstractByteMediator {
  private ByteMediator(final byte argument) {
    super(argument);
  }

  private ByteMediator(final byte argument, final String argumentName) {
    super(argument, argumentName);
  }

  public static ByteMediator forArgument(final byte argument) {
    return new ByteMediator(argument);
  }

  public static ByteMediator forArgumentAndArgumentName(final byte argument, final String argumentName) {
    return new ByteMediator(argument, argumentName);
  }
}
