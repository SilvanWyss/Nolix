//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//Java imports
import java.math.BigDecimal;

//class
/**
 * A {@link SmallerArgumentException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument is undesirably smaller than a
 * certain limit.
 * 
 * @author Silvan Wyss
 * @date 2016-03-01
 */
@SuppressWarnings("serial")
public final class SmallerArgumentException extends InvalidArgumentException {

  //constructor
  /**
   * Creates a new {@link SmallerArgumentException} for the given argumentName,
   * argument and limit.
   * 
   * @param argumentName
   * @param argument
   * @param limit
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   * @throws IllegalArgumentException if the given limit is null.
   */
  private SmallerArgumentException(final String argumentName, final BigDecimal argument, final BigDecimal limit) {

    //Calls constructor of the base class.
    super(argumentName, argument, "is smaller than " + getValidLimitOfLimit(limit));
  }

  //constructor
  /**
   * Creates a new {@link SmallerArgumentException} for the given argumentName,
   * argument and limit.
   * 
   * @param argumentName
   * @param argument
   * @param limit
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   * @throws IllegalArgumentException if the given limit is null.
   */
  private SmallerArgumentException(final String argumentName, final double argument, final double limit) {

    //Calls constructor of the base class.
    super(argumentName, argument, "is smaller than " + limit);
  }

  //constructor
  /**
   * @param argumentName
   * @param argument
   * @param limit
   * @return a new {@link SmallerArgumentException} for the given argumentName,
   *         argument and limit.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   * @throws IllegalArgumentException if the given limit is null.
   */
  public static SmallerArgumentException forArgumentNameAndArgumentAndLimit(
    final String argumentName,
    final BigDecimal argument,
    final BigDecimal limit) {
    return new SmallerArgumentException(argumentName, argument, limit);
  }

  //constructor
  /**
   * @param argumentName
   * @param argument
   * @param limit
   * @return a new {@link SmallerArgumentException} for the given argumentName,
   *         argument and limit.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   * @throws IllegalArgumentException if the given limit is null.
   */
  public static SmallerArgumentException forArgumentNameAndArgumentAndLimit(
    final String argumentName,
    final double argument,
    final double limit) {
    return new SmallerArgumentException(argumentName, argument, limit);
  }

  //static method
  /**
   * @param limit
   * @return a valid limit of the given limit.
   * @throws IllegalArgumentException if the given limit is null.
   */
  private static BigDecimal getValidLimitOfLimit(final BigDecimal limit) {

    if (limit == null) {
      throw new IllegalArgumentException("The given limit is null.");
    }

    return limit;
  }
}
