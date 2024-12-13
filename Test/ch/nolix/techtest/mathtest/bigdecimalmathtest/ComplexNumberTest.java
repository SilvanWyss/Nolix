package ch.nolix.techtest.mathtest.bigdecimalmathtest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.tech.math.bigdecimalmath.ComplexNumber;

final class ComplexNumberTest extends StandardTest {

  @Test
  void testCase_constructor_1A() {

    //execution
    final var result = new ComplexNumber(0.0, 0.0);

    //verification
    expect(result.getRealComponent().doubleValue()).isEqualTo(0.0);
    expect(result.getImaginaryComponent().doubleValue()).isEqualTo(0.0);
  }

  @Test
  void testCase_constructor_1B() {

    //execution
    final var result = new ComplexNumber(1.0, 0.0);

    //verification
    expect(result.getRealComponent().doubleValue()).isEqualTo(1.0);
    expect(result.getImaginaryComponent().doubleValue()).isEqualTo(0.0);
  }

  @Test
  void testCase_constructor_1C() {

    //execution
    final var result = new ComplexNumber(0.0, 1.0);

    //verification
    expect(result.getRealComponent().doubleValue()).isEqualTo(0.0);
    expect(result.getImaginaryComponent().doubleValue()).isEqualTo(1.0);
  }

  @Test
  void testCase_constructor_2A() {

    //execution
    final var realComponent = new BigDecimal("3.14159265359").setScale(5, RoundingMode.HALF_UP);
    final var imaginaryComponent = new BigDecimal("2.71828182846").setScale(10, RoundingMode.HALF_UP);

    //execution
    final var result = new ComplexNumber(realComponent, imaginaryComponent);

    //verification
    expect(result.getScale()).isEqualTo(10);
    expect(result.getRealComponent()).hasStringRepresentation("3.1415900000");
    expect(result.getImaginaryComponent()).hasStringRepresentation("2.7182818285");
  }

  @Test
  void testCase_constructor_2B() {

    //execution
    final var realComponent = new BigDecimal("3.14159265359").setScale(10, RoundingMode.HALF_UP);
    final var imaginaryComponent = new BigDecimal("2.71828182846").setScale(5, RoundingMode.HALF_UP);

    //execution
    final var result = new ComplexNumber(realComponent, imaginaryComponent);

    //verification
    expect(result.getScale()).isEqualTo(10);
    expect(result.getRealComponent()).hasStringRepresentation("3.1415926536");
    expect(result.getImaginaryComponent()).hasStringRepresentation("2.7182800000");
  }

  @Test
  void testCase_getConjugate_1A() {

    //setup
    final var testUnit = new ComplexNumber(0.0, 0.0);

    //execution
    final var result = testUnit.getConjugate();

    //verification
    expect(result).isEqualTo(new ComplexNumber(0.0, 0.0));
  }

  @Test
  void testCase_getConjugate_1B() {

    //setup
    final var testUnit = new ComplexNumber(0.0, 1.0);

    //execution
    final var result = testUnit.getConjugate();

    //verification
    expect(result).isEqualTo(new ComplexNumber(0.0, -1.0));
  }

  @Test
  void testCase_getConjugate_1C() {

    //setup
    final var testUnit = new ComplexNumber(1.0, 0.0);

    //execution
    final var result = testUnit.getConjugate();

    //verification
    expect(result).isEqualTo(new ComplexNumber(1.0, 0.0));
  }

  @Test
  void testCase_getConjugate_1D() {

    //setup
    final var testUnit = new ComplexNumber(1.0, 1.0);

    //execution
    final var result = testUnit.getConjugate();

    //verification
    expect(result).isEqualTo(new ComplexNumber(1.0, -1.0));
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

    //parameter definition
    final var scale = 10;

    //setup
    final var testUnit = new ComplexNumber(realComponent, imaginaryComponent);

    //execution
    final var result = testUnit.getMagnitude();

    //verification
    expect(result).isEqualTo(BigDecimal.valueOf(expectedMagnitude).setScale(scale));
  }

