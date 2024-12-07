package ch.nolix.core.math.main;

import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.math.algebra.Matrix;
import ch.nolix.core.math.algebra.Polynom;
import ch.nolix.core.math.basic.BasicCalculator;
import ch.nolix.core.math.stochastic.ARModel;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

/**
 * The {@link GlobalCalculator} provides mathematical functions.
 * 
 * @author Silvan Wyss
 * @version 2016-05-01
 */
public final class GlobalCalculator {

  /**
   * The default maximum deviation is 10^-9.
   */
  public static final double DEFAULT_MAX_DEVIATION = BasicCalculator.DEFAULT_MAX_DEVIATION;

  private static final BasicCalculator BASIC_CALCULATOR = new BasicCalculator();

  /**
   * Prevents that an instance of the {@link GlobalCalculator} can be created.
   */
  private GlobalCalculator() {
  }

  /**
   * @param value1
   * @param value2
   * @return true if the given values equals approximately each other with a
   *         deviation that is not bigger than {@value #DEFAULT_MAX_DEVIATION}.
   */
  public static boolean equalsApproximatively(final double value1, final double value2) {
    return BASIC_CALCULATOR.equalsApproximatively(value1, value2);
  }

  /**
   * @param value1
   * @param value2
   * @param maxDeviation
   * @return true if the given values equals approximately each other with a
   *         deviation that is not bigger than the given maxDeviation.
   * @throws NegativeArgumentException if the given maxDeviation is negative.
   */
  public static boolean equalsApproximatively(final double value1, final double value2, final double maxDeviation) {
    return BASIC_CALCULATOR.equalsApproximatively(value1, value2, maxDeviation);
  }

  /**
   * @param value1
   * @param value2
   * @return the absolute difference between the given 2 values.
   */
  public static double getAbsoluteDifference(final double value1, final double value2) {
    return BASIC_CALCULATOR.getAbsoluteDifference(value2, value1);
  }

  /**
   * @param value1
   * @param value2
   * @return the absolute difference between the given 2 values.
   */
  public static int getAbsoluteDifference(final int value1, final int value2) {
    return BASIC_CALCULATOR.getAbsoluteDifference(value2, value1);
  }

  /**
   * @param value1
   * @param value2
   * @return the absolute difference between the given 2 values.
   */
  public static long getAbsoluteDifference(final long value1, final long value2) {
    return BASIC_CALCULATOR.getAbsoluteDifference(value2, value1);
  }

  /**
   * The absolute value of a value x is -x if x is negative, x otherwise.
   * 
   * @param value
   * @return the absolute value of the given value.
   */
  public static double getAbsoluteValue(final double value) {
    return BASIC_CALCULATOR.getAbsoluteValue(value);
  }

  /**
   * The absolute value of a value x is -x if x is negative, x otherwise.
   * 
   * @param value
   * @return the absolute value of the given value.
   */
  public static int getAbsoluteValue(final int value) {
    return BASIC_CALCULATOR.getAbsoluteValue(value);
  }

  /**
   * The absolute value of a value x is -x if x is negative, x otherwise.
   * 
   * @param value
   * @return the absolute value of the given value.
   */
  public static long getAbsoluteValue(final long value) {
    return BASIC_CALCULATOR.getAbsoluteValue(value);
  }

  /**
   * @param value
   * @param values
   * @return the average of the given values.
   */
  public static double getAverage(final double value, final double... values) {
    return BASIC_CALCULATOR.getAverage(value, values);
  }

  /**
   * @param values
   * @return the average of the given values.
   * @throws EmptyArgumentException if the given values is empty.
   */
  public static double getAverage(final Iterable<Double> values) {
    return BASIC_CALCULATOR.getAverage(values);
  }

  /**
   * @param value
   * @param values
   * @return the average of the given values.
   */
  public static int getAverage(final int value, final int... values) {
    return BASIC_CALCULATOR.getAverage(value, values);
  }

  /**
   * @param value
   * @param values
   * @return the average of the given values.
   * @throws EmptyArgumentException if the given values is empty.
   */
  public static long getAverage(final long value, final long... values) {
    return BASIC_CALCULATOR.getAverage(value, values);
  }

  /**
   * @param pOrder
   * @param inputValues
   * @return a new {@link ARModel} with the given pOrder and inputValues.
   * @throws NegativeArgumentException if the given pOrder is negative.
   */
  public static ARModel getARModell(final int pOrder, final double[] inputValues) {
    return new ARModel(pOrder, inputValues);
  }

