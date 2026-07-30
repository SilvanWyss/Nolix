/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.attribute.mandatoryattribute;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;

/**
 * @author Silvan Wyss
 */
final class NameHolderTest extends StandardTest {
  @Test
  void testCase_getNameInSingleQuotes() {
    // setup
    final var testUnit = Mockito.mock(NameHolder.class);
    Mockito.when(testUnit.getNameInSingleQuotes()).thenCallRealMethod();
    Mockito.when(testUnit.getName()).thenReturn("name");

    // execute
    final var result = testUnit.getNameInSingleQuotes();

    // verify
    expect(result).isEqualTo("'name'");
  }

  @Test
  void testCase_hasName_whenHasTheGivenName() {
    // setup
    final var testUnit = Mockito.mock(NameHolder.class);
    Mockito.when(testUnit.hasName(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.getName()).thenReturn("name");

    // execute
    final var result = testUnit.hasName("name");

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_hasName_whenDoesNotHaveTheGivenName() {
    // setup
    final var testUnit = Mockito.mock(NameHolder.class);
    Mockito.when(testUnit.hasName(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.getName()).thenReturn("name");

    // execute
    final var result = testUnit.hasName("Name");

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_hasSameNameAs_whenHasTheSameName() {
    // setup step 1: create nameHolderMock
    final var nameHolderMock = Mockito.mock(NameHolder.class);
    Mockito.when(nameHolderMock.getName()).thenReturn("name");

    // setup step 2: create testUnit
    final var testUnit = Mockito.mock(NameHolder.class);
    Mockito.when(testUnit.hasSameNameAs(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.getName()).thenReturn("name");

    // execute
    final var result = testUnit.hasSameNameAs(nameHolderMock);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_hasSameNameAs_whenDoesNotHaveTheSameName() {
    // setup step 1: create nameHolderMock
    final var nameHolderMock = Mockito.mock(NameHolder.class);
    Mockito.when(nameHolderMock.getName()).thenReturn("Name");

    // setup step 2: create testUnit
    final var testUnit = Mockito.mock(NameHolder.class);
    Mockito.when(testUnit.hasSameNameAs(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.getName()).thenReturn("name");

    // execute
    final var result = testUnit.hasSameNameAs(nameHolderMock);

    // verify
    expect(result).isFalse();
  }
}
