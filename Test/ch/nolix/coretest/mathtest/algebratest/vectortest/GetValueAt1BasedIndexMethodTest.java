//package declaration
package ch.nolix.coretest.mathtest.algebratest.vectortest;

//JUnit imports
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.math.algebra.Vector;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class GetValueAt1BasedIndexMethodTest extends StandardTest {

  //method
  @ParameterizedTest
  @CsvSource({
  "1, 2.0",
  "2, 5.0",
  "3, 10.0",
  "4, -3.0",
  "5, -8.0",
  "6, 0.0"
  })
  void testCase_getValueAt1BasedIndex_whenGiven1BasedIndexIsInRange(
    final int param1BasedIndex,
    final double expectedValue) {

    //setup
    final var testUnit = Vector.withValue(2.0, 5.0, 10.0, -3.0, -8.0, 0.0);

    //execution
    final var result = testUnit.getValueAt1BasedIndex(param1BasedIndex);

    //verification
    expect(result).isEqualTo(expectedValue);
  }

  //method
  @ParameterizedTest
  @CsvSource({ "-1", "0", "7", "8"
  })
  void testCase_getValueAt1BasedIndex_whenGiven1BasedIndexIsOutOfRange(final int param1BasedIndex) {

    //setup
    final var testUnit = Vector.withValue(2.0, 5.0, 10.0, -3.0, -8.0, 0.0);

    //execution & verification
    expectRunning(() -> testUnit.getValueAt1BasedIndex(param1BasedIndex))
      .throwsException()
      .ofType(ArgumentIsOutOfRangeException.class)
      .withMessage("The given 1-based index '" + param1BasedIndex + "' is not in [1, 6].");
  }
}
