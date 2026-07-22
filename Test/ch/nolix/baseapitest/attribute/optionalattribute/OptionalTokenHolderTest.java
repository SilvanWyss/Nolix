/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.attribute.optionalattribute;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.attribute.optionalattribute.OptionalTokenHolder;

/**
 * @author Silvan Wyss
 */
final class OptionalTokenHolderTest extends StandardTest {
  @Test
  void testCase_hasToken_whenHasTheGivenToken() {
    // setup
    final var testUnit = Mockito.mock(OptionalTokenHolder.class);
    Mockito.when(testUnit.hasToken(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.hasToken()).thenReturn(true);
    Mockito.when(testUnit.getToken()).thenReturn("token");

   // execute
    final var result = testUnit.hasToken("token");

   // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_hasToken_whenHasAnotherToken() {
    // setup
    final var testUnit = Mockito.mock(OptionalTokenHolder.class);
    Mockito.when(testUnit.hasToken(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.hasToken()).thenReturn(true);
    Mockito.when(testUnit.getToken()).thenReturn("token");

   // execute
    final var result = testUnit.hasToken("Token");

   // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_hasToken_whenDoesNotHaveAToken() {
    // setup
    final var testUnit = Mockito.mock(OptionalTokenHolder.class);
    Mockito.when(testUnit.hasToken(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.hasToken()).thenReturn(false);

   // execute
    final var result = testUnit.hasToken("token");

   // verify
    expect(result).isFalse();
  }
}
