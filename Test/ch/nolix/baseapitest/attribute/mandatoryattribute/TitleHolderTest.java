/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.attribute.mandatoryattribute;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.attribute.mandatoryattribute.TitleHolder;

/**
 * @author Silvan Wyss
 */
final class TitleHolderTest extends StandardTest {
  @Test
  void testCase_getTitleInSingleQuotes() {
    // setup
    final var testUnit = Mockito.mock(TitleHolder.class);
    Mockito.when(testUnit.getTitleInSingleQuotes()).thenCallRealMethod();
    Mockito.when(testUnit.getTitle()).thenReturn("title");

    // execution
    final var result = testUnit.getTitleInSingleQuotes();

    // verification
    expect(result).isEqualTo("'title'");
  }
}
