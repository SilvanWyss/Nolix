package ch.nolix.coretest.math.main;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.core.math.main.NumberComparator;
import ch.nolix.core.testing.standardtest.StandardTest;

final class NumberComparatorTest extends StandardTest {

  @ParameterizedTest
  @CsvSource({
  "-1.0, -1.1, false",
  "-1.0, -1.01, false",
  "-1.0, -1.001, false",
  "-1.0, -1.000_1, false",
  "-1.0, -1.000_01, false",
  "-1.0, -1.000_000_1, true",
  "-1.0, -1.000_000_01, true",
  "-1.0, -1.000_000_001, true",
  "-1.0, -1.0, true",
  "-1.0, -0.999_9999_999, true",
  "-1.0, -0.999_999_99, true",
  "-1.0, -0.999_999_9, true",
  "-1.0, -0.999_99, false",
  "-1.0, -0.999_9, false",
  "-1.0, -0.999, false",
  "-1.0, -0.99, false",
  "-1.0, -0.9, false",
  "0.0, -0.1, false",
  "0.0, -0.01, false",
  "0.0, -0.001, false",
  "0.0, -0.000_1, false",
  "0.0, -0.000_01, false",
  "0.0, -0.000_000_1, true",
  "0.0, -0.000_000_01, true",
  "0.0, -0.000_000_001, true",
  "0.0, 0.0, true",
  "0.0, 0.000_000_001, true",
  "0.0, 0.000_000_01, true",
  "0.0, 0.000_000_1, true",
  "0.0, 0.000_01, false",
  "0.0, 0.000_1, false",
  "0.0, 0.001, false",
  "0.0, 0.01, false",
  "0.0, 0.1, false",
  "1.0, 0.9, false",
  "1.0, 0.99, false",
  "1.0, 0.999, false",
  "1.0, 0.999_9, false",
  "1.0, 0.999_99, false",
  "1.0, 0.999_999_9, true",
  "1.0, 0.999_999_99, true",
  "1.0, 0.999_9999_999, true",
  "1.0, 1.0, true",
  "1.0, 1.000_000_001, true",
  "1.0, 1.000_000_01, true",
  "1.0, 1.000_000_1, true",
  "1.0, 1.000_01, false",
  "1.0, 1.000_1, false",
  "1.0, 1.001, false",
  "1.0, 1.01, false",
  "1.0, 1.1, false",
  })
  void testCase_areEqual(final double value1, final double value2, final boolean expectedResult) {

    //execution
    final var result = NumberComparator.areEqual(value1, value2);

    //verification
    expect(result).is(expectedResult);
  }

  @ParameterizedTest
  @CsvSource({
  "0.9, false",
  "0.99, false",
  "0.999, false",
  "0.999_9, false",
  "0.999_99, false",
  "0.999_999_9, true",
  "0.999_999_99, true",
  "0.999_9999_999, true",
  "1.0, true",
  "1.000_000_001, true",
  "1.000_000_01, true",
  "1.000_000_1, true",
  "1.000_01, false",
  "1.000_1, false",
  "1.001, false",
  "1.01, false",
  "1.1, false",
  })
  void testCase_isOne(final double value, final boolean expectedResult) {

    //execution
    final var result = NumberComparator.isOne(value);

    //verification
    expect(result).is(expectedResult);
  }

  @ParameterizedTest
  @CsvSource({
  "-0.1, false",
  "-0.01, false",
  "-0.001, false",
  "-0.000_1, false",
  "-0.000_01, false",
  "-0.000_000_1, true",
  "-0.000_000_01, true",
  "-0.000_000_001, true",
  "0.0, true",
  "0.000_000_001, true",
  "0.000_000_01, true",
  "0.000_000_1, true",
  "0.000_01, false",
  "0.000_1, false",
  "0.001, false",
  "0.01, false",
  "0.1, false",
  })
  void testCase_isZero(final double value, final boolean expectedResult) {

    //execution
    final var result = NumberComparator.isZero(value);

    //verification
    expect(result).is(expectedResult);
  }
}
