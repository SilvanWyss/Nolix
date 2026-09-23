/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.commontype.arraytool.arrayiteratortest;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontype.arraytool.ArrayIterator;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;

/**
 * @author Silvan Wyss
 */
final class ForArrayTest extends StandardTest {
  @Test
  void testCase_forArray_whenGivenArrayIsNull() {
    // execute & verify
    expectRunning(() -> ArrayIterator.forArray(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given array is null.");
  }
}
