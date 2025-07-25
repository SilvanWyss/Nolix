package ch.nolix.coretest.container.arraylist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.core.container.arraylist.ArrayListCapacityCalculator;
import ch.nolix.core.testing.standardtest.StandardTest;

final class ArrayListCapacityCalculatorTest extends StandardTest {

  @ParameterizedTest
  @CsvSource({
  "0, 1, 1", //
  "0, 1_000, 1_000", //
  "0, 1_001, 1_001", //
  "0, 1_000_000, 1_000_000", //
  "0, 1_000_001, 1_000_001", //
  "0, 1_000_000_000, 1_000_000_000", //
  "0, 1_000_000_001, 2_147_483_647", //
  "0, 2_000_000_000, 2_147_483_647", //
  "1_000, 1_001, 2_000", //
  "1_000, 1_000_000, 1_000_000", //
  "1_000, 1_000_001, 1_000_001", //
  "1_000, 1_000_000_000, 1_000_000_000", //
  "1_000, 1_000_000_001, 2_147_483_647", //
  "1_000, 2_000_000_000, 2_147_483_647", //
  "1_000_000, 1_000_001, 2_000_000", //
  "1_000_000, 1_000_000_000, 1_000_000_000", //
  "1_000_000, 1_000_000_001, 2_147_483_647", //
  "1_000_000, 2_000_000_000, 2_147_483_647" //
  })
  void testCase_calculateTargetCapacityForActualCapacityAndRequiredCapacity(
    final int actualCapacity,
    final int requiredCapacity,
    final int expectedResult) {

    //setup
    final var testUnit = new ArrayListCapacityCalculator();

    //execution
    final var result = //
    testUnit.calculateTargetCapacityForActualCapacityAndRequiredCapacity(actualCapacity, requiredCapacity);

    //verification
    expect(result).isEqualTo(expectedResult);
  }
}
