/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.primitive;

/**
 * An {@link NamableLongMediator} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class NamableLongMediator extends AbstractLongMediator {
  /**
   * Creates a new {@link NamableLongMediator} for the given argument.
   * 
   * @param argument
   */
  private NamableLongMediator(final long argument) {
    super(argument);
  }

  /**
   * @param argument
   * @return a new {@link NamableLongMediator} for the given argument.
   */
  public static NamableLongMediator forArgument(final long argument) {
    return new NamableLongMediator(argument);
  }

  /**
   * @param argumentName
   * @return a new {@link LongMediator} for the given argumentName and the
   *         argument of the current {@link NamableLongMediator}
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public LongMediator thatIsNamed(final String argumentName) {
    return LongMediator.forArgumentNameAndArgument(argumentName, getArgument());
  }
}