  /**
   * @param degree
   * @param xValues
   * @param yValues
   * @return a new {@link Polynom} that has the given degree and fits the given
   *         values
   * @throws NegativeArgumentException if the given degree is negative.
   * @throws BiggerArgumentException   if the given degree is bigger than the
   *                                   count of the given xValues.
   * @throws InvalidArgumentException  if the count of the given yValues does not
   *                                   equal the count of the given xValues.
   */
  public static Polynom getFittingPolynom(final int degree, final double[] xValues, final double[] yValues) {

    //Asserts that the given degree is not negative.
    GlobalValidator.assertThat(degree).thatIsNamed(LowerCaseVariableCatalogue.DEGREE).isNotNegative();

    //Asserts that the given degree is not bigger than the count of the given
    //xValues.
    GlobalValidator.assertThat(degree).thatIsNamed(LowerCaseVariableCatalogue.DEGREE).isNotBiggerThan(xValues.length);

    //Asserts that the count of the given yValues equals the count of the given
    //xValues.
    GlobalValidator.assertThat(yValues).thatIsNamed("y-values container").hasSameSizeAs(xValues);

    final var factorMatrix = Matrix.createMatrixWithRowCountAndColumnCount(xValues.length, degree + 1);
    final var xMatrixValues = new double[factorMatrix.getSize()];
    for (var i = 0; i < factorMatrix.getRowCount(); i++) {
      for (var j = 0; j < factorMatrix.getColumnCount(); j++) {
        xMatrixValues[i * factorMatrix.getColumnCount() + j] = Math.pow(xValues[i],
          factorMatrix.getColumnCount() - j - 1.0);
      }
    }
    factorMatrix.setValues(xMatrixValues);

    final var solutionMatrix = Matrix.createMatrixWithRowCountAndColumnCount(yValues.length, 1);
    solutionMatrix.setValues(yValues);

    return factorMatrix.getMinimalFactorMatrix(solutionMatrix).toPolynom();
  }

  /**
   * @param value
   * @param values
   * @return the biggest value of the given values.
   */
  public static double getMax(final double value, final double... values) {
    return BASIC_CALCULATOR.getMax(value, values);
  }

  /**
   * @param value
   * @param values
   * @return the biggest value of the given values.
   */
  public static int getMax(final int value, final int... values) {
    return BASIC_CALCULATOR.getMax(value, values);
  }

  /**
   * @param value
   * @param values
   * @return the biggest value of the given values.
   */
  public static long getMax(final long value, final long... values) {
    return BASIC_CALCULATOR.getMax(value, values);
  }

  /**
   * @param value
   * @param values
   * @return the smallest value of the given values.
   */
  public static double getMin(final double value, final double... values) {
    return BASIC_CALCULATOR.getMin(value, values);
  }

  /**
   * @param value
   * @param values
   * @return the smallest value of the given values.
   */
  public static int getMin(final int value, final int... values) {
    return BASIC_CALCULATOR.getMin(value, values);
  }

  /**
   * @param value
   * @param values
   * @return the smallest value of the given values.
   */
  public static long getMin(final long value, final long... values) {
    return BASIC_CALCULATOR.getMin(value, values);
  }

  /**
   * @param value
   * @return the square of the given value.
   */
  public static double getSquare(final double value) {
    return BASIC_CALCULATOR.getSquare(value);
  }

  /**
   * @param value
   * @return the square of the given value.
   */
  public static double getSquare(final int value) {
    return BASIC_CALCULATOR.getSquare(value);
  }

  /**
   * @param value
   * @return the square of the given value.
   */
  public static double getSquare(final long value) {
    return BASIC_CALCULATOR.getSquare(value);
  }

  /**
   * @param value
   * @param values
   * @return the sum of the given values.
   */
  public static double getSum(final double value, final double... values) {
    return BASIC_CALCULATOR.getSum(value, values);
  }

  /**
   * @param value
   * @param values
   * @return the sum of the given values.
   */
  public static int getSum(final int value, final int... values) {
    return BASIC_CALCULATOR.getSum(value, values);
  }

  /**
   * @param values
   * @return the sum of the given values.
   */
  public static double getSum(final Iterable<Double> values) {
    return BASIC_CALCULATOR.getSum(values);
  }

  /**
   * @param value
   * @param values
   * @return the sum of the given values.
   */
  public static long getSum(final long value, final long... values) {
    return BASIC_CALCULATOR.getSum(value, values);
  }

  /**
   * @param value
   * @return true if the given value is approximately 1.0 with a deviation that is
   *         not bigger than {@value #DEFAULT_MAX_DEVIATION}.
   */
  public static boolean isApproximatelyOne(final double value) {
    return BASIC_CALCULATOR.isApproximatelyOne(value);
  }

  /**
   * @param value
   * @param maxDeviation
   * @return true if the given value is approximately 1.0 with a deviation that is
   *         not bigger than the given maxDeviation.
   * @throws NegativeArgumentException if the given maxDeviation is negative.
   */
  public static boolean isApproximatelyOne(final double value, final double maxDeviation) {
    return BASIC_CALCULATOR.isApproximatelyOne(value, maxDeviation);
  }

  /**
   * @param value
   * @return true if the given value is approximately 0.0 with a deviation that is
   *         not bigger than {@value #DEFAULT_MAX_DEVIATION}.
   */
  public static boolean isApproximatelyZero(final double value) {
    return BASIC_CALCULATOR.isApproximatelyZero(value);
  }

  /**
   * @param value
   * @param maxDeviation
   * @return true if the given value is approximately 0.0 with a deviation that is
   *         not bigger than the given maxDeviation.
   * @throws NegativeArgumentException if the given maxDeviation is negative.
   */
  public static boolean isApproximatelyZero(final double value, final double maxDeviation) {
    return BASIC_CALCULATOR.isApproximatelyZero(value, maxDeviation);
  }
}
