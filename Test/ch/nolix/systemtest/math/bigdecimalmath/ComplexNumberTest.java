/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.math.bigdecimalmath;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.math.bigdecimalmath.ComplexNumber;

/**
 * @author Silvan Wyss
 */
final class ComplexNumberTest extends StandardTest {
  @Test
  void testCase_constructor_1A() {
    // execute
    final var result = ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0);

    // verify
    expect(result.getRealComponent().doubleValue()).isEqualTo(0.0);
    expect(result.getImaginaryComponent().doubleValue()).isEqualTo(0.0);
  }

  @Test
  void testCase_constructor_1B() {
    // execute
    final var result = ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 0.0);

    // verify
    expect(result.getRealComponent().doubleValue()).isEqualTo(1.0);
    expect(result.getImaginaryComponent().doubleValue()).isEqualTo(0.0);
  }

  @Test
  void testCase_constructor_1C() {
    // execute
    final var result = ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 1.0);

    // verify
    expect(result.getRealComponent().doubleValue()).isEqualTo(0.0);
    expect(result.getImaginaryComponent().doubleValue()).isEqualTo(1.0);
  }

  @Test
  void testCase_constructor_2A() {
    // execute
    final var realComponent = new BigDecimal("3.14159265359").setScale(5, RoundingMode.HALF_UP);
    final var imaginaryComponent = new BigDecimal("2.71828182846").setScale(10, RoundingMode.HALF_UP);

    // execute
    final var result = ComplexNumber.withRealComponentAndImaginaryComponent(realComponent, imaginaryComponent);

    // verify
    expect(result.getDecimalPlaceCount()).isEqualTo(10);
    expect(result.getRealComponent()).hasStringRepresentation("3.1415900000");
    expect(result.getImaginaryComponent()).hasStringRepresentation("2.7182818285");
  }

  @Test
  void testCase_constructor_2B() {
    // execute
    final var realComponent = new BigDecimal("3.14159265359").setScale(10, RoundingMode.HALF_UP);
    final var imaginaryComponent = new BigDecimal("2.71828182846").setScale(5, RoundingMode.HALF_UP);

    // execute
    final var result = ComplexNumber.withRealComponentAndImaginaryComponent(realComponent, imaginaryComponent);

    // verify
    expect(result.getDecimalPlaceCount()).isEqualTo(10);
    expect(result.getRealComponent()).hasStringRepresentation("3.1415926536");
    expect(result.getImaginaryComponent()).hasStringRepresentation("2.7182800000");
  }

  @Test
  void testCase_getConjugate_1A() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0);

    // execute
    final var result = testUnit.getConjugate();

    // verify
    expect(result).isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0));
  }

  @Test
  void testCase_getConjugate_1B() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 1.0);

    // execute
    final var result = testUnit.getConjugate();

    // verify
    expect(result).isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, -1.0));
  }

  @Test
  void testCase_getConjugate_1C() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 0.0);

    // execute
    final var result = testUnit.getConjugate();

    // verify
    expect(result).isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 0.0));
  }

  @Test
  void testCase_getConjugate_1D() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 1.0);

    // execute
    final var result = testUnit.getConjugate();

    // verify
    expect(result).isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, -1.0));
  }

  @ParameterizedTest
  @CsvSource({
  "0.0, 0.0, 0.0",
  "0.0, 1.0, 1.0",
  "1.0, 0.0, 1.0",
  "3.0, 4.0, 5.0",
  "4.0, 3.0, 5.0"
  })
  void testCase_getMagnitude(
    final double realComponent,
    final double imaginaryComponent,
    final double expectedMagnitude) {

    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(realComponent, imaginaryComponent);

    // execute
    final var result = testUnit.getMagnitude();

    // verify
    expect(result).isEqualTo(BigDecimal.valueOf(expectedMagnitude).setScale(20));
  }

  @Test
  void testCase_getProduct_1A() {
    // define test parameters
    final var factor = ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0);

    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0);

    // execute
    final var result = testUnit.getProduct(factor);

    // verify
    expect(result).isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0));
  }

  @Test
  void testCase_getProduct_1B() {
    // define test parameters
    final var factor = ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0);

    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 1.0);

    // execute
    final var result = testUnit.getProduct(factor);

    // verify
    expect(result).isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0));
  }

  @Test
  void testCase_getSum_1A() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(-1.0, -1.0);

    // execute & verify
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(-1.0, -1.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(-2.0, -2.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(-1.0, 0.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(-2.0, -1.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(-1.0, 1.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(-2.0, 0.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, -1.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(-1.0, -2.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(-1.0, -1.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 1.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(-1.0, 0.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, -1.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, -2.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 0.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, -1.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 1.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0));
  }

  @Test
  void testCase_getSum_1B() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0);

    // execute & verify
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(-1.0, -1.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(-1.0, -1.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(-1.0, 0.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(-1.0, 0.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(-1.0, 1.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(-1.0, 1.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, -1.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, -1.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 1.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 1.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, -1.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, -1.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 0.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 0.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 1.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 1.0));
  }

  @Test
  void testCase_getSum_1C() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 1.0);

    // execute & verify
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(-1.0, -1.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(-1.0, 0.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 1.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(-1.0, 1.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 2.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, -1.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 0.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 1.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 1.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 2.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, -1.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(2.0, 0.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 0.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(2.0, 1.0));
    expect(testUnit.getSum(ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 1.0)))
      .isEqualTo(ComplexNumber.withRealComponentAndImaginaryComponent(2.0, 2.0));
  }

  @Test
  void testCase_inDecimalPlaces() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponentAndDecimalPlaceCount(3.0, 2.0, 10);

    // execute
    final var result = testUnit.withDecimalPlaceCount(50);

    // verify
    expect(result.getDecimalPlaceCount()).isEqualTo(50);
    expect(result.getRealComponent().scale()).isEqualTo(50);
    expect(result.getImaginaryComponent().scale()).isEqualTo(50);
  }

  @Test
  void testCase_isPureImaginary_whenTheGivenComplexNumberIsNotPureImaginary_1() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(1.0, -1.0);

    // execute
    final var result = testUnit.isPureImaginary();

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isPureImaginary_whenTheGivenComplexNumberIsNotPureImaginary_2() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 0.0);

    // execute
    final var result = testUnit.isPureImaginary();

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isPureImaginary_whenTheGivenComplexNumberIsNotPureImaginary_3() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 1.0);

    // execute
    final var result = testUnit.isPureImaginary();

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isPureImaginary_whenTheGivenComplexNumberIsPureImaginary_1() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(0.0, -1.0);

    // execute
    final var result = testUnit.isPureImaginary();

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isPureImaginary_whenTheGivenComplexNumberIsPureImaginary_2() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0);

    // execute
    final var result = testUnit.isPureImaginary();

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isPureImaginary_whenTheGivenComplexNumberIsPureImaginary_3() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 1.0);

    // execute
    final var result = testUnit.isPureImaginary();

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isPureReal_whenTheGivenComplexNumberIsNotPureReal_1() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(-1.0, 1.0);

    // execute
    final var result = testUnit.isPureReal();

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isPureReal_whenTheGivenComplexNumberIsNotPureReal_2() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 1.0);

    // execute
    final var result = testUnit.isPureReal();

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isPureReal_whenTheGivenComplexNumberIsNotPureReal_3() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 1.0);

    // execute
    final var result = testUnit.isPureReal();

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isPureReal_whenTheGivenComplexNumberIsPureReal_1() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(-1.0, 0.0);

    // execute
    final var result = testUnit.isPureReal();

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isPureReal_whenTheGivenComplexNumberIsPureReal_2() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(0.0, 0.0);

    // execute
    final var result = testUnit.isPureReal();

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isPureReal_whenTheGivenComplexNumberIsPureReal_3() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponent(1.0, 0.0);

    // execute
    final var result = testUnit.isPureReal();

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_toString() {
    // setup
    final var testUnit = ComplexNumber.withRealComponentAndImaginaryComponentAndDecimalPlaceCount(3.0, 2.0, 5);

    // execute
    final var result = testUnit.toString();

    // verify
    expect(result).isEqualTo("3.00000 + 2.00000i");
  }
}
