/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.attributeapi.mandatoryattributeapi;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.attribute.mandatoryattribute.IHeaderHolder;

/**
 * @author Silvan Wyss
 */
final class HeaderHolderTest extends StandardTest {
  @Test
  void testCase_getHeaderInSingleQuotes() {
    //setup
    final var testUnit = Mockito.mock(IHeaderHolder.class);
    Mockito.when(testUnit.getHeaderInSingleQuotes()).thenCallRealMethod();
    Mockito.when(testUnit.getHeader()).thenReturn("header");

    //execution
    final var result = testUnit.getHeaderInSingleQuotes();

    //verification
    expect(result).isEqualTo("'header'");
  }

  @Test
  void testCase_hasHeader_whenHasTheGivenHeader() {
    //setup
    final var testUnit = Mockito.mock(IHeaderHolder.class);
    Mockito.when(testUnit.hasHeader(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.getHeader()).thenReturn("header");

    //execution
    final var result = testUnit.hasHeader("header");

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_hasHeader_whenDoesNotHaveTheGivenHeader() {
    //setup
    final var testUnit = Mockito.mock(IHeaderHolder.class);
    Mockito.when(testUnit.hasHeader(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.getHeader()).thenReturn("header");

    //execution
    final var result = testUnit.hasHeader("Header");

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_hasSameHeaderAs_whenHasTheSameHeader() {
    //setup part 1: create headerHolderMock
    final var headerHolderMock = Mockito.mock(IHeaderHolder.class);
    Mockito.when(headerHolderMock.getHeader()).thenReturn("header");

    //setup part 2: create testUnit
    final var testUnit = Mockito.mock(IHeaderHolder.class);
    Mockito.when(testUnit.hasSameHeaderAs(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.getHeader()).thenReturn("header");

    //execution
    final var result = testUnit.hasSameHeaderAs(headerHolderMock);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_hasSameHeaderAs_whenDoesNotHaveTheSameHeader() {
    //setup part 1: create headerHolderMock
    final var headerHolderMock = Mockito.mock(IHeaderHolder.class);
    Mockito.when(headerHolderMock.getHeader()).thenReturn("Header");

    //setup part 2: create testUnit
    final var testUnit = Mockito.mock(IHeaderHolder.class);
    Mockito.when(testUnit.hasSameHeaderAs(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.getHeader()).thenReturn("header");

    //execution
    final var result = testUnit.hasSameHeaderAs(headerHolderMock);

    //verification
    expect(result).isFalse();
  }
}
