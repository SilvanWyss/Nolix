/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.math.bigdecimalmath;

import java.math.BigDecimal;

/**
 * @author Silvan Wyss
 */
public interface IComplexNumber extends Comparable<IComplexNumber> {
  /**
   * @return a new {@link IComplexNumber} that is the conjugate of the current
   *         {@link IComplexNumber}
   */
  IComplexNumber getConjugate();

  /**
   * @return the number of decimal places of the current {@link IComplexNumber}
   */
  int getDecimalPlaceCount();

  /**
   * @return the imaginary component of the current {@link IComplexNumber}
   */
  BigDecimal getImaginaryComponent();

  /**
   * @return the magnitude of the current {@link IComplexNumber}
   */
  BigDecimal getMagnitude();

  /**
   * @param exponent
   * @return a new {@link IComplexNumber} that is the current
   *         {@link IComplexNumber} to the power of the given exponent
   */
  IComplexNumber getPower(int exponent);

  /**
   * @return a new {@link IComplexNumber} that is the current
   *         {@link IComplexNumber} to the power of 2
   */
  IComplexNumber getPower2();

  /**
   * @return a new {@link IComplexNumber} that is the current
   *         {@link IComplexNumber} to the power of 3
   */
  IComplexNumber getPower3();

  /**
   * @return a new {@link IComplexNumber} that is the current
   *         {@link IComplexNumber} to the power of 4
   */
  IComplexNumber getPower4();

  /**
   * @param number
   * @return a new {@link IComplexNumber} that is the product of the current
   *         {@link IComplexNumber} and the given number
   */
  IComplexNumber getProduct(BigDecimal number);

  /**
   * @param number
   * @return a new {@link IComplexNumber} that is the product of the current
   *         {@link IComplexNumber} and the given number
   */
  IComplexNumber getProduct(double number);

  /**
   * @param complexNumber
   * @return a new {@link IComplexNumber} that is the product of the current
   *         {@link IComplexNumber} and the given complexNumber
   */
  IComplexNumber getProduct(IComplexNumber complexNumber);

  /**
   * @return the real component of the current {@link IComplexNumber}
   */
  BigDecimal getRealComponent();

  /**
   * @return the squared magnitude of the current {@link IComplexNumber}
   */
  BigDecimal getSquaredMagnitude();

  /**
   * @param number
   * @return a new {@link IComplexNumber} that is the sum of the current
   *         {@link IComplexNumber} and the given number
   */
  IComplexNumber getSum(BigDecimal number);

  /**
   * @param number
   * @return a new {@link IComplexNumber} that is the sum of the current
   *         {@link IComplexNumber} and the given number
   */
  IComplexNumber getSum(double number);

  /**
   * @param complexNumber
   * @return a new {@link IComplexNumber} that is the sum of the current
   *         {@link IComplexNumber} and the given complexNumber
   */
  IComplexNumber getSum(IComplexNumber complexNumber);

  /**
   * @return true if the current {@link IComplexNumber} is pure imaginary, false
   *         otherwise
   */
  boolean isPureImaginary();

  /**
   * @return true if the current {@link IComplexNumber} is pure real, false
   *         otherwise
   */
  boolean isPureReal();

  /**
   * @param decimalPlaces
   * @return a new {@link IComplexNumber} with the value of the current
   *         {@link IComplexNumber} and the given decimalPlaces
   * @throws RuntimeException if the given decimalPlaces is not positive
   */
  IComplexNumber withDecimalPlaceCount(int decimalPlaces);
}
