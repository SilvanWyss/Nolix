package ch.nolix.core.errorcontrol.validator;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;

final class ExtendedStringMediatorTest extends StandardTest {

  @Test
  void testCase_thatIsNamed() {

    //setup
    final var argument = "Tom";
    final var testUnit = ExtendedStringMediator.forArgument(argument);

    //execution
    final var result = testUnit.thatIsNamed("name");

    //verification
    expectRunning(() -> result.isNotShorterThan(4))
      .throwsException()
      .withMessage("The given name 'Tom' has the length 3 and is therefore shorter than 4.");
  }
}
