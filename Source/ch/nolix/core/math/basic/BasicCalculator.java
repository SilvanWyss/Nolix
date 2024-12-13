package ch.nolix.core.math.basic;

import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.PluralLowerCaseVariableCatalogue;

public final class BasicCalculator {

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
}
