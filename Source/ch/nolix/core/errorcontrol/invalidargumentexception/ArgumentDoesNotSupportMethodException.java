//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link ArgumentDoesNotSupportMethodException} is a
 * {@link InvalidArgumentException} that is supposed to be thrown when a given
 * argument does undesirably not support an invoked method.
 * 
 * @author Silvan Wyss
 * @version 2019-02-03
 */
@SuppressWarnings("serial")
public final class ArgumentDoesNotSupportMethodException extends InvalidArgumentException {

  //constructor
  /**
   * Creates a new {@link ArgumentDoesNotSupportMethodException} for the given
   * argument and methodName.
   * 
   * @param argument
   * @param methodName
   * @throws IllegalArgumentException if the given methodName is null.
   * @throws IllegalArgumentException if the given methodName is blank.
   */
  private ArgumentDoesNotSupportMethodException(final Object argument, final String methodName) {

    //Calls constructor of the base class.
    super(argument, "does not support the " + getValidMethodNameOfMethodName(methodName) + " method");
  }

  //static method
  /**
   * @param argument
   * @param methodName
   * @return new {@link ArgumentDoesNotSupportMethodException} for the given
   *         argument and methodName.
   * @throws IllegalArgumentException if the given methodName is null.
   * @throws IllegalArgumentException if the given methodName is blank.
   */
  public static ArgumentDoesNotSupportMethodException forArgumentAndMethodName(
    final Object argument,
    final String methodName) {
    return new ArgumentDoesNotSupportMethodException(argument, methodName);
  }

  //static method
  /**
   * @param methodName
   * @return a valid method name of the given methodName.
   * @throws IllegalArgumentException if the given methodName is null.
   * @throws IllegalArgumentException if the given methodName is blank.
   */
  private static String getValidMethodNameOfMethodName(final String methodName) {

    //Asserts that the given methodName is not null.
    if (methodName == null) {
      throw new IllegalArgumentException("The given method name is null.");
    }

    //Asserts that the given methodName is not blank.
    if (methodName.isBlank()) {
      throw new IllegalArgumentException("The given method name is blank.");
    }

    return methodName;
  }
}
