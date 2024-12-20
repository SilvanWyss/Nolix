package ch.nolix.core.math.basic;

import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.PluralLowerCaseVariableCatalogue;

public final class BasicCalculator {

  /**
   * The default maximum deviation is 10^-9.
   */
  public static final double DEFAULT_MAX_DEVIATION = 0.000000001;

  //For a better performance, this implementation does not use all comfortable methods.
  /**
   * @param value1
   * @param value2
   * @return true if the given values equals approximately each other with a
   *         deviation that is not bigger than {@value #DEFAULT_MAX_DEVIATION}.
   */
  public boolean equalsApproximatively(final double value1, final double value2) {
    return (Math.abs(value1 - value2) < DEFAULT_MAX_DEVIATION);
  }

  /**
   * @param value1
   * @param value2
   * @param maxDeviation
   * @return true if the given values equals approximately each other with a
   *         deviation that is not bigger than the given maxDeviation.
   * @throws NegativeArgumentException if the given maxDeviation is negative.
   */
  public boolean equalsApproximatively(final double value1, final double value2, final double maxDeviation) {

    //Asserts that the given maxDeviation is not negative.
    GlobalValidator.assertThat(maxDeviation).thatIsNamed("max deviation").isNotNegative();

    return (Math.abs(value1 - value2) <= maxDeviation);
  }

  /**
   * @param value1
   * @param value2
   * @return the absolute difference between the given 2 values.
   */
  public double getAbsoluteDifference(final double value1, final double value2) {
    return getAbsoluteValue(value2 - value1);
  }

  /**
   * @param value1
   * @param value2
   * @return the absolute difference between the given 2 values.
   */
  public int getAbsoluteDifference(final int value1, final int value2) {
    return getAbsoluteValue(value2 - value1);
  }

  /**
   * @param value1
   * @param value2
   * @return the absolute difference between the given 2 values.
   */
  public long getAbsoluteDifference(final long value1, final long value2) {
    return getAbsoluteValue(value2 - value1);
  }

  /**
   * The absolute value of a value x is -x if x is negative, x otherwise.
   * 
   * @param value
   * @return the absolute value of the given value.
   */
  public double getAbsoluteValue(final double value) {

    if (value < 0) {
      return -value;
    }

    return value;
  }

  /**
   * The absolute value of a value x is -x if x is negative, x otherwise.
   * 
   * @param value
   * @return the absolute value of the given value.
   */
  public int getAbsoluteValue(final int value) {

    if (value < 0) {
      return -value;
    }

    return value;
  }

  /**
   * The absolute value of a value x is -x if x is negative, x otherwise.
   * 
   * @param value
   * @return the absolute value of the given value.
   */
  public long getAbsoluteValue(final long value) {

    if (value < 0) {
      return -value;
    }

    return value;
  }

  /**
   * @param value
   * @param values
   * @return the average of the given values.
   */
  public double getAverage(final double value, final double... values) {

    final var sum = getSum(value, values);
    final var valueCount = 1 + values.length;

    return (sum / valueCount);
  }

  /**
   * @param values
   * @return the average of the given values.
   * @throws EmptyArgumentException if the given values is empty.
   */
  public double getAverage(final Iterable<Double> values) {

    var count = 0;
    var sum = 0.0;
    for (final var v : values) {
      count++;
      sum += v;
    }

    if (count == 0) {
      throw EmptyArgumentException.forArgumentNameAndArgument(PluralLowerCaseVariableCatalogue.VALUES, values);
    }

    return (sum / count);
  }

  /**
   * @param value
   * @param values
   * @return the average of the given values.
   */
  public int getAverage(final int value, final int... values) {

    final var sum = getSum(value, values);
    final var valueCount = 1 + values.length;

    return (sum / valueCount);
  }

  /**
   * @param value
   * @param values
   * @return the average of the given values.
   */
  public long getAverage(final long value, final long... values) {

    final var sum = getSum(value, values);
    final var valueCount = 1 + values.length;

    return (sum / valueCount);
  }

  /**
   * @param value
   * @param values
   * @return the biggest value of the given values.
   */
  public double getMax(final double value, final double... values) {

    var max = value;
    for (final var v : values) {
      if (v > max) {
        max = v;
      }
    }

    return max;
  }

