/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.attribute.optionalattribute;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.attribute.optionalattribute.OptionalLabelHolder;

/**
 * @author Silvan Wyss
 */
final class OptionalLabelHolderTest extends StandardTest {
  @Test
  void testCase_getLabelInSingleQuotes() {
    // setup
    final var testUnit = Mockito.mock(OptionalLabelHolder.class);
    Mockito.when(testUnit.getLabelInSingleQuotes()).thenCallRealMethod();
    Mockito.when(testUnit.hasLabel()).thenReturn(true);
    Mockito.when(testUnit.getLabel()).thenReturn("label");

    // execution
    final var result = testUnit.getLabelInSingleQuotes();

    // verification
    expect(result).isEqualTo("'label'");
  }
}
