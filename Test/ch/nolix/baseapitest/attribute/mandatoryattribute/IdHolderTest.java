/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.attribute.mandatoryattribute;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.attribute.mandatoryattribute.IdHolder;

/**
 * @author Silvan Wyss
 */
final class IdHolderTest extends StandardTest {
  @Test
  void testCase_getIdInSingleQuotes() {
    // setup
    final var testUnit = Mockito.mock(IdHolder.class);
    Mockito.when(testUnit.getIdInSingleQuotes()).thenCallRealMethod();
    Mockito.when(testUnit.getId()).thenReturn("id");

   // execute
    final var result = testUnit.getIdInSingleQuotes();

   // verify
    expect(result).isEqualTo("'id'");
  }

  @Test
  void testCase_hasId_whenHasTheGivenId() {
    // setup
    final var testUnit = Mockito.mock(IdHolder.class);
    Mockito.when(testUnit.hasId(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.getId()).thenReturn("id");

   // execute
    final var result = testUnit.hasId("id");

   // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_hasId_whenDoesNotHaveTheGivenId() {
    // setup
    final var testUnit = Mockito.mock(IdHolder.class);
    Mockito.when(testUnit.hasId(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.getId()).thenReturn("id");

   // execute
    final var result = testUnit.hasId("Id");

   // verify
    expect(result).isFalse();
  }
}