  /**
   * @param value
   * @param values
   * @return the biggest value of the given values.
   */
  public int getMax(final int value, final int... values) {

    var max = value;
    for (final var v : values) {
      if (v > max) {
        max = v;
      }
    }

    return max;
  }

  /**
   * @param value
   * @param values
   * @return the biggest value of the given values.
   */
  public long getMax(final long value, final long... values) {

    var max = value;
    for (final var v : values) {
      if (v > max) {
        max = v;
      }
    }

    return max;
  }

  /**
   * @param value
   * @param values
   * @return the smallest value of the given values.
   */
  public double getMin(final double value, final double... values) {

    var min = value;
    for (final var v : values) {
      if (v < min) {
        min = v;
      }
    }

    return min;
  }

  /**
   * @param value
   * @param values
   * @return the smallest value of the given values.
   */
  public int getMin(final int value, final int... values) {

    var min = value;
    for (final var v : values) {
      if (v < min) {
        min = v;
      }
    }

    return min;
  }

  /**
   * @param value
   * @param values
   * @return the smallest value of the given values.
   */
  public long getMin(final long value, final long... values) {

    var min = value;
    for (final var v : values) {
      if (v < min) {
        min = v;
      }
    }

    return min;
  }

  /**
   * @param value
   * @return the square of the given value.
   */
  public double getSquare(final double value) {
    return (value * value);
  }

  /**
   * @param value
   * @return the square of the given value.
   */
  public double getSquare(final int value) {
    return (value * value);
  }

  /**
   * @param value
   * @return the square of the given value.
   */
  public double getSquare(final long value) {
    return (value * value);
  }

  /**
   * @param value
   * @param values
   * @return the sum of the given values.
   */
  public double getSum(final double value, final double... values) {

    var sum = value;

    for (final var v : values) {
      sum += v;
    }

    return sum;
  }

  /**
   * @param value
   * @param values
   * @return the sum of the given values.
   */
  public int getSum(final int value, final int... values) {

    var sum = value;
    for (final var v : values) {
      sum += v;
    }

    return sum;
  }

  /**
   * @param values
   * @return the sum of the given values.
   */
  public double getSum(final Iterable<Double> values) {

    var sum = 0.0;
    for (final var v : values) {
      sum += v;
    }

    return sum;
  }

  /**
   * @param value
   * @param values
   * @return the sum of the given values.
   */
  public long getSum(final long value, final long... values) {

    var sum = value;
    for (final var v : values) {
      sum += v;
    }

    return sum;
  }

  //For a better performance, this implementation does not use all comfortable methods.
  /**
   * @param value
   * @return true if the given value is approximately 1.0 with a deviation that is
   *         not bigger than {@value #DEFAULT_MAX_DEVIATION}.
   */
  public boolean isApproximatelyOne(final double value) {
    return (Math.abs(value - 1.0) <= DEFAULT_MAX_DEVIATION);
  }

  /**
   * @param value
   * @param maxDeviation
   * @return true if the given value is approximately 1.0 with a deviation that is
   *         not bigger than the given maxDeviation.
   * @throws NegativeArgumentException if the given maxDeviation is negative.
   */
  public boolean isApproximatelyOne(final double value, final double maxDeviation) {

    //Asserts that the given maxDeviation is not negative.
    GlobalValidator.assertThat(maxDeviation).thatIsNamed("max deviation").isNotNegative();

    return (Math.abs(value - 1.0) <= maxDeviation);
  }

  //For a better performance, this implementation does not use all comfortable methods.
  /**
   * @param value
   * @return true if the given value is approximately 0.0 with a deviation that is
   *         not bigger than {@value #DEFAULT_MAX_DEVIATION}.
   */
  public boolean isApproximatelyZero(final double value) {
    return (Math.abs(value) <= DEFAULT_MAX_DEVIATION);
  }

  /**
   * @param value
   * @param maxDeviation
   * @return true if the given value is approximately 0.0 with a deviation that is
   *         not bigger than the given maxDeviation.
   * @throws NegativeArgumentException if the given maxDeviation is negative.
   */
  public boolean isApproximatelyZero(final double value, final double maxDeviation) {

    //Asserts that the given maxDeviation is not negative.
    GlobalValidator.assertThat(maxDeviation).thatIsNamed("max deviation").isNotNegative();

    return (Math.abs(value) <= maxDeviation);
  }
}
