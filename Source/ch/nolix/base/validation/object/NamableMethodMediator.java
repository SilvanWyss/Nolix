/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

import java.lang.reflect.Method;

/**
 * @author Silvan Wyss
 */
public final class NamableMethodMediator extends AbstractMethodMediator {
  private NamableMethodMediator(final Method argument) {
    super(argument);
  }

  /**
   * @param argument
   * @return a new {@link NamableMethodMediator} for the given argument
   */
  public static NamableMethodMediator forArgument(final Method argument) {
    return new NamableMethodMediator(argument);
  }

  /**
   * @param argumentName
   * @return a new {@link MethodMediator} for the argument of the current
   *         {@link NamableMethodMediator} with the given argumentName
   */
  public MethodMediator thatIsNamed(final String argumentName) {
    return MethodMediator.forArgumentAndArgumentName(getStoredArgument(), argumentName);
  }
}
