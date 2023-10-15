//package declaration
package ch.nolix.techapi.mathapi.bigdecimalmathapi;

//Java imports
import java.math.BigDecimal;

//interface
public interface IComplexNumber extends Comparable<IComplexNumber> {

  // method declaration
  int getBigDecimalScale();

  // method declaration
  IComplexNumber getConjugate();

  // method declaration
  BigDecimal getImaginaryComponent();

  // method declaration
  BigDecimal getMagnitude();

  // method declaration
  IComplexNumber getPower(int exponent);

  // method declaration
  IComplexNumber getProduct(BigDecimal number);

  // method declaration
  IComplexNumber getProduct(double number);

  // method declaration
  IComplexNumber getProduct(IComplexNumber complexNumber);

  // method declaration
  BigDecimal getRealComponent();

  // method declaration
  int getScale();

  // method declaration
  IComplexNumber getPower2();

  // method declaration
  IComplexNumber getPower3();

  // method declaration
  IComplexNumber getPower4();

  // method declaration
  BigDecimal getSquaredMagnitude();

  // method declaration
  IComplexNumber getSum(BigDecimal number);

  // method declaration
  IComplexNumber getSum(double number);

  // method declaration
  IComplexNumber getSum(IComplexNumber complexNumber);

  // method declaration
  IComplexNumber inBigDecimalScale(int bigDecimalScale);

  // method declaration
  boolean isPureImaginary();

  // method declaration
  boolean isPureReal();
}
