//package declaration
package ch.nolix.systemtest.structuretest;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.structure.AbsoluteOrRelativeInt;

//class
public final class AbsoluteOrRelativeIntTest extends Test {

  //method
  @TestCase
  public void testCase_fromSpecification_whenTheGivenSpecificationSpecifiesAValue() {

    //setup
    final var specification = Node.fromString("Width(500)");

    //execution
    final var result = AbsoluteOrRelativeInt.fromSpecification(specification);

    //verification
    expect(result.isAbsolute());
    expect(result.getAbsoluteValue()).isEqualTo(500);
  }

  //method
  @TestCase
  public void testCase_fromSpecification_whenTheGivenSpecificationSpecifiesAPercentage() {

    //setup
    final var specification = Node.fromString("Width(20%)");

    //execution
    final var result = AbsoluteOrRelativeInt.fromSpecification(specification);

    //verification
    expect(result.isRelative());
    expect(result.getPercentage()).isEqualTo(0.2);
  }

  //method
  @TestCase
  public void testCase_getAbsoluteValue_whenIsRelative() {

    //setup
    final var testUnit = AbsoluteOrRelativeInt.withPercentage(0.2);

    //execution & verification
    expectRunning(testUnit::getAbsoluteValue)
        .throwsException()
        .ofType(InvalidArgumentException.class)
        .withMessage("The given AbsoluteOrRelativeInt 'AbsoluteOrRelativeInt(20%)' does not have a absolute value.");
  }

  //method
  @TestCase
  public void testCase_getPercentage_whenIsAbsolute() {

    //setup
    final var testUnit = AbsoluteOrRelativeInt.withIntValue(500);

    //execution & verification
    expectRunning(testUnit::getPercentage)
        .throwsException()
        .ofType(InvalidArgumentException.class)
        .withMessage("The given AbsoluteOrRelativeInt 'AbsoluteOrRelativeInt(500)' does not have a percentage.");
  }

  //method
  @TestCase
  public void testCase_getValueRelativeToHundredPercentValue_whenIsAbsolute() {

    //setup
    final var testUnit = AbsoluteOrRelativeInt.withIntValue(500);

    //execution
    final var result = testUnit.getValueRelativeToHundredPercentValue(200);

    //verification
    expect(result).isEqualTo(500);
  }

  //method
  @TestCase
  public void testCase_getValueRelativeToHundredPercentValue_whenIsAbsoluteAndTheGivenHundredPercentValueIsZero() {

    //setup
    final var testUnit = AbsoluteOrRelativeInt.withIntValue(500);

    //execution
    final var result = testUnit.getValueRelativeToHundredPercentValue(0);

    //verification
    expect(result).isEqualTo(500);
  }

  //method
  @TestCase
  public void testCase_getValueRelativeToHundredPercentValue_whenIsRelative() {

    //setup
    final var testUnit = AbsoluteOrRelativeInt.withPercentage(0.2);

    //execution
    final var result = testUnit.getValueRelativeToHundredPercentValue(200);

    //verification
    expect(result).isEqualTo(40);
  }

  //method
  @TestCase
  public void testCase_getValueRelativeToHundredPercentValue_whenIsRelativeAndTheGivenHundredPercentValueIsZero() {

    //setup
    final var testUnit = AbsoluteOrRelativeInt.withPercentage(0.2);

    //execution
    final var result = testUnit.getValueRelativeToHundredPercentValue(0);

    //verification
    expect(result).isEqualTo(0);
  }

  //method
  @TestCase
  public void testCase_withIntValue() {

    //execution
    final var result = AbsoluteOrRelativeInt.withIntValue(500);

    //verification
    expect(result.isAbsolute());
    expect(result.getAbsoluteValue()).isEqualTo(500);
  }

  //method
  @TestCase
  public void testCase_withPercentage() {

    //execution
    final var result = AbsoluteOrRelativeInt.withPercentage(0.2);

    //verification
    expect(result.isRelative());
    expect(result.getPercentage()).isEqualTo(0.2);
  }

  //method
  @TestCase
  public void testCase_withPercentage_whenTheGivenPercentageIsNegative() {

    //execution & verification
    expectRunning(() -> AbsoluteOrRelativeInt.withPercentage(-0.2))
        .throwsException()
        .ofType(NegativeArgumentException.class)
        .withMessage("The given percentage '-0.2' is negative.");
  }
}
