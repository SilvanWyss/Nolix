/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.attribute.mandatoryattribute;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.attribute.mandatoryattribute.DatabaseNameHolder;

/**
 * @author Silvan Wyss
 */
final class DatabaseNameHolderTest extends StandardTest {
  @Test
  void testCase_getNameInSingleQuotes() {
    // setup
    final var testUnit = Mockito.mock(DatabaseNameHolder.class);
    Mockito.when(testUnit.getDatabaseNameInSingleQuotes()).thenCallRealMethod();
    Mockito.when(testUnit.getDatabaseName()).thenReturn("database");

   // execute
    final var result = testUnit.getDatabaseNameInSingleQuotes();

   // verify
    expect(result).isEqualTo("'database'");
  }
}
