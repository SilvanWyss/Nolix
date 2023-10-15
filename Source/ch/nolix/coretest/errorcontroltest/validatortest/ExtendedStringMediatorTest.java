//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//own imports
import ch.nolix.core.errorcontrol.validator.ExtendedStringMediator;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class ExtendedStringMediatorTest extends Test {

  // method
  @TestCase
  public void testCase_thatIsNamed() {

    // setup
    final var argument = "Tom";
    final var testUnit = ExtendedStringMediator.forArgument(argument);

    // execution
    final var result = testUnit.thatIsNamed("name");

    // verification
    expectRunning(() -> result.isNotShorterThan(4))
        .throwsException()
        .withMessage("The given name 'Tom' has the length 3 and is therefore shorter than 4.");
  }
}
