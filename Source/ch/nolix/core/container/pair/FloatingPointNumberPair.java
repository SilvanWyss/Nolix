package ch.nolix.core.container.pair;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.coreapi.mathapi.machineprecisionapi.ComparsionThresholdCatalogue;

/**
 * A {@link FloatingPointNumberPair} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2016-08-01
 */
public final class FloatingPointNumberPair {

  public static final double DEFAULT_VALUE = 0.0;

  private static final StringTool STRING_TOOL = new StringTool();

  private final double value1;

  private final double value2;

  /**
   * Creates a new {@link FloatingPointNumberPair} with the given values.
   * 
   * @param value1
   * @param value2
   */
  private FloatingPointNumberPair(final double value1, final double value2) {
    this.value1 = value1;
    this.value2 = value2;
  }

  /**
   * @param value1
   * @param value2
   * @return a new {@link FloatingPointNumberPair} with the given values.
   */
  public static FloatingPointNumberPair withValues(final double value1, final double value2) {
    return new FloatingPointNumberPair(value1, value2);
  }

  //TODO: Centralize this implementation.
  /**
   * @param value1
   * @param value2
   * @return true if the given values are equal, false otherwise.
   */
  private static boolean areEqual(double value1, double value2) {

    if (value1 < value2) {
      return (value1 + ComparsionThresholdCatalogue.COMMON_DOUBLE_COMPARSION_THRESHOLD >= value2);
    }

    return (value2 + ComparsionThresholdCatalogue.COMMON_DOUBLE_COMPARSION_THRESHOLD >= value1);
  }

  //For a better performance, this implementation does not use all comfortable methods.
  /**
   * {@inheritDoc}}
   */
  @Override
  public boolean equals(final Object object) {

    if (object instanceof FloatingPointNumberPair floatingPointNumberPair) {
      return areEqual(value1, floatingPointNumberPair.value1) && areEqual(value2, floatingPointNumberPair.value2);
    }

    return false;
  }

  /**
   * @return value 1 of the current {@link FloatingPointNumberPair}.
   */
  public double getValue1() {
    return value1;
  }

  /**
   * @return value 2 of the current {@link FloatingPointNumberPair}.
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
    return STRING_TOOL.getInParentheses(getValue1(), getValue2());
  }
}
