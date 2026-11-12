/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.generalstate.staterequest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.generalstate.staterequest.MaterializationRequestable;

/**
 * @author Silvan Wyss
 */
final class MaterializationRequestableTest extends StandardTest {
  @Test
  void testCase_isView_whenIsMaterialized() {
    // setup
    final var testUnit = Mockito.mock(MaterializationRequestable.class);
    Mockito.when(testUnit.isView()).thenCallRealMethod();
    Mockito.when(testUnit.isMaterialized()).thenReturn(true);

    // execute
    final var result = testUnit.isView();

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isView_whenIsNotMaterialized() {
    // setup
    final var testUnit = Mockito.mock(MaterializationRequestable.class);
    Mockito.when(testUnit.isView()).thenCallRealMethod();
    Mockito.when(testUnit.isMaterialized()).thenReturn(false);

    // execute
    final var result = testUnit.isView();

    // verify
    expect(result).isTrue();
  }
}
