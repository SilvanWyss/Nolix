//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link BiggerArgumentException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument is undesirably bigger than a
 * certain max.
 * 
 * @author Silvan Wyss
 * @date 2016-03-01
 */
@SuppressWarnings("serial")
public final class BiggerArgumentException extends InvalidArgumentException {

  //constructor
  /**
   * Creates a new {@link BiggerArgumentException} for the given argumentName,
   * argument and max.
   * 
   * @param argumentName
   * @param argument
   * @param max
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private BiggerArgumentException(final String argumentName, final double argument, final double max) {

    //Calls constructor of the base class.
    super(argumentName, argument, "is bigger than " + max);
  }

  //constructor
  /**
   * Creates a new {@link BiggerArgumentException} for the given argumentName,
   * argument and max.
   * 
   * @param argumentName
   * @param argument
   * @param max
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private BiggerArgumentException(final String argumentName, final long argument, final long max) {

    //Calls constructor of the base class.
    super(argumentName, argument, "is bigger than " + max);
  }

  //static method
  /**
   * @param argumentName
   * @param argument
   * @param max
   * @return a new {@link BiggerArgumentException} for the given argumentName,
   *         argument and max.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static BiggerArgumentException forArgumentNameAndArgumentAndMax(
      final String argumentName,
      final double argument,
      final double max) {
    return new BiggerArgumentException(argumentName, argument, max);
  }

  //static method
  /**
   * @param argumentName
   * @param argument
   * @param max
   * @return a new {@link BiggerArgumentException} for the given argumentName,
   *         argument and max.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static BiggerArgumentException forArgumentNameAndArgumentAndMax(
      final String argumentName,
      final long argument,
      final long max) {
    return new BiggerArgumentException(argumentName, argument, max);
  }
}
