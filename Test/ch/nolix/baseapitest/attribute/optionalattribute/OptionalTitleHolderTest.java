/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.attribute.optionalattribute;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.attribute.optionalattribute.OptionalTitleHolder;

/**
 * @author Silvan Wyss
 */
final class OptionalTitleHolderTest extends StandardTest {
  @Test
  void testCase_getTitleInSingleQuotes() {
    // setup
    final var testUnit = Mockito.mock(OptionalTitleHolder.class);
    Mockito.when(testUnit.getTitleInSingleQuotes()).thenCallRealMethod();
    Mockito.when(testUnit.hasTitle()).thenReturn(true);
    Mockito.when(testUnit.getTitle()).thenReturn("title");

    // execution
    final var result = testUnit.getTitleInSingleQuotes();

    // verification
    expect(result).isEqualTo("'title'");
  }
}
