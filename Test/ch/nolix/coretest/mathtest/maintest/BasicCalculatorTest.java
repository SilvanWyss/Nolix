//package decl
package ch.nolix.coretest.mathtest.maintest;

//JUnit imports
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

//own imports
import ch.nolix.core.math.basic.BasicCalculator;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class BasicCalculatorTest extends StandardTest {

  //method
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
  void testCase_getAbsoluteValue(final double value1, final double value2, final double expectedAbsoluteDifference) {

    //setup
    final var testUnit = new BasicCalculator();

    //execution
    final var result = testUnit.getAbsoluteDifference(value1, value2);

    //validation
    expect(result).isEqualTo(expectedAbsoluteDifference);
  }

  //method
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
  void testCase_getAbsoluteValue(final int value1, final int value2, final int expectedAbsoluteDifference) {

    //setup
    final var testUnit = new BasicCalculator();

    //execution
    final var result = testUnit.getAbsoluteDifference(value1, value2);

    //validation
    expect(result).isEqualTo(expectedAbsoluteDifference);
  }

  //method
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

  //method
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

  //method
  @Test
  void testCase_getMax() {

    //setup
    final var testUnit = new BasicCalculator();

    //execution
    final var result = testUnit.getMax(-2.0, -1.0, 0.0, 1.0, 2.0);

    //validation
    expect(result).isEqualTo(2.0);
  }

  //method
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
