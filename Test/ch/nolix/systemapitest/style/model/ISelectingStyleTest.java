/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.style.model;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.systemapi.style.model.ISelectingStyle;
import ch.nolix.systemapi.style.stylable.StylableElement;

/**
 * @author Silvan Wyss
 */
final class ISelectingStyleTest extends StandardTest {
  @Test
  void testCase_skipsChildElements_whenDoesNotSelectChildElements() {
    // setup
    final ISelectingStyle<?> testUnit = Mockito.mock(ISelectingStyle.class);
    Mockito.when(testUnit.skipsChildElements()).thenCallRealMethod();
    Mockito.when(testUnit.selectsChildElements()).thenReturn(false);

    // execute
    final var result = testUnit.skipsChildElements();

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_skipsChildElements_whenSelectsChildElements() {
    // setup
    final ISelectingStyle<?> testUnit = Mockito.mock(ISelectingStyle.class);
    Mockito.when(testUnit.skipsChildElements()).thenCallRealMethod();
    Mockito.when(testUnit.selectsChildElements()).thenReturn(true);

    // execute
    final var result = testUnit.skipsChildElements();

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_skipsElement_whenDoesNotSelectTheGivenElement() {
    // setup
    final var element = Mockito.mock(StylableElement.class);
    final ISelectingStyle<?> testUnit = Mockito.mock(ISelectingStyle.class);
    Mockito.when(testUnit.skipsElement(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.selectsElement(element)).thenReturn(false);

    // execute
    final var result = testUnit.skipsElement(element);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_skipsElement_whenSelectsTheGivenElement() {
    // setup
    final var element = Mockito.mock(StylableElement.class);
    final ISelectingStyle<?> testUnit = Mockito.mock(ISelectingStyle.class);
    Mockito.when(testUnit.skipsElement(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.when(testUnit.selectsElement(element)).thenReturn(true);

    // execute
    final var result = testUnit.skipsElement(element);

    // verify
    expect(result).isFalse();
  }
}
