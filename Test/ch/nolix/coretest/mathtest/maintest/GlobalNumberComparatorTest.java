package ch.nolix.coretest.mathtest.maintest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.core.math.main.GlobalNumberComparator;
import ch.nolix.core.testing.standardtest.StandardTest;

final class GlobalNumberComparatorTest extends StandardTest {

  @Test
  void temp() {

    //execution
    final var result = GlobalNumberComparator.areEqual(0.0, 0.0);

    //TODO: Create BooleanMediator
    //verification
    expect(result);
  }

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
    final var result = GlobalNumberComparator.areEqual(value1, value2);

    //TODO: Create BooleanMediator
    //verification
    expect(Boolean.valueOf(result)).isEqualTo(expectedResult);
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
    final var result = GlobalNumberComparator.isOne(value);

    //TODO: Create BooleanMediator
    //verification
    expect(Boolean.valueOf(result)).isEqualTo(expectedResult);
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
    final var result = GlobalNumberComparator.isZero(value);

    //TODO: Create BooleanMediator
    //verification
    expect(Boolean.valueOf(result)).isEqualTo(expectedResult);
  }
}
