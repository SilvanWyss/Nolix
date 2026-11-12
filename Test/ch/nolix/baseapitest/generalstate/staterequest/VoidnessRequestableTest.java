/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.generalstate.staterequest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.generalstate.staterequest.VoidnessRequestable;

/**
 * @author Silvan Wyss
 */
final class VoidnessRequestableTest extends StandardTest {
  @Test
  void testCase_isEffectual_whenIsNotVoid() {
    // setup
    final var testUnit = Mockito.mock(VoidnessRequestable.class);
    Mockito.when(testUnit.isEffectual()).thenCallRealMethod();
    Mockito.when(testUnit.isVoid()).thenReturn(false);

    // execute
    final var result = testUnit.isEffectual();

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isEffectual_whenIsVoid() {
    // setup
    final var testUnit = Mockito.mock(VoidnessRequestable.class);
    Mockito.when(testUnit.isEffectual()).thenCallRealMethod();
    Mockito.when(testUnit.isVoid()).thenReturn(true);

    // execute
    final var result = testUnit.isEffectual();

    // verify
    expect(result).isFalse();
  }
}
