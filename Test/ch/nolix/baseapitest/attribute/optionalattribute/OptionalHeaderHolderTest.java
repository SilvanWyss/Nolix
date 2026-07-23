/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.attribute.optionalattribute;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.attribute.optionalattribute.OptionalHeaderHolder;

/**
 * @author Silvan Wyss
 */
final class OptionalHeaderHolderTest extends StandardTest {
  @Test
  void testCase_getHeaderOrEmptyString_whenHasAHeader() {
    // setup
    final var testUnit = Mockito.mock(OptionalHeaderHolder.class);
    Mockito.when(testUnit.getHeaderOrEmptyString()).thenCallRealMethod();
    Mockito.when(testUnit.hasHeader()).thenReturn(true);
    Mockito.when(testUnit.getHeader()).thenReturn("header");

    // execute
    final var result = testUnit.getHeaderOrEmptyString();

    // verify
    expect(result).isEqualTo("header");
  }

  @Test
  void testCase_getHeaderOrEmptyString_whenDoesNotHaveAHeader() {
    // setup
    final var testUnit = Mockito.mock(OptionalHeaderHolder.class);
    Mockito.when(testUnit.getHeaderOrEmptyString()).thenCallRealMethod();
    Mockito.when(testUnit.hasHeader()).thenReturn(false);

    // execute
    final var result = testUnit.getHeaderOrEmptyString();

    // verify
    expect(result).isEmpty();
  }

  @Test
  void testCase_getOptionalHeader_whenHasAHeader() {
    // setup
    final var testUnit = Mockito.mock(OptionalHeaderHolder.class);
    Mockito.when(testUnit.getOptionalHeader()).thenCallRealMethod();
    Mockito.when(testUnit.hasHeader()).thenReturn(true);
    Mockito.when(testUnit.getHeader()).thenReturn("header");

    // execute
    final var result = testUnit.getOptionalHeader();

    // verify
    expect(result).containsEqualObject("header");
  }

  @Test
  void testCase_getOptionalHeader_whenDoesNotHaveAHeader() {
    // setup
    final var testUnit = Mockito.mock(OptionalHeaderHolder.class);
    Mockito.when(testUnit.getOptionalHeader()).thenCallRealMethod();
    Mockito.when(testUnit.hasHeader()).thenReturn(false);

    // execute
    final var result = testUnit.getOptionalHeader();

    // verify
    expect(result).isEmpty();
  }

  @Test
  void testCase_hasHeader_whenHasTheGivenHeader() {
    // setup
    final var testUnit = Mockito.mock(OptionalHeaderHolder.class);
    Mockito.when(testUnit.hasHeader(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.hasHeader()).thenReturn(true);
    Mockito.when(testUnit.getHeader()).thenReturn("header");

    // execute
    final var result = testUnit.hasHeader("header");

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_hasHeader_whenHasAnotherHeader() {
    // setup
    final var testUnit = Mockito.mock(OptionalHeaderHolder.class);
    Mockito.when(testUnit.hasHeader(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.hasHeader()).thenReturn(true);
    Mockito.when(testUnit.getHeader()).thenReturn("header");

    // execute
    final var result = testUnit.hasHeader("Header");

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_hasHeader_whenDoesNotHaveAHeader() {
    // setup
    final var testUnit = Mockito.mock(OptionalHeaderHolder.class);
    Mockito.when(testUnit.hasHeader(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.hasHeader()).thenReturn(false);

    // execute
    final var result = testUnit.hasHeader("header");

    // verify
    expect(result).isFalse();
  }
}
