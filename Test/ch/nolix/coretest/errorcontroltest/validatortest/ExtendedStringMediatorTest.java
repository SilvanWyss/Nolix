//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.errorcontrol.validator.ExtendedStringMediator;
import ch.nolix.core.testing.test.StandardTest;

//class
final class ExtendedStringMediatorTest extends StandardTest {

  //method
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
