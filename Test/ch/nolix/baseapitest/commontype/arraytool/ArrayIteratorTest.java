/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.commontype.arraytool;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontype.arraytool.ArrayIterator;
import ch.nolix.base.programcontrol.flowcontrol.FlowController;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class ArrayIteratorTest extends StandardTest {
  @Test
  void testCase_next_whenDoesNotHaveMoreElements() {
    // setup
    final var array = new Object[10];
    final var testUnit = ArrayIterator.forArray(array);
    FlowController.forCount(10).run(testUnit::next);

    // verify setup
    expect(testUnit.hasNext()).isFalse();

    // execute & verify
    expectRunning(testUnit::next).throwsException().ofType(NoSuchElementException.class);
  }
}
