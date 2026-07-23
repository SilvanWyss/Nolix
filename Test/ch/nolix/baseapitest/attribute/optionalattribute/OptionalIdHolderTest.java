/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.attribute.optionalattribute;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.attribute.optionalattribute.OptionalIdHolder;

/**
 * @author Silvan Wyss
 */
final class OptionalIdHolderTest extends StandardTest {
  @Test
  void testCase_getIdInSingleQuotes() {
    // setup
    final var testUnit = Mockito.mock(OptionalIdHolder.class);
    Mockito.when(testUnit.getIdInSingleQuotes()).thenCallRealMethod();
    Mockito.when(testUnit.hasId()).thenReturn(true);
    Mockito.when(testUnit.getId()).thenReturn("id");

    // execute
    final var result = testUnit.getIdInSingleQuotes();

    // verify
    expect(result).isEqualTo("'id'");
  }

  @Test
  void testCase_hasId_whenHasTheGivenId() {
    // setup
    final var testUnit = Mockito.mock(OptionalIdHolder.class);
    Mockito.when(testUnit.hasId(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.hasId()).thenReturn(true);
    Mockito.when(testUnit.getId()).thenReturn("id");

    // execute
    final var result = testUnit.hasId("id");

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_hasId_whenHasAnotherId() {
    // setup
    final var testUnit = Mockito.mock(OptionalIdHolder.class);
    Mockito.when(testUnit.hasId(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.hasId()).thenReturn(true);
    Mockito.when(testUnit.getId()).thenReturn("id");

    // execute
    final var result = testUnit.hasId("Id");

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_hasId_whenDoesNotHaveAId() {
    // setup
    final var testUnit = Mockito.mock(OptionalIdHolder.class);
    Mockito.when(testUnit.hasId(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.hasId()).thenReturn(false);

    // execute
    final var result = testUnit.hasId("id");

    // verify
    expect(result).isFalse();
  }
}
