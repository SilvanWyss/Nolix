/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.generalstate.staterequest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.generalstate.staterequest.AlivenessRequestable;

/**
 * @author Silvan Wyss
 */
final class AlivenessRequestableTest extends StandardTest {
  @Test
  void testCase_isOutdated_whenIsAlive() {
    // setup
    final var testUnit = Mockito.mock(AlivenessRequestable.class);
    Mockito.when(testUnit.isOutdated()).thenCallRealMethod();
    Mockito.when(testUnit.isAlive()).thenReturn(true);

    // execute
    final var result = testUnit.isOutdated();

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isOutdated_whenIsNotAlive() {
    // setup
    final var testUnit = Mockito.mock(AlivenessRequestable.class);
    Mockito.when(testUnit.isOutdated()).thenCallRealMethod();
    Mockito.when(testUnit.isAlive()).thenReturn(false);

    // execute
    final var result = testUnit.isOutdated();

    // verify
    expect(result).isTrue();
  }
}
