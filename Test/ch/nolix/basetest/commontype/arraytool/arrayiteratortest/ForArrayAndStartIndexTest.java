/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.commontype.arraytool.arrayiteratortest;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontype.arraytool.ArrayIterator;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NegativeArgumentException;

/**
 * @author Silvan Wyss
 */
final class ForArrayAndStartIndexTest extends StandardTest {
  @Test
  void testCase_forArrayAndStartIndex_whenGivenArrayIsNull() {
    // execute & verify
    expectRunning(() -> ArrayIterator.forArrayAndStartIndex(null, 0))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given array is null.");
  }

  @Test
  void testCase_forArrayAndStartIndex_whenGivenStartIndexIsNegative() {
    // execute & verify
    expectRunning(() -> ArrayIterator.forArrayAndStartIndex(new Object[0], -1))
      .throwsException()
      .ofType(NegativeArgumentException.class)
      .withMessage("The given start index '-1' is negative.");
  }

  @Test
  void testCase_forArrayAndStartIndex_whenGivenArrayIsEmptyAndGivenStartIndexIs0() {
    // execute & verify
    expectRunning(() -> ArrayIterator.forArrayAndStartIndex(new Object[0], 0)).doesNotThrowException();
  }
}
