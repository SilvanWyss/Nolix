/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.primitive;

/**
 * @author Silvan Wyss
 */
public final class NamableByteMediator extends AbstractByteMediator {
  private NamableByteMediator(final byte argument) {
    super(argument);
  }

  public static NamableByteMediator forArgument(final byte argument) {
    return new NamableByteMediator(argument);
  }

  public ByteMediator thatIsNamed(final String argumentName) {
    return ByteMediator.forArgumentAndArgumentName(getArgument(), argumentName);
  }
}
