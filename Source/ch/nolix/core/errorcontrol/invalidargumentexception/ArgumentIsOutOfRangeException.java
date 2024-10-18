package ch.nolix.core.errorcontrol.invalidargumentexception;

/**
 * A {@link ArgumentIsOutOfRangeException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given argument is undesirably not in a
 * certain range.
 * 
 * @author Silvan Wyss
 * @version 2016-03-01
 */
@SuppressWarnings("serial")
public final class ArgumentIsOutOfRangeException extends InvalidArgumentException {

  /**
   * Creates a new {@link ArgumentIsOutOfRangeException} for the given argument
   * and range defined by the given min and max.
   * 
   * @param argument
   * @param min
   * @param max
   */
  private ArgumentIsOutOfRangeException(final double argument, final double min, final double max) {

    //Calls constructor of the base class.
    super(argument, "is not in [" + min + ", " + max + "]");
  }

  /**
   * Creates a new {@link ArgumentIsOutOfRangeException} for the given argument
   * and range defined by the given min and max.
   * 
   * @param argument
   * @param min
   * @param max
   */
  private ArgumentIsOutOfRangeException(final long argument, final long min, final long max) {

    //Calls constructor of the base class.
    super(argument, "is not in [" + min + ", " + max + "]");
  }

  /**
   * Creates a new {@link ArgumentIsOutOfRangeException} for the given
   * argumentName, argument and range defined by the given min and max.
   * 
   * @param argumentName
   * @param argument
   * @param min
   * @param max
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private ArgumentIsOutOfRangeException(
    final String argumentName,
    final double argument,
    final double min,
    final double max) {
    //Calls constructor of the base class.
    super(argumentName, argument, "is not in [" + min + ", " + max + "]");
  }

  /**
   * Creates a new {@link ArgumentIsOutOfRangeException} for the given
   * argumentName, argument and range defined by the given min and max.
   * 
   * @param argumentName
   * @param argument
   * @param min
   * @param max
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private ArgumentIsOutOfRangeException(
    final String argumentName,
    final long argument,
    final long min,
    final long max) {

    //Calls constructor of the base class.
    super(argumentName, argument, "is not in [" + min + ", " + max + "]");
  }

  /**
   * @param argument
   * @return a new {@link ArgumentIsOutOfRangeException} for the given argument
   *         and range defined by the given min and max.
   * @param min
   * @param max
   */
  public static ArgumentIsOutOfRangeException forArgumentAndRangeWithMinAndMax(
    final double argument,
    final double min,
    final double max) {
    return new ArgumentIsOutOfRangeException(argument, min, max);
  }

  /**
   * @param argument
   * @return a new {@link ArgumentIsOutOfRangeException} for the given argument
   *         and range defined by the given min and max.
   * @param min
   * @param max
   */
  public static ArgumentIsOutOfRangeException forArgumentAndRangeWithMinAndMax(
    final long argument,
    final long min,
    final long max) {
    return new ArgumentIsOutOfRangeException(argument, min, max);
  }

  /**
   * @param argumentName
   * @param argument
   * @return a new {@link ArgumentIsOutOfRangeException} for the given
   *         argumentName, argument and range defined by the given min and max.
   * @param min
   * @param max
   * @throws IllegalArgumentException if the given argument name is null.
   * @throws IllegalArgumentException if the given argument name is blank.
   */
  public static ArgumentIsOutOfRangeException forArgumentNameAndArgumentAndRangeWithMinAndMax(
    final String argumentName,
    final double argument,
    final double min,
    final double max) {
    return new ArgumentIsOutOfRangeException(argumentName, argument, min, max);
  }

  /**
   * @param argumentName
   * @param argument
   * @return a new {@link ArgumentIsOutOfRangeException} for the given
   *         argumentName, argument and range defined by the given min and max.
   * @param min
   * @param max
   * @throws IllegalArgumentException if the given argument name is null.
   * @throws IllegalArgumentException if the given argument name is blank.
   */
  public static ArgumentIsOutOfRangeException forArgumentNameAndArgumentAndRangeWithMinAndMax(
    final String argumentName,
    final long argument,
    final long min,
    final long max) {
    return new ArgumentIsOutOfRangeException(argumentName, argument, min, max);
  }
}
