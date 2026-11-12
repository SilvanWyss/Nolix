/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.generalstate.staterequest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.generalstate.staterequest.EmptinessRequestable;

/**
 * @author Silvan Wyss
 */
final class EmptinessRequestableTest extends StandardTest {
  @Test
  void testCase_containsAny_whenIsEmpty() {
    // setup
    final var testUnit = Mockito.mock(EmptinessRequestable.class);
    Mockito.when(testUnit.containsAny()).thenCallRealMethod();
    Mockito.when(testUnit.isEmpty()).thenReturn(true);

    // execute
    final var result = testUnit.containsAny();

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_containsAny_whenIsNotEmpty() {
    // setup
    final var testUnit = Mockito.mock(EmptinessRequestable.class);
    Mockito.when(testUnit.containsAny()).thenCallRealMethod();
    Mockito.when(testUnit.isEmpty()).thenReturn(false);

    // execute
    final var result = testUnit.containsAny();

    // verify
    expect(result).isTrue();
  }
}
