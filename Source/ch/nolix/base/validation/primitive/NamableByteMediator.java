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

  /**
   * @param argument
   * @return a new {@link NamableByteMediator} for the given argument
   */
  public static NamableByteMediator forArgument(final byte argument) {
    return new NamableByteMediator(argument);
  }

  /**
   * @param argumentName
   * @return a new {@link ByteMediator} for the argument of the current
   *         {@link NamableByteMediator} with the given argumentName
   */
  public ByteMediator thatIsNamed(final String argumentName) {
    return ByteMediator.forArgumentAndArgumentName(getArgument(), argumentName);
  }
}
