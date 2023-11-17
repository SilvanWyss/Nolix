//package declaration
package ch.nolix.core.math.main;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.pair.FloatingPointNumberPair;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.math.algebra.Matrix;
import ch.nolix.core.math.algebra.Polynom;
import ch.nolix.core.math.base.GlobalBasicCalculator;
import ch.nolix.core.math.stochastic.ARModel;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;

//class
/**
 * The {@link GlobalCalculator} provides mathematical functions.
 * 
 * @author Silvan Wyss
 * @date 2016-05-01
 */
public final class GlobalCalculator {

  //constant
  /**
   * The default maximum deviation is 10^-9.
   */
  public static final double DEFAULT_MAX_DEVIATION = 0.000000001;

  //constructor
  /**
   * Prevents that an instance of the {@link GlobalCalculator} can be created.
   */
  private GlobalCalculator() {
  }

  //static method
  /**
   * @param xValues
   * @param yValues
   * @return a new {@link LinkedList} with {@link FloatingPointNumberPair}s
   *         created from the given xValues and yValues.
   * @throws InvalidArgumentException if the count of the given yValues does not
   *                                  equal the count of the given xValues.
   */
  public static LinkedList<FloatingPointNumberPair> createFPNPairs(final double[] xValues, final double[] yValues) {
    return GlobalBasicCalculator.createFPNPairs(xValues, yValues);
  }

  //static method
  /**
   * @param value1
   * @param value2
   * @return true if the given values equals approximately each other with a
   *         deviation that is not bigger than {@value #DEFAULT_MAX_DEVIATION}.
   */
  public static boolean equalsApproximatively(final double value1, final double value2) {
    return GlobalBasicCalculator.equalsApproximatively(value1, value2);
  }

  //static method
  /**
   * @param value1
   * @param value2
   * @param maxDeviation
   * @return true if the given values equals approximately each other with a
   *         deviation that is not bigger than the given maxDeviation.
   * @throws NegativeArgumentException if the given maxDeviation is negative.
   */
  public static boolean equalsApproximatively(final double value1, final double value2, final double maxDeviation) {
    return GlobalBasicCalculator.equalsApproximatively(value1, value2, maxDeviation);
  }

  //static method
  /**
   * @param value1
   * @param value2
   * @return the absolute difference between the given 2 values.
   */
  public static double getAbsoluteDifference(final double value1, final double value2) {
    return getAbsoluteValue(value2 - value1);
  }

  //static method
  /**
   * @param value1
   * @param value2
   * @return the absolute difference between the given 2 values.
   */
  public static int getAbsoluteDifference(final int value1, final int value2) {
    return getAbsoluteValue(value2 - value1);
  }

  //static method
  /**
   * @param value1
   * @param value2
   * @return the absolute difference between the given 2 values.
   */
  public static long getAbsoluteDifference(final long value1, final long value2) {
    return getAbsoluteValue(value2 - value1);
  }

  //static method
  /**
   * The absolute value of a value x is -x if x is negative, and is x in the else
   * case.
   * 
   * @param value
   * @return the absolute value of the given value.
   */
  public static double getAbsoluteValue(final double value) {

    if (value < 0.0) {
      return -value;
    }

    return value;
  }

  //static method
  /**
   * The absolute value of a value x is -x if x is negative, and is x in the else
   * case.
   * 
   * @param value
   * @return the absolute value of the given value.
   */
  public static int getAbsoluteValue(final int value) {

    if (value < 0) {
      return -value;
    }

    return value;
  }

  //static method
  /**
   * The absolute value of a value x is -x if x is negative, and is x in the else
   * case.
   * 
   * @param value
   * @return the absolute value of the given value.
   */
  public static long getAbsoluteValue(final long value) {

    if (value < 0) {
      return -value;
    }

    return value;
  }

  //static method
  /**
   * @param value
   * @param values
   * @return the average of the given values.
   */
  public static double getAverage(final double value, final double... values) {
    return GlobalBasicCalculator.getAverage(value, values);
  }

  //method
  /**
   * @param values
   * @return the average of the given values.
   * @throws EmptyArgumentException if the given values is empty.
   */
  public static double getAverage(final Iterable<Double> values) {
    return GlobalBasicCalculator.getAverage(values);
  }

  //static method
  /**
   * @param value
   * @param values
   * @return the average of the given values.
   */
  public static int getAverage(final int value, final int... values) {
    return GlobalBasicCalculator.getAverage(value, values);
  }

  //static method
  /**
   * @param value
   * @param values
   * @return the average of the given values.
   * @throws EmptyArgumentException if the given values is empty.
   */
  public static long getAverage(final long value, final long... values) {
    return GlobalBasicCalculator.getAverage(value, values);
  }

  //static method
  /**
   * @param pOrder
   * @param inputValues
   * @return a new {@link ARModel} with the given pOrder and inputValues.
   * @throws NegativeArgumentException if the given pOrder is negative.
   */
  public static ARModel getARModell(final int pOrder, final double[] inputValues) {
    return new ARModel(pOrder, inputValues);
  }

  //static method
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
    GlobalValidator.assertThat(degree).thatIsNamed(LowerCaseCatalogue.DEGREE).isNotNegative();

