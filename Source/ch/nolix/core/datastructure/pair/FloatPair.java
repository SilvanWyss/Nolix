package ch.nolix.core.datastructure.pair;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.math.main.NumberComparator;

/**
 * A {@link FloatPair} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2016-08-01
 */
public final class FloatPair {

  public static final double DEFAULT_VALUE = 0.0;

  private final double value1;

  private final double value2;

  /**
   * Creates a new {@link FloatPair} with the given values.
   * 
   * @param value1
   * @param value2
   */
  private FloatPair(final double value1, final double value2) {
    this.value1 = value1;
    this.value2 = value2;
  }

  /**
   * @param value1
   * @param value2
   * @return a new {@link FloatPair} with the given values.
   */
  public static FloatPair withValues(final double value1, final double value2) {
    return new FloatPair(value1, value2);
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}}
   */
  @Override
  public boolean equals(final Object object) {

    if (object instanceof final FloatPair floatPair) {
      return //
      NumberComparator.areEqual(value1, floatPair.value1)
      && NumberComparator.areEqual(value2, floatPair.value2);
    }

    return false;
  }

  /**
   * @return value 1 of the current {@link FloatPair}.
   */
  public double getValue1() {
    return value1;
  }

  /**
   * @return value 2 of the current {@link FloatPair}.
   */
  public double getValue2() {
    return value2;
  }

  /**
   * {@inheritDoc}}
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  /**
   * {@inheritDoc}}
   */
  @Override
  public String toString() {
    return StringTool.getInParentheses(getValue1(), getValue2());
  }
}
