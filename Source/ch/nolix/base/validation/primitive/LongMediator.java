/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.primitive;

/**
 * {@link LongMediator} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class LongMediator extends AbstractLongMediator {
  /**
   * Creates a new {@link LongMediator} for the given argument.
   * 
   * @param argument
   */
  private LongMediator(final long argument) {
    super(argument);
  }

  /**
   * Creates a new {@link LongMediator} for the given argumentName and argument.
   * 
   * @param argumentName
   * @param argument
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  private LongMediator(final String argumentName, final long argument) {
    super(argumentName, argument);
  }

  /**
   * @param argument
   * @return a new {@link LongMediator} for the given argument.
   */
  public static LongMediator forArgument(final long argument) {
    return new LongMediator(argument);
  }

  /**
   * @param argumentName
   * @param argument
   * @return a new {@link LongMediator} for the given argumentName and argument
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public static LongMediator forArgumentNameAndArgument(final String argumentName, final long argument) {
    return new LongMediator(argumentName, argument);
  }
}
