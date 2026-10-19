/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.commontype.arraytool;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontype.arraytool.ByteArrayWithStartIndexMediator;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;

/**
 * @author Silvan Wyss
 */
final class ByteArrayWithStartIndexMediatorTest extends StandardTest {
  @Test
  void testCase_forByteArrayAndStartIndex_whenGivenByteArrayIsNull() {
    expectRunning(() -> ByteArrayWithStartIndexMediator.forByteArrayAndStartIndex(null, 0))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given byte array is null.");
  }

  @Test
  void testCase_forByteArrayAndStartIndex_whenGivenStartIndexIsNegative() {
    expectRunning(() -> ByteArrayWithStartIndexMediator.forByteArrayAndStartIndex(new byte[10], -1))
      .throwsException()
      .ofType(ArgumentIsOutOfRangeException.class)
      .withMessage("The given start index '-1' is not in [0, 9].");
  }

  @Test
  void testCase_forByteArrayAndStartIndex_whenGivenStartIndexIsOutOfRange() {
    expectRunning(() -> ByteArrayWithStartIndexMediator.forByteArrayAndStartIndex(new byte[10], 10))
      .throwsException()
      .ofType(ArgumentIsOutOfRangeException.class)
      .withMessage("The given start index '10' is not in [0, 9].");
  }
}
