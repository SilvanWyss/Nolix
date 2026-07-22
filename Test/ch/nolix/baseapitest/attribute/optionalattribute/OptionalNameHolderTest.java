/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.attribute.optionalattribute;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.attribute.optionalattribute.OptionalNameHolder;

/**
 * @author Silvan Wyss
 */
final class OptionalNameHolderTest extends StandardTest {
  @Test
  void testCase_getNameInSingleQuotes() {
    // setup
    final var testUnit = Mockito.mock(OptionalNameHolder.class);
    Mockito.when(testUnit.getNameInSingleQuotes()).thenCallRealMethod();
    Mockito.when(testUnit.hasName()).thenReturn(true);
    Mockito.when(testUnit.getName()).thenReturn("name");

   // execute
    final var result = testUnit.getNameInSingleQuotes();

   // verify
    expect(result).isEqualTo("'name'");
  }

  @Test
  void testCase_hasName_whenHasTheGivenName() {
    // setup
    final var testUnit = Mockito.mock(OptionalNameHolder.class);
    Mockito.when(testUnit.hasName(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.hasName()).thenReturn(true);
    Mockito.when(testUnit.getName()).thenReturn("name");

   // execute
    final var result = testUnit.hasName("name");

   // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_hasName_whenHasAnotherName() {
    // setup
    final var testUnit = Mockito.mock(OptionalNameHolder.class);
    Mockito.when(testUnit.hasName(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.hasName()).thenReturn(true);
    Mockito.when(testUnit.getName()).thenReturn("name");

   // execute
    final var result = testUnit.hasName("Name");

   // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_hasName_whenDoesNotHaveAName() {
    // setup
    final var testUnit = Mockito.mock(OptionalNameHolder.class);
    Mockito.when(testUnit.hasName(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.hasName()).thenReturn(false);

   // execute
    final var result = testUnit.hasName("name");

   // verify
    expect(result).isFalse();
  }
}