    //Asserts that the given degree is not bigger than the count of the given
    //xValues.
    GlobalValidator.assertThat(degree).thatIsNamed(LowerCaseCatalogue.DEGREE).isNotBiggerThan(xValues.length);

    //Asserts that the count of the given yValues equals the count of the given
    //xValues.
    GlobalValidator.assertThat(yValues).thatIsNamed("y-values container").hasSameSizeAs(xValues);

    final var factorMatrix = new Matrix(xValues.length, degree + 1);
    final var xMatrixValues = new double[factorMatrix.getSize()];
    for (var i = 0; i < factorMatrix.getRowCount(); i++) {
      for (var j = 0; j < factorMatrix.getColumnCount(); j++) {
        xMatrixValues[i * factorMatrix.getColumnCount() + j] = Math.pow(xValues[i],
          factorMatrix.getColumnCount() - j - 1.0);
      }
    }
    factorMatrix.setValues(xMatrixValues);

    final var solutionMatrix = new Matrix(yValues.length, 1);
    solutionMatrix.setValues(yValues);

    return factorMatrix.getMinimalFactorMatrix(solutionMatrix).toPolynom();
  }

  //static method
  /**
   * @param value
   * @param values
   * @return the biggest value of the given values.
   */
  public static double getMax(final double value, final double... values) {
    return GlobalBasicCalculator.getMax(value, values);
  }

  //static method
  /**
   * @param value
   * @param values
   * @return the biggest value of the given values.
   */
  public static int getMax(final int value, final int... values) {
    return GlobalBasicCalculator.getMax(value, values);
  }

  //static method
  /**
   * @param value
   * @param values
   * @return the biggest value of the given values.
   */
  public static long getMax(final long value, final long... values) {
    return GlobalBasicCalculator.getMax(value, values);
  }

  //static method
  /**
   * @param value
   * @param values
   * @return the smallest value of the given values.
   */
  public static double getMin(final double value, final double... values) {
    return GlobalBasicCalculator.getMin(value, values);
  }

  //static method
  /**
   * @param value
   * @param values
   * @return the smallest value of the given values.
   */
  public static int getMin(final int value, final int... values) {
    return GlobalBasicCalculator.getMin(value, values);
  }

  //static method
  /**
   * @param value
   * @param values
   * @return the smallest value of the given values.
   */
  public static long getMin(final long value, final long... values) {
    return GlobalBasicCalculator.getMin(value, values);
  }

  //static method
  /**
   * @param value
   * @return the square of the given value.
   */
  public static double getSquare(final double value) {
    return GlobalBasicCalculator.getSquare(value);
  }

  //static method
  /**
   * @param value
   * @return the square of the given value.
   */
  public static double getSquare(final int value) {
    return GlobalBasicCalculator.getSquare(value);
  }

  //static method
  /**
   * @param value
   * @return the square of the given value.
   */
  public static double getSquare(final long value) {
    return GlobalBasicCalculator.getSquare(value);
  }

  //static method
  /**
   * @param value
   * @param values
   * @return the sum of the given values.
   */
  public static double getSum(final double value, final double... values) {
    return GlobalBasicCalculator.getSum(value, values);
  }

  //static method
  /**
   * @param value
   * @param values
   * @return the sum of the given values.
   */
  public static int getSum(final int value, final int... values) {
    return GlobalBasicCalculator.getSum(value, values);
  }

  //static method
  /**
   * @param values
   * @return the sum of the given values.
   */
  public static double getSum(final Iterable<Double> values) {
    return GlobalBasicCalculator.getSum(values);
  }

  //static method
  /**
   * @param value
   * @param values
   * @return the sum of the given values.
   */
  public static long getSum(final long value, final long... values) {
    return GlobalBasicCalculator.getSum(value, values);
  }

  //static method
  /**
   * @param value
   * @return true if the given value is approximately 1.0 with a deviation that is
   *         not bigger than {@value #DEFAULT_MAX_DEVIATION}.
   */
  public static boolean isApproximatelyOne(final double value) {
    return GlobalBasicCalculator.isApproximatelyOne(value);
  }

  //static method
  /**
   * @param value
   * @param maxDeviation
   * @return true if the given value is approximately 1.0 with a deviation that is
   *         not bigger than the given maxDeviation.
   * @throws NegativeArgumentException if the given maxDeviation is negative.
   */
  public static boolean isApproximatelyOne(final double value, final double maxDeviation) {
    return GlobalBasicCalculator.isApproximatelyOne(value, maxDeviation);
  }

  //static method
  /**
   * @param value
   * @return true if the given value is approximately 0.0 with a deviation that is
   *         not bigger than {@value #DEFAULT_MAX_DEVIATION}.
   */
  public static boolean isApproximatelyZero(final double value) {
    return GlobalBasicCalculator.isApproximatelyZero(value);
  }

  //static method
  /**
   * @param value
   * @param maxDeviation
   * @return true if the given value is approximately 0.0 with a deviation that is
   *         not bigger than the given maxDeviation.
   * @throws NegativeArgumentException if the given maxDeviation is negative.
   */
  public static boolean isApproximatelyZero(final double value, final double maxDeviation) {
    return GlobalBasicCalculator.isApproximatelyZero(value, maxDeviation);
  }
}