  @Test
  void testCase_getProduct_1A() {

    //parameter definition
    final var factor = new ComplexNumber(0.0, 0.0);

    //setup
    final var testUnit = new ComplexNumber(0.0, 0.0);

    //execution
    final var result = testUnit.getProduct(factor);

    //verification
    expect(result).isEqualTo(new ComplexNumber(0.0, 0.0));
  }

  @Test
  void testCase_getProduct_1B() {

    //parameter definition
    final var factor = new ComplexNumber(0.0, 0.0);

    //setup
    final var testUnit = new ComplexNumber(1.0, 1.0);

    //execution
    final var result = testUnit.getProduct(factor);

    //verification
    expect(result).isEqualTo(new ComplexNumber(0.0, 0.0));
  }

  @Test
  void testCase_getSum_1A() {

    //setup
    final var testUnit = new ComplexNumber(-1.0, -1.0);

    //execution & verification
    expect(testUnit.getSum(new ComplexNumber(-1.0, -1.0))).isEqualTo(new ComplexNumber(-2.0, -2.0));
    expect(testUnit.getSum(new ComplexNumber(-1.0, 0.0))).isEqualTo(new ComplexNumber(-2.0, -1.0));
    expect(testUnit.getSum(new ComplexNumber(-1.0, 1.0))).isEqualTo(new ComplexNumber(-2.0, 0.0));
    expect(testUnit.getSum(new ComplexNumber(0.0, -1.0))).isEqualTo(new ComplexNumber(-1.0, -2.0));
    expect(testUnit.getSum(new ComplexNumber(0.0, 0.0))).isEqualTo(new ComplexNumber(-1.0, -1.0));
    expect(testUnit.getSum(new ComplexNumber(0.0, 1.0))).isEqualTo(new ComplexNumber(-1.0, 0.0));
    expect(testUnit.getSum(new ComplexNumber(1.0, -1.0))).isEqualTo(new ComplexNumber(0.0, -2.0));
    expect(testUnit.getSum(new ComplexNumber(1.0, 0.0))).isEqualTo(new ComplexNumber(0.0, -1.0));
    expect(testUnit.getSum(new ComplexNumber(1.0, 1.0))).isEqualTo(new ComplexNumber(0.0, 0.0));
  }

  @Test
  void testCase_getSum_1B() {

    //setup
    final var testUnit = new ComplexNumber(0.0, 0.0);

    //execution & verification
    expect(testUnit.getSum(new ComplexNumber(-1.0, -1.0))).isEqualTo(new ComplexNumber(-1.0, -1.0));
    expect(testUnit.getSum(new ComplexNumber(-1.0, 0.0))).isEqualTo(new ComplexNumber(-1.0, 0.0));
    expect(testUnit.getSum(new ComplexNumber(-1.0, 1.0))).isEqualTo(new ComplexNumber(-1.0, 1.0));
    expect(testUnit.getSum(new ComplexNumber(0.0, -1.0))).isEqualTo(new ComplexNumber(0.0, -1.0));
    expect(testUnit.getSum(new ComplexNumber(0.0, 0.0))).isEqualTo(new ComplexNumber(0.0, 0.0));
    expect(testUnit.getSum(new ComplexNumber(0.0, 1.0))).isEqualTo(new ComplexNumber(0.0, 1.0));
    expect(testUnit.getSum(new ComplexNumber(1.0, -1.0))).isEqualTo(new ComplexNumber(1.0, -1.0));
    expect(testUnit.getSum(new ComplexNumber(1.0, 0.0))).isEqualTo(new ComplexNumber(1.0, 0.0));
    expect(testUnit.getSum(new ComplexNumber(1.0, 1.0))).isEqualTo(new ComplexNumber(1.0, 1.0));
  }

