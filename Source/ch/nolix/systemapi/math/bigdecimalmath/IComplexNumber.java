/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.math.bigdecimalmath;

import java.math.BigDecimal;

/**
 * @author Silvan Wyss
 */
public interface IComplexNumber extends Comparable<IComplexNumber> {
  IComplexNumber getConjugate();

  int getDecimalPlaceCount();

  BigDecimal getImaginaryComponent();

  BigDecimal getMagnitude();

  IComplexNumber getPower(int exponent);

  IComplexNumber getProduct(BigDecimal number);

  IComplexNumber getProduct(double number);

  IComplexNumber getProduct(IComplexNumber complexNumber);

  BigDecimal getRealComponent();

  IComplexNumber getPower2();

  IComplexNumber getPower3();

  IComplexNumber getPower4();

  BigDecimal getSquaredMagnitude();

  IComplexNumber getSum(BigDecimal number);

  IComplexNumber getSum(double number);

  IComplexNumber getSum(IComplexNumber complexNumber);

  boolean isPureImaginary();

  boolean isPureReal();

  IComplexNumber withDecimalPlaceCount(int decimalPlaces);
}
