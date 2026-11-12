/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.resourcecontrol.resourcerequest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.resourcecontrol.resourcerequest.OpennessRequestable;

/**
 * @author Silvan Wyss
 */
final class OpennessRequestableTest extends StandardTest {
  @Test
  void testCase_isOpen_whenIsClosed() {
    // setup
    final var testUnit = Mockito.mock(OpennessRequestable.class);
    Mockito.when(testUnit.isOpen()).thenCallRealMethod();
    Mockito.when(testUnit.isClosed()).thenReturn(true);

    // execute
    final var result = testUnit.isOpen();

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isOpen_whenIsNotClosed() {
    // setup
    final var testUnit = Mockito.mock(OpennessRequestable.class);
    Mockito.when(testUnit.isOpen()).thenCallRealMethod();
    Mockito.when(testUnit.isClosed()).thenReturn(false);

    // execute
    final var result = testUnit.isOpen();

    // verify
    expect(result).isTrue();
  }
}
