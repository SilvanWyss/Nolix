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

  public static NamableMethodMediator forArgument(final Method argument) {
    return new NamableMethodMediator(argument);
  }

  public MethodMediator thatIsNamed(final String argumentName) {
    return MethodMediator.forArgumentAndArgumentName(getStoredArgument(), argumentName);
  }
}
