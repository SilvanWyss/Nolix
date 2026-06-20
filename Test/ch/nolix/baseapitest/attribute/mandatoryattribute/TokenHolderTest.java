/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.attribute.mandatoryattribute;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.attribute.mandatoryattribute.TokenHolder;

/**
 * @author Silvan Wyss
 */
final class TokenHolderTest extends StandardTest {
  @Test
  void testCase_hasToken_whenHasTheGivenToken() {
    //setup
    final var testUnit = Mockito.mock(TokenHolder.class);
    Mockito.when(testUnit.hasToken(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.getToken()).thenReturn("token");

    //execution
    final var result = testUnit.hasToken("token");

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_hasToken_whenDoesNotHaveTheGivenToken() {
    //setup
    final var testUnit = Mockito.mock(TokenHolder.class);
    Mockito.when(testUnit.hasToken(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.getToken()).thenReturn("token");

    //execution
    final var result = testUnit.hasToken("Token");

    //verification
    expect(result).isFalse();
  }
}
