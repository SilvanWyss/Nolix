/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.commontype.arraytool;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontype.arraytool.ByteArrayMediator;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;

/**
 * @author Silvan Wyss
 */
final class ByteArrayMediatorTest extends StandardTest {
  @Test
  void testCase_forByteArray_whenGivenByteArrayIsNull() {
    // execute & verify
    expectRunning(() -> ByteArrayMediator.forByteArray(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given byte array is null.");
  }
}
