package ch.nolix.coretest.independent.arrayvalidator;

import org.junit.jupiter.api.Test;

import ch.nolix.core.independent.arraytool.ArrayValidator;
import ch.nolix.core.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class ArrayValidatorTest extends StandardTest {
  @Test
  void testCase_assertDoesNotContainNull_whenGivenArrayIsNull() {
    //setup
    final String[] array = null;
    final var testUnit = new ArrayValidator();

    //execution & verification
    expectRunning(() -> testUnit.assertDoesNotContainNull(array))
      .throwsException()
      .ofType(IllegalArgumentException.class)
      .withMessage("The given array is null.");
  }

  @Test
  void testCase_assertDoesNotContainNull_whenGivenArrayIsEmpty() {
    //setup
    final String[] array = {};
    final var testUnit = new ArrayValidator();

    //execution & verification
    expectRunning(() -> testUnit.assertDoesNotContainNull(array)).doesNotThrowException();
  }

  @Test
  void testCase_assertDoesNotContainNull_whenGivenArrayContainsAnyButNotNull() {
    //setup
    final String[] array = { "antelope", "baboon", "elephant", "lion", "rhino", "zebra" };
    final var testUnit = new ArrayValidator();

    //execution & verification
    expectRunning(() -> testUnit.assertDoesNotContainNull(array)).doesNotThrowException();
  }

  @Test
  void testCase_assertDoesNotContainNull_whenGivenArrayContainsNull() {
    //setup
    final String[] array = { "antelope", "baboon", "elephant", "lion", null, "rhino", "zebra" };
    final var testUnit = new ArrayValidator();

    //execution & verification
    expectRunning(() -> testUnit.assertDoesNotContainNull(array))
      .throwsException()
      .ofType(IllegalArgumentException.class)
      .withMessage("The given array contains a null element at the index 4.");
  }
}
