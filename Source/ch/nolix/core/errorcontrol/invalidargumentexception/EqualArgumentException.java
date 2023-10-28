//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link EqualArgumentException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument equals undesirably a certain
 * value.
 * 
 * @author Silvan Wyss
 * @date 2017-10-14
 */
@SuppressWarnings("serial")
public final class EqualArgumentException extends InvalidArgumentException {

  //constructor
  /**
   * Creates a new {@link EqualArgumentException} for the given argument and
   * equalValue.
   * 
   * @param argument
   * @param equalValue
   */
  private EqualArgumentException(final Object argument, final Object equalValue) {

    //Calls constructor of the base class.
    super(argument, "equals " + equalValue);
  }

  //constructor
  /**
   * Creates a new {@link EqualArgumentException} for the given argumentName,
   * argument and equalValue.
   * 
   * @param argumentName
   * @param argument
   * @param equalValue
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private EqualArgumentException(final String argumentName, final double argument, final double equalValue) {

    //Calls constructor of the base class.
    super(argumentName, argument, "equals " + equalValue);
  }

  //constructor
  /**
   * Creates a new {@link EqualArgumentException} for the given argumentName,
   * argument and equalValue.
   * 
   * @param argumentName
   * @param argument
   * @param equalValue
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private EqualArgumentException(final String argumentName, final long argument, final long equalValue) {

    //Calls constructor of the base class.
    super(argumentName, argument, "equals " + equalValue);
  }

  //static method
  /**
   * @param argumentName
   * @param argument
   * @param equalValue
   * @return a new {@link EqualArgumentException} for the given argument and
   *         equalValue.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static EqualArgumentException forArgumentNameAndArgumentAndEqualValue(
      final String argumentName,
      final double argument,
      final double equalValue) {

    //Calls constructor of the base class.
    return new EqualArgumentException(argumentName, argument, equalValue);
  }

  //static method
  /**
   * @param argumentName
   * @param argument
   * @param equalValue
   * @return a new {@link EqualArgumentException} for the given argument and
   *         equalValue.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static EqualArgumentException forArgumentNameAndArgumentAndEqualValue(
      final String argumentName,
      final long argument,
      final long equalValue) {

    //Calls constructor of the base class.
    return new EqualArgumentException(argumentName, argument, equalValue);
  }

  //static method
  /**
   * @param argument
   * @param equalValue
   * @return a new {@link EqualArgumentException} for the given argument and
   *         equalValue.
   */
  public static EqualArgumentException forArgumentAndEqualValue(final Object argument, final Object equalValue) {

    //Calls constructor of the base class.
    return new EqualArgumentException(argument, equalValue);
  }
}
