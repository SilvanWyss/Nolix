/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.generalstate.staterequest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.generalstate.staterequest.UpToDateRequestable;

/**
 * @author Silvan Wyss
 */
final class UpToDateRequestableTest extends StandardTest {
  @Test
  void testCase_isOutOfDate_whenIsNotUpToDate() {
    // setup
    final var testUnit = Mockito.mock(UpToDateRequestable.class);
    Mockito.when(testUnit.isOutOfDate()).thenCallRealMethod();
    Mockito.when(testUnit.isUpToDate()).thenReturn(false);

    // execute
    final var result = testUnit.isOutOfDate();

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isOutOfDate_whenIsUpToDate() {
    // setup
    final var testUnit = Mockito.mock(UpToDateRequestable.class);
    Mockito.when(testUnit.isOutOfDate()).thenCallRealMethod();
    Mockito.when(testUnit.isUpToDate()).thenReturn(true);

    // execute
    final var result = testUnit.isOutOfDate();

    // verify
    expect(result).isFalse();
  }
}
