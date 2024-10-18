package ch.nolix.core.container.pair;

import ch.nolix.core.commontypetool.stringtool.StringTool;

/**
 * A {@link FloatingPointNumberPair} contains two floating point numbers. FPN =
 * floating pointer number. A {@link FloatingPointNumberPair} is not mutable.
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
   * Creates a new {@link FloatingPointNumberPair} with default values.
   */
  public FloatingPointNumberPair() {

    //Calls other constructor.
    this(DEFAULT_VALUE, DEFAULT_VALUE);
  }

  /**
   * Creates a new {@link FloatingPointNumberPair} with the given values.
   * 
   * @param value1
   * @param value2
   */
  public FloatingPointNumberPair(final double value1, final double value2) {

    //Sets the values of the current FPNPair.
    this.value1 = value1;
    this.value2 = value2;
  }

  /**
   * {@inheritDoc}}
   */
  @Override
  public boolean equals(final Object object) {

    //Handles the case that the given object is not a FPNPair.
    if (!(object instanceof FloatingPointNumberPair)) {
      return false;
    }

    //Handles the case that the given object is a FPNPair.
    final var lFPNPair = (FloatingPointNumberPair) object;
    return (getValue1() == lFPNPair.getValue1() && getValue2() == lFPNPair.getValue2());
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
