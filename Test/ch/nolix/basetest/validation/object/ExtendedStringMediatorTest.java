/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.validation.object;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.validation.object.NamableStringMediator;

/**
 * @author Silvan Wyss
 */
final class ExtendedStringMediatorTest extends StandardTest {
  @Test
  void testCase_thatIsNamed() {
    // setup
    final var argument = "Tom";
    final var testUnit = NamableStringMediator.forArgument(argument);

    // execute
    final var result = testUnit.thatIsNamed("name");

    // verify
    expectRunning(() -> result.isNotShorterThan(4))
      .throwsException()
      .withMessage("The given name 'Tom' has the length 3 and is therefore shorter than 4.");
  }
}
