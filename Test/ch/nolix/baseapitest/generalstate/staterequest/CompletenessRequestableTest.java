/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.generalstate.staterequest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.generalstate.staterequest.CompletenessRequestable;

/**
 * @author Silvan Wyss
 */
final class CompletenessRequestableTest extends StandardTest {
  @Test
  void testCase_isIncomplete_whenIsComplete() {
    // setup
    final var testUnit = Mockito.mock(CompletenessRequestable.class);
    Mockito.when(testUnit.isIncomplete()).thenCallRealMethod();
    Mockito.when(testUnit.isComplete()).thenReturn(true);

    // execute
    final var result = testUnit.isIncomplete();

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isIncomplete_whenIsNotComplete() {
    // setup
    final var testUnit = Mockito.mock(CompletenessRequestable.class);
    Mockito.when(testUnit.isIncomplete()).thenCallRealMethod();
    Mockito.when(testUnit.isComplete()).thenReturn(false);

    // execute
    final var result = testUnit.isIncomplete();

    // verify
    expect(result).isTrue();
  }
}
