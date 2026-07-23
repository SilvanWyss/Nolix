/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.attribute.mandatoryattribute;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.attribute.mandatoryattribute.LabelHolder;

/**
 * @author Silvan Wyss
 */
final class LabelHolderTest extends StandardTest {
  @Test
  void testCase_getLabelInSingleQuotes() {
    // setup
    final var testUnit = Mockito.mock(LabelHolder.class);
    Mockito.when(testUnit.getLabelInSingleQuotes()).thenCallRealMethod();
    Mockito.when(testUnit.getLabel()).thenReturn("label");

    // execute
    final var result = testUnit.getLabelInSingleQuotes();

    // verify
    expect(result).isEqualTo("'label'");
  }
}
