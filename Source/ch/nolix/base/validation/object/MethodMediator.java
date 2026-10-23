/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

import java.lang.reflect.Method;

/**
 * A {@link MethodMediator} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class MethodMediator extends AbstractMethodMediator {
  private MethodMediator(final Method argument, final String argumentName) {
    super(argument, argumentName);
  }

  /**
   * @param argument
   * @param argumentName
   * @return a new {@link MethodMediator} for the given argument with the given
   *         argumentName
   */
  public static MethodMediator forArgumentAndArgumentName(final Method argument, final String argumentName) {
    return new MethodMediator(argument, argumentName);
  }
}
