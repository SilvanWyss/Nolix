/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.math.basic;

/**
 * @author Silvan Wyss
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
   * @param values
   * @return the average of the given values.
   * @throws RuntimeException if the given values is null or empty.
   */
  double getAverage(double... values);

  /**
   * @param values
   * @return the average of the given values.
   * @throws RuntimeException if the given values is null or empty.
   */
  double getAverage(Iterable<Double> values);

  /**
   * @param values
   * @return the average of the given values.
   * @throws RuntimeException if the given values is null or empty.
   */
  int getAverage(int... values);

  /**
   * @param values
   * @return the average of the given values.
   * @throws RuntimeException if the given values is null or empty.
   */
  long getAverage(long... values);

  /**
   * @param values
   * @return the biggest value of the given values.
   * @throws RuntimeException if the given values is null or empty.
   */
  double getMax(double... values);

  /**
   * @param values
   * @return the biggest value of the given values.
   * @throws RuntimeException if the given values is null or empty.
   */
  int getMax(int... values);

  /**
   * @param values
   * @return the biggest value of the given values.
   * @throws RuntimeException if the given values is null or empty.
   */
  long getMax(long... values);

  /**
   * @param values
   * @return the smallest value of the given values.
   * @throws RuntimeException if the given values is null or empty.
   */
  double getMin(double... values);

  /**
   * @param values
   * @return the smallest value of the given values.
   * @throws RuntimeException if the given values is null or empty.
   */
  int getMin(int... values);

  /**
   * @param values
   * @return the smallest value of the given values.
   * @throws RuntimeException if the given values is null or empty.
   */
  long getMin(long... values);

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
   * @param values
   * @return the sum of the given values.
   * @throws RuntimeException if the given values is null.
   */
  double getSum(double... values);

  /**
   * @param values
   * @return the sum of the given values.
   * @throws RuntimeException if the given values is null.
   */
  int getSum(int... values);

  /**
   * @param values
   * @return the sum of the given values.
   */
  double getSum(Iterable<Double> values);

  /**
   * @param values
   * @return the sum of the given values.
   * @throws RuntimeException if the given values is null.
   */
  long getSum(long... values);
}
