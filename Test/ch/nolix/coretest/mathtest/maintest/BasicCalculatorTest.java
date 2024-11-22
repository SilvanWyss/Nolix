package ch.nolix.coretest.mathtest.maintest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.core.math.basic.BasicCalculator;
import ch.nolix.core.testing.standardtest.StandardTest;

final class BasicCalculatorTest extends StandardTest {

  @ParameterizedTest
  @CsvSource({
  "-1.0, -1.0, 0.0",
  "-1.0, -0.5, 0.5",
  "-1.0, 0.0, 1.0",
  "-1.0, 0.5, 1.5",
  "-1.0, 1.0, 2.0",
  "0.0, -1.0, 1.0",
  "0.0, -0.5, 0.5",
  "0.0, 0.0, 0.0",
  "0.0, 0.5, 0.5",
  "0.0, 1.0, 1.0",
  "1.0, -1.0, 2.0",
  "1.0, -0.5, 1.5",
  "1.0, 0.0, 1.0",
  "1.0, 0.5, 0.5",
  "1.0, 1.0, 0.0"
  })
  void testCase_getAbsoluteDifference(
    final double value1,
    final double value2,
    final double expectedAbsoluteDifference) {

    //setup
    final var testUnit = new BasicCalculator();

    //execution
    final var result = testUnit.getAbsoluteDifference(value1, value2);

    //validation
    expect(result).isEqualTo(expectedAbsoluteDifference);
  }

  @ParameterizedTest
  @CsvSource({
  "-1, -1, 0",
  "-1, 0, 1",
  "-1, 1, 2",
  "0, -1, 1",
  "0, 0, 0",
  "0, 1, 1",
  "1, -1, 2",
  "1, 0, 1",
  "1, 1, 0"
  })
  void testCase_getAbsoluteDifference(final int value1, final int value2, final int expectedAbsoluteDifference) {

    //setup
    final var testUnit = new BasicCalculator();

    //execution
    final var result = testUnit.getAbsoluteDifference(value1, value2);

    //validation
    expect(result).isEqualTo(expectedAbsoluteDifference);
  }

  @ParameterizedTest
  @CsvSource({
  "-1, -1, 0",
  "-1, 0, 1",
  "-1, 1, 2",
  "0, -1, 1",
  "0, 0, 0",
  "0, 1, 1",
  "1, -1, 2",
  "1, 0, 1",
  "1, 1, 0"
  })
  void testCase_getAbsoluteDifference(final long value1, final long value2, final long expectedAbsoluteDifference) {

    //setup
    final var testUnit = new BasicCalculator();

    //execution
    final var result = testUnit.getAbsoluteDifference(value1, value2);

    //validation
    expect(result).isEqualTo(expectedAbsoluteDifference);
  }

  @ParameterizedTest
  @CsvSource({
  "-1.0, 1.0",
  "-0.5, 0.5",
  "0.0, 0.0",
  "0.5, 0.5",
  "1.0, 1.0"
  })
  void testCase_getAbsoluteValue(final double value, final double expectedAbsoluteValue) {

    //setup
    final var testUnit = new BasicCalculator();

    //execution
    final var result = testUnit.getAbsoluteValue(value);

    //validation
    expect(result).isEqualTo(expectedAbsoluteValue);
  }

  @ParameterizedTest
  @CsvSource({
  "-1, 1",
  "0, 0",
  "1, 1"
  })
  void testCase_getAbsoluteValue(final int value, final int expectedAbsoluteValue) {

    //setup
    final var testUnit = new BasicCalculator();

    //execution
    final var result = testUnit.getAbsoluteValue(value);

    //validation
    expect(result).isEqualTo(expectedAbsoluteValue);
  }

  @ParameterizedTest
  @CsvSource({
  "-1, 1",
  "0, 0",
  "1, 1"
  })
  void testCase_getAbsoluteValue(final long value, final long expectedAbsoluteValue) {

    //setup
    final var testUnit = new BasicCalculator();

    //execution
    final var result = testUnit.getAbsoluteValue(value);

    //validation
    expect(result).isEqualTo(expectedAbsoluteValue);
  }

  @Test
  void testCase_getMax() {

    //setup
    final var testUnit = new BasicCalculator();

    //execution
    final var result = testUnit.getMax(-2.0, -1.0, 0.0, 1.0, 2.0);

    //validation
    expect(result).isEqualTo(2.0);
  }

  @Test
  void testCase_getMin() {

    //setup
    final var testUnit = new BasicCalculator();

    //execution
    final var result = testUnit.getMin(-2.0, -1.0, 0.0, 1.0, 2.0);

    //validation
    expect(result).isEqualTo(-2.0);
  }
}
