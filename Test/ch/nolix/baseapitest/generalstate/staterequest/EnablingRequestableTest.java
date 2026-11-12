/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.generalstate.staterequest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.generalstate.staterequest.EnablingRequestable;

/**
 * @author Silvan Wyss
 */
final class EnablingRequestableTest extends StandardTest {
  @Test
  void testCase_isDisabled_whenIsEnabled() {
    // setup
    final var testUnit = Mockito.mock(EnablingRequestable.class);
    Mockito.when(testUnit.isDisabled()).thenCallRealMethod();
    Mockito.when(testUnit.isEnabled()).thenReturn(true);

    // execute
    final var result = testUnit.isDisabled();

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isDisabled_whenIsNotEnabled() {
    // setup
    final var testUnit = Mockito.mock(EnablingRequestable.class);
    Mockito.when(testUnit.isDisabled()).thenCallRealMethod();
    Mockito.when(testUnit.isEnabled()).thenReturn(false);

    // execute
    final var result = testUnit.isDisabled();

    // verify
    expect(result).isTrue();
  }
}
