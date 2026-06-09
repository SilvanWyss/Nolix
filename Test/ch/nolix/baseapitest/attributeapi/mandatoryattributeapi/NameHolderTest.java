/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.attributeapi.mandatoryattributeapi;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.attribute.mandatoryattribute.INameHolder;

/**
 * @author Silvan Wyss
 */
final class NameHolderTest extends StandardTest {
  @Test
  void testCase_getNameInSingleQuotes() {
    //setup
    final var testUnit = Mockito.mock(INameHolder.class);
    Mockito.when(testUnit.getNameInSingleQuotes()).thenCallRealMethod();
    Mockito.when(testUnit.getName()).thenReturn("name");

    //execution
    final var result = testUnit.getNameInSingleQuotes();

    //verification
    expect(result).isEqualTo("'name'");
  }

  @Test
  void testCase_hasName_whenHasTheGivenName() {
    //setup
    final var testUnit = Mockito.mock(INameHolder.class);
    Mockito.when(testUnit.hasName(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.getName()).thenReturn("name");

    //execution
    final var result = testUnit.hasName("name");

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_hasName_whenDoesNotHaveTheGivenName() {
    //setup
    final var testUnit = Mockito.mock(INameHolder.class);
    Mockito.when(testUnit.hasName(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.getName()).thenReturn("name");

    //execution
    final var result = testUnit.hasName("Name");

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_hasSameNameAs_whenHasTheSameName() {
    //setup part 1: create nameHolderMock
    final var nameHolderMock = Mockito.mock(INameHolder.class);
    Mockito.when(nameHolderMock.getName()).thenReturn("name");

    //setup part 2: create testUnit
    final var testUnit = Mockito.mock(INameHolder.class);
    Mockito.when(testUnit.hasSameNameAs(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.getName()).thenReturn("name");

    //execution
    final var result = testUnit.hasSameNameAs(nameHolderMock);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_hasSameNameAs_whenDoesNotHaveTheSameName() {
    //setup part 1: create nameHolderMock
    final var nameHolderMock = Mockito.mock(INameHolder.class);
    Mockito.when(nameHolderMock.getName()).thenReturn("Name");

    //setup part 2: create testUnit
    final var testUnit = Mockito.mock(INameHolder.class);
    Mockito.when(testUnit.hasSameNameAs(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.getName()).thenReturn("name");

    //execution
    final var result = testUnit.hasSameNameAs(nameHolderMock);

    //verification
    expect(result).isFalse();
  }
}