  @Test
  void testCase_getSum_1C() {

    //setup
    final var testUnit = new ComplexNumber(1.0, 1.0);

    //execution & verification
    expect(testUnit.getSum(new ComplexNumber(-1.0, -1.0))).isEqualTo(new ComplexNumber(0.0, 0.0));
    expect(testUnit.getSum(new ComplexNumber(-1.0, 0.0))).isEqualTo(new ComplexNumber(0.0, 1.0));
    expect(testUnit.getSum(new ComplexNumber(-1.0, 1.0))).isEqualTo(new ComplexNumber(0.0, 2.0));
    expect(testUnit.getSum(new ComplexNumber(0.0, -1.0))).isEqualTo(new ComplexNumber(1.0, 0.0));
    expect(testUnit.getSum(new ComplexNumber(0.0, 0.0))).isEqualTo(new ComplexNumber(1.0, 1.0));
    expect(testUnit.getSum(new ComplexNumber(0.0, 1.0))).isEqualTo(new ComplexNumber(1.0, 2.0));
    expect(testUnit.getSum(new ComplexNumber(1.0, -1.0))).isEqualTo(new ComplexNumber(2.0, 0.0));
    expect(testUnit.getSum(new ComplexNumber(1.0, 0.0))).isEqualTo(new ComplexNumber(2.0, 1.0));
    expect(testUnit.getSum(new ComplexNumber(1.0, 1.0))).isEqualTo(new ComplexNumber(2.0, 2.0));
  }

  @Test
  void testCase_inDecimalPlaces() {

    //setup
    final var testUnit = new ComplexNumber(3.0, 2.0, 10);

    //execution
    final var result = testUnit.inDecimalPlaces(50);

    //verification
    expect(result.getDecimalPlaces()).isEqualTo(50);
    expect(result.getRealComponent().scale()).isEqualTo(50);
    expect(result.getImaginaryComponent().scale()).isEqualTo(50);
  }

  @Test
  void testCase_isPureImaginary_whenTheGivenComplexNumberIsNotPureImaginary_1() {

    //setup
    final var testUnit = new ComplexNumber(1.0, -1.0);

    //execution
    final var result = testUnit.isPureImaginary();

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_isPureImaginary_whenTheGivenComplexNumberIsNotPureImaginary_2() {

    //setup
    final var testUnit = new ComplexNumber(1.0, 0.0);

    //execution
    final var result = testUnit.isPureImaginary();

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_isPureImaginary_whenTheGivenComplexNumberIsNotPureImaginary_3() {

    //setup
    final var testUnit = new ComplexNumber(1.0, 1.0);

    //execution
    final var result = testUnit.isPureImaginary();

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_isPureImaginary_whenTheGivenComplexNumberIsPureImaginary_1() {

    //setup
    final var testUnit = new ComplexNumber(0.0, -1.0);

    //execution
    final var result = testUnit.isPureImaginary();

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_isPureImaginary_whenTheGivenComplexNumberIsPureImaginary_2() {

    //setup
    final var testUnit = new ComplexNumber(0.0, 0.0);

    //execution
    final var result = testUnit.isPureImaginary();

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_isPureImaginary_whenTheGivenComplexNumberIsPureImaginary_3() {

    //setup
    final var testUnit = new ComplexNumber(0.0, 1.0);

    //execution
    final var result = testUnit.isPureImaginary();

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_isPureReal_whenTheGivenComplexNumberIsNotPureReal_1() {

    //setup
    final var testUnit = new ComplexNumber(-1.0, 1.0);

    //execution
    final var result = testUnit.isPureReal();

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_isPureReal_whenTheGivenComplexNumberIsNotPureReal_2() {

    //setup
    final var testUnit = new ComplexNumber(0.0, 1.0);

    //execution
    final var result = testUnit.isPureReal();

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_isPureReal_whenTheGivenComplexNumberIsNotPureReal_3() {

    //setup
    final var testUnit = new ComplexNumber(1.0, 1.0);

    //execution
    final var result = testUnit.isPureReal();

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_isPureReal_whenTheGivenComplexNumberIsPureReal_1() {

    //setup
    final var testUnit = new ComplexNumber(-1.0, 0.0);

    //execution
    final var result = testUnit.isPureReal();

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_isPureReal_whenTheGivenComplexNumberIsPureReal_2() {

    //setup
    final var testUnit = new ComplexNumber(0.0, 0.0);

    //execution
    final var result = testUnit.isPureReal();

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_isPureReal_whenTheGivenComplexNumberIsPureReal_3() {

    //setup
    final var testUnit = new ComplexNumber(1.0, 0.0);

    //execution
    final var result = testUnit.isPureReal();

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_toString() {

    //setup
    final var testUnit = new ComplexNumber(3.0, 2.0, 5);

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("3.00000 + 2.00000i");
  }
}
