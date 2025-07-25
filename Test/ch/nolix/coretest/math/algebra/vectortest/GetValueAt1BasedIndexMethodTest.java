package ch.nolix.coretest.math.algebra.vectortest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.math.algebra.Vector;
import ch.nolix.core.testing.standardtest.StandardTest;

final class GetValueAtOneBasedIndexMethodTest extends StandardTest {

  @ParameterizedTest
  @CsvSource({
  "1, 2.0",
  "2, 5.0",
  "3, 10.0",
  "4, -3.0",
  "5, -8.0",
  "6, 0.0"
  })
  void testCase_getValueAtOneBasedIndex_whenGivenOneBasedIndexIsInRange(
    final int oneBasedIndex,
    final double expectedValue) {

    //setup
    final var testUnit = Vector.withValue(2.0, 5.0, 10.0, -3.0, -8.0, 0.0);

    //execution
    final var result = testUnit.getValueAtOneBasedIndex(oneBasedIndex);

    //verification
    expect(result).isEqualTo(expectedValue);
  }

  @ParameterizedTest
  @CsvSource({ "-1", "0", "7", "8"
  })
  void testCase_getValueAtOneBasedIndex_whenGivenOneBasedIndexIsOutOfRange(final int oneBasedIndex) {

    //setup
    final var testUnit = Vector.withValue(2.0, 5.0, 10.0, -3.0, -8.0, 0.0);

    //execution & verification
    expectRunning(() -> testUnit.getValueAtOneBasedIndex(oneBasedIndex))
      .throwsException()
      .ofType(ArgumentIsOutOfRangeException.class)
      .withMessage("The given 1-based index '" + oneBasedIndex + "' is not in [1, 6].");
  }
}
