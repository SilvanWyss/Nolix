package ch.nolix.techapi.mathapi.bigdecimalmathapi;

import java.math.BigDecimal;

public interface IComplexNumber extends Comparable<IComplexNumber> {

  IComplexNumber getConjugate();

  int getDecimalPlaces();

  BigDecimal getImaginaryComponent();

  BigDecimal getMagnitude();

  IComplexNumber getPower(int exponent);

  IComplexNumber getProduct(BigDecimal number);

  IComplexNumber getProduct(double number);

  IComplexNumber getProduct(IComplexNumber complexNumber);

  BigDecimal getRealComponent();

  int getScale();

  IComplexNumber getPower2();

  IComplexNumber getPower3();

  IComplexNumber getPower4();

  BigDecimal getSquaredMagnitude();

  IComplexNumber getSum(BigDecimal number);

  IComplexNumber getSum(double number);

  IComplexNumber getSum(IComplexNumber complexNumber);

  IComplexNumber inDecimalPlaces(int decimalPlaces);

  boolean isPureImaginary();

  boolean isPureReal();
}
