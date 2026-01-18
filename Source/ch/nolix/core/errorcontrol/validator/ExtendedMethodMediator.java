/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.errorcontrol.validator;

import java.lang.reflect.Method;

/**
 * @author Silvan Wyss
 */
public final class ExtendedMethodMediator extends MethodMediator {
  private ExtendedMethodMediator(final Method argument) {
    super(argument);
  }

  public static ExtendedMethodMediator forArgument(final Method argument) {
    return new ExtendedMethodMediator(argument);
  }

  public MethodMediator thatIsNamed(final String argumentName) {
    return MethodMediator.forArgumentNameAndArgument(argumentName, getStoredArgument());
  }
}
