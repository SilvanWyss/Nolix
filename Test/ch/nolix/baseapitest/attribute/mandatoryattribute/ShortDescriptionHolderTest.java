/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.attribute.mandatoryattribute;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.attribute.mandatoryattribute.ShortDescriptionHolder;

/**
 * @author Silvan Wyss
 */
final class ShortDescriptionHolderTest extends StandardTest {
  @Test
  void testCase_getShortDescriptionInSingleQuotes() {
    // setup
    final var testUnit = Mockito.mock(ShortDescriptionHolder.class);
    Mockito.when(testUnit.getShortDescriptionInSingleQuotes()).thenCallRealMethod();
    Mockito.when(testUnit.getShortDescription()).thenReturn("short_description");

    // execute
    final var result = testUnit.getShortDescriptionInSingleQuotes();

    // verify
    expect(result).isEqualTo("'short_description'");
  }
}
