package ch.nolix.coreapi.mathapi.basicapi;

/**
 * @author Silvan Wyss
 * @version 2024-12-27
 */
public interface IBasicCalculator {

  /**
   * @param value1
   * @param value2
   * @return the absolute difference between the given 2 values.
   */
  double getAbsoluteDifference(double value1, double value2);

  /**
   * @param value1
   * @param value2
   * @return the absolute difference between the given 2 values.
   */
  int getAbsoluteDifference(int value1, int value2);

  /**
   * @param value1
   * @param value2
   * @return the absolute difference between the given 2 values.
   */
  long getAbsoluteDifference(long value1, long value2);

  /**
   * The absolute value of a value x is -x if x is negative, x otherwise.
   * 
   * @param value
   * @return the absolute value of the given value.
   */
  double getAbsoluteValue(double value);

  /**
   * The absolute value of a value x is -x if x is negative, x otherwise.
   * 
   * @param value
   * @return the absolute value of the given value.
   */
  int getAbsoluteValue(int value);

  /**
   * The absolute value of a value x is -x if x is negative, x otherwise.
   * 
   * @param value
   * @return the absolute value of the given value.
   */
  long getAbsoluteValue(long value);

  /**
   * @param value
   * @param values
   * @return the average of the given values.
   */
  double getAverage(double value, double... values);

  /**
   * @param values
   * @return the average of the given values.
   * @throws RuntimeException if the given values is empty.
   */
  double getAverage(Iterable<Double> values);

  /**
   * @param value
   * @param values
   * @return the average of the given values.
   */
  int getAverage(int value, int... values);

  /**
   * @param value
   * @param values
   * @return the average of the given values.
   */
  long getAverage(long value, long... values);

  /**
   * @param value
   * @param values
   * @return the biggest value of the given values.
   */
  double getMax(double value, double... values);

  /**
   * @param value
   * @param values
   * @return the biggest value of the given values.
   */
  int getMax(int value, int... values);

  /**
   * @param value
   * @param values
   * @return the biggest value of the given values.
   */
  long getMax(long value, long... values);

  /**
   * @param value
   * @param values
   * @return the smallest value of the given values.
   */
  double getMin(double value, double... values);

  /**
   * @param value
   * @param values
   * @return the smallest value of the given values.
   */
  int getMin(int value, int... values);

  /**
   * @param value
   * @param values
   * @return the smallest value of the given values.
   */
  long getMin(long value, long... values);

  /**
   * @param value
   * @return the square of the given value.
   */
  double getSquare(double value);

  /**
   * @param value
   * @return the square of the given value.
   */
  double getSquare(int value);

  /**
   * @param value
   * @return the square of the given value.
   */
  double getSquare(long value);

  /**
   * @param value
   * @param values
   * @return the sum of the given values.
   */
  double getSum(double value, double... values);

  /**
   * @param value
   * @param values
   * @return the sum of the given values.
   */
  int getSum(int value, int... values);

  /**
   * @param values
   * @return the sum of the given values.
   */
  double getSum(Iterable<Double> values);

  /**
   * @param value
   * @param values
   * @return the sum of the given values.
   */
  long getSum(long value, long... values);
}
