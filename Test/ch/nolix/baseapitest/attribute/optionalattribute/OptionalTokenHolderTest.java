/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.attribute.optionalattribute;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.attribute.optionalattribute.IOptionalTokenHolder;

/**
 * @author Silvan Wyss
 */
final class OptionalTokenHolderTest extends StandardTest {
  @Test
  void testCase_hasToken_whenHasTheGivenToken() {
    //setup
    final var testUnit = Mockito.mock(IOptionalTokenHolder.class);
    Mockito.when(testUnit.hasToken(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.hasToken()).thenReturn(true);
    Mockito.when(testUnit.getToken()).thenReturn("token");

    //execution
    final var result = testUnit.hasToken("token");

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_hasToken_whenHasAnotherToken() {
    //setup
    final var testUnit = Mockito.mock(IOptionalTokenHolder.class);
    Mockito.when(testUnit.hasToken(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.hasToken()).thenReturn(true);
    Mockito.when(testUnit.getToken()).thenReturn("token");

    //execution
    final var result = testUnit.hasToken("Token");

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_hasToken_whenDoesNotHaveAToken() {
    //setup
    final var testUnit = Mockito.mock(IOptionalTokenHolder.class);
    Mockito.when(testUnit.hasToken(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.hasToken()).thenReturn(false);

    //execution
    final var result = testUnit.hasToken("token");

    //verification
    expect(result).isFalse();
  }
}
