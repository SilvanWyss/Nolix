package ch.nolix.core.errorcontrol.invalidargumentexception;

/**
 * A {@link ArgumentIsInRangeException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given value is undesirably in a certain
 * range.
 * 
 * @author Silvan Wyss
 * @version 2017-10-14
 */
@SuppressWarnings("serial")
public final class ArgumentIsInRangeException extends InvalidArgumentException {

  /**
   * Creates a new {@link ArgumentIsInRangeException} for the given argument and
   * range defined by the given min and max.
   * 
   * @param argument
   * @param min
   * @param max
   */
  private ArgumentIsInRangeException(final double argument, final double min, final double max) {

    //Calls constructor of the base class.
    super(argument, "is in [" + min + ", " + max + "]");
  }

  /**
   * Creates a new {@link ArgumentIsInRangeException} for the given argument and
   * range defined by the given min and max.
   * 
   * @param argument
   * @param min
   * @param max
   */
  private ArgumentIsInRangeException(final long argument, final long min, final long max) {

    //Calls constructor of the base class.
    super(argument, "is in [" + min + ", " + max + "]");
  }

  /**
   * Creates a new {@link ArgumentIsInRangeException} for the given argumentName,
   * argument and range defined by the given min and max.
   * 
   * @param argumentName
   * @param argument
   * @param min
   * @param max
   * @throws IllegalArgumentException if the given argument name is null.
   * @throws IllegalArgumentException if the given argument name is blank.
   */
  private ArgumentIsInRangeException(
    final String argumentName,
    final double argument,
    final double min,
    final double max) {

    //Calls constructor of the base class.
    super(argumentName, argument, "is in [" + min + ", " + max + "]");
  }

  /**
   * Creates a new {@link ArgumentIsInRangeException} for the given argumentName,
   * argument and range defined by the given min and max.
   * 
   * @param argumentName
   * @param argument
   * @param min
   * @param max
   * @throws IllegalArgumentException if the given argument name is null.
   * @throws IllegalArgumentException if the given argument name is blank.
   */
  private ArgumentIsInRangeException(
    final String argumentName,
    final long argument,
    final long min,
    final long max) {

    //Calls constructor of the base class.
    super(argumentName, argument, "is in [" + min + ", " + max + "]");
  }

  /**
   * @param argument
   * @return a new {@link ArgumentIsInRangeException} for the given argument and
   *         range defined by the given min and max.
   * @param min
   * @param max
   */
  public static ArgumentIsInRangeException forArgumentAndRangeWithMinAndMax(
    final double argument,
    final double min,
    final double max) {
    return new ArgumentIsInRangeException(argument, min, max);
  }

  /**
   * @param argument
   * @return a new {@link ArgumentIsInRangeException} for the given argument and
   *         range defined by the given min and max.
   * @param min
   * @param max
   */
  public static ArgumentIsInRangeException forArgumentAndRangeWithMinAndMax(
    final long argument,
    final long min,
    final long max) {
    return new ArgumentIsInRangeException(argument, min, max);
  }

  /**
   * @param argumentName
   * @param argument
   * @return a new {@link ArgumentIsInRangeException} for the given argumentName,
   *         argument and range defined by the given min and max.
   * @param min
   * @param max
   * @throws IllegalArgumentException if the given argument name is null.
   * @throws IllegalArgumentException if the given argument name is blank.
   */
  public static ArgumentIsInRangeException forArgumentNameAndArgumentAndRangeWithMinAndMax(
    final String argumentName,
    final double argument,
    final double min,
    final double max) {
    return new ArgumentIsInRangeException(argumentName, argument, min, max);
  }

  /**
   * @param argumentName
   * @param argument
   * @return a new {@link ArgumentIsInRangeException} for the given argumentName,
   *         argument and range defined by the given min and max.
   * @param min
   * @param max
   * @throws IllegalArgumentException if the given argument name is null.
   * @throws IllegalArgumentException if the given argument name is blank.
   */
  public static ArgumentIsInRangeException forArgumentNameAndArgumentAndRangeWithMinAndMax(
    final String argumentName,
    final long argument,
    final long min,
    final long max) {
    return new ArgumentIsInRangeException(argumentName, argument, min, max);
  }
}
