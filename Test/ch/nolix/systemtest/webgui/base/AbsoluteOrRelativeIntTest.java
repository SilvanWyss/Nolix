/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.webgui.base;

import org.junit.jupiter.api.Test;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.system.webgui.base.AbsoluteOrRelativeInt;

/**
 * @author Silvan Wyss
 */
final class AbsoluteOrRelativeIntTest extends StandardTest {
  @Test
  void testCase_fromSpecification_whenTheGivenSpecificationSpecifiesAValue() {
    // setup
    final var specification = ImmutableNode.fromString("Width(500)");

    // execute
    final var result = AbsoluteOrRelativeInt.fromSpecification(specification);

    // verify
    expect(result.isAbsolute()).isTrue();
    expect(result.getAbsoluteValue()).isEqualTo(500);
  }

  @Test
  void testCase_fromSpecification_whenTheGivenSpecificationSpecifiesAPercentage() {
    // setup
    final var specification = ImmutableNode.fromString("Width(20%)");

    // execute
    final var result = AbsoluteOrRelativeInt.fromSpecification(specification);

    // verify
    expect(result.isRelative()).isTrue();
    expect(result.getPercentage()).isEqualTo(0.2);
  }

  @Test
  void testCase_getAbsoluteValue_whenIsRelative() {
    // setup
    final var testUnit = AbsoluteOrRelativeInt.withPercentage(0.2);

    // execute & verify
    expectRunning(testUnit::getAbsoluteValue)
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class)
      .withMessage("The given AbsoluteOrRelativeInt 'AbsoluteOrRelativeInt(20%)' does not have a absolute value.");
  }

  @Test
  void testCase_getPercentage_whenIsAbsolute() {
    // setup
    final var testUnit = AbsoluteOrRelativeInt.withIntValue(500);

    // execute & verify
    expectRunning(testUnit::getPercentage)
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class)
      .withMessage("The given AbsoluteOrRelativeInt 'AbsoluteOrRelativeInt(500)' does not have a percentage.");
  }

  @Test
  void testCase_getValueRelativeToHundredPercentValue_whenIsAbsolute() {
    // setup
    final var testUnit = AbsoluteOrRelativeInt.withIntValue(500);

    // execute
    final var result = testUnit.getValueRelativeToHundredPercentValue(200);

    // verify
    expect(result).isEqualTo(500);
  }

  @Test
  void testCase_getValueRelativeToHundredPercentValue_whenIsAbsoluteAndTheGivenHundredPercentValueIsZero() {
    // setup
    final var testUnit = AbsoluteOrRelativeInt.withIntValue(500);

    // execute
    final var result = testUnit.getValueRelativeToHundredPercentValue(0);

    // verify
    expect(result).isEqualTo(500);
  }

  @Test
  void testCase_getValueRelativeToHundredPercentValue_whenIsRelative() {
    // setup
    final var testUnit = AbsoluteOrRelativeInt.withPercentage(0.2);

    // execute
    final var result = testUnit.getValueRelativeToHundredPercentValue(200);

    // verify
    expect(result).isEqualTo(40);
  }

  @Test
  void testCase_getValueRelativeToHundredPercentValue_whenIsRelativeAndTheGivenHundredPercentValueIsZero() {
    // setup
    final var testUnit = AbsoluteOrRelativeInt.withPercentage(0.2);

    // execute
    final var result = testUnit.getValueRelativeToHundredPercentValue(0);

    // verify
    expect(result).isEqualTo(0);
  }

  @Test
  void testCase_withIntValue() {
    // execute
    final var result = AbsoluteOrRelativeInt.withIntValue(500);

    // verify
    expect(result.isAbsolute()).isTrue();
    expect(result.getAbsoluteValue()).isEqualTo(500);
  }

  @Test
  void testCase_withPercentage() {
    // execute
    final var result = AbsoluteOrRelativeInt.withPercentage(0.2);

    // verify
    expect(result.isRelative()).isTrue();
    expect(result.getPercentage()).isEqualTo(0.2);
  }

  @Test
  void testCase_withPercentage_whenTheGivenPercentageIsNegative() {
    // execute & verify
    expectRunning(() -> AbsoluteOrRelativeInt.withPercentage(-0.2))
      .throwsException()
      .ofType(NegativeArgumentException.class)
      .withMessage("The given percentage '-0.2' is negative.");
  }
}
