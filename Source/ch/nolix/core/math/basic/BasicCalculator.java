package ch.nolix.core.math.basic;

import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.coreapi.mathapi.basicapi.IBasicCalculator;
import ch.nolix.coreapi.programatomapi.variableapi.PluralLowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2022-11-13
 */
public final class BasicCalculator implements IBasicCalculator {

  /**
   * {@inheritDoc}
   */
  @Override
  public double getAbsoluteDifference(final double value1, final double value2) {
    return getAbsoluteValue(value2 - value1);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getAbsoluteDifference(final int value1, final int value2) {
    return getAbsoluteValue(value2 - value1);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public long getAbsoluteDifference(final long value1, final long value2) {
    return getAbsoluteValue(value2 - value1);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getAbsoluteValue(final double value) {

    if (value < 0) {
      return -value;
    }

    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getAbsoluteValue(final int value) {

    if (value < 0) {
      return -value;
    }

    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public long getAbsoluteValue(final long value) {

    if (value < 0) {
      return -value;
    }

    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getAverage(final double value, final double... values) {

    final var sum = getSum(value, values);
    final var valueCount = 1 + values.length;

    return (sum / valueCount);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getAverage(final Iterable<Double> values) {

    var count = 0;
    var sum = 0.0;
    for (final var v : values) {
      count++;
      sum += v;
    }

    if (count == 0) {
      throw EmptyArgumentException.forArgumentNameAndArgument(PluralLowerCaseVariableCatalog.VALUES, values);
    }

    return (sum / count);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getAverage(final int value, final int... values) {

    final var sum = getSum(value, values);
    final var valueCount = 1 + values.length;

    return (sum / valueCount);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public long getAverage(final long value, final long... values) {

    final var sum = getSum(value, values);
    final var valueCount = 1 + values.length;

    return (sum / valueCount);
  }

  /**
   * {@inheritDoc}
   */
  @Override
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
   * {@inheritDoc}
   */
  @Override
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
   * {@inheritDoc}
   */
  @Override
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
   * {@inheritDoc}
   */
  @Override
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
   * {@inheritDoc}
   */
  @Override
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
   * {@inheritDoc}
   */
  @Override
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
   * {@inheritDoc}
   */
  @Override
  public double getSquare(final double value) {
    return (value * value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getSquare(final int value) {
    return (value * value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getSquare(final long value) {
    return (value * value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getSum(final double value, final double... values) {

    var sum = value;

    for (final var v : values) {
      sum += v;
    }

    return sum;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getSum(final int value, final int... values) {

    var sum = value;
    for (final var v : values) {
      sum += v;
    }

    return sum;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getSum(final Iterable<Double> values) {

    var sum = 0.0;
    for (final var v : values) {
      sum += v;
    }

    return sum;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public long getSum(final long value, final long... values) {

    var sum = value;
    for (final var v : values) {
      sum += v;
    }

    return sum;
  }
}
