package ch.nolix.core.errorcontrol.validator;

import java.lang.reflect.Method;

public final class ExtendedMethodMediator extends MethodMediator {
  ExtendedMethodMediator(final Method argument) {
    super(argument);
  }

  public MethodMediator thatIsNamed(final String argumentName) {
    return new MethodMediator(argumentName, getStoredArgument());
  }
}
