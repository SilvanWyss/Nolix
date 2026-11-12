/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.element.base;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.systemapi.element.base.SpecificationRepresentable;

/**
 * @author Silvan Wyss
 */
final class SpecificationRepresentableTest extends StandardTest {
  @Test
  void testCase_hasEqualSpecificationAsElement_whenGivenElementHasAnEqualSpecification() {
    // setup step 1: create elementMock
    final var elementMock = Mockito.mock(SpecificationRepresentable.class);
    Mockito.doReturn(ImmutableNode.withHeaderAndChildNodes("a", "b", "c")).when(elementMock).getSpecification();

    // setup step 2: create testUnit
    final var testUnit = Mockito.mock(SpecificationRepresentable.class);
    Mockito.when(testUnit.hasEqualSpecificationAsElement(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.doReturn(ImmutableNode.withHeaderAndChildNodes("a", "b", "c")).when(testUnit).getSpecification();

    // execute
    final var result = testUnit.hasEqualSpecificationAsElement(elementMock);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_hasEqualSpecificationAsElement_whenGivenElementHasAnotherSpecification() {
    // setup step 1: create elementMock
    final var elementMock = Mockito.mock(SpecificationRepresentable.class);
    Mockito.doReturn(ImmutableNode.withHeaderAndChildNodes("a", "b", "c")).when(elementMock).getSpecification();

    // setup step 2: create testUnit
    final var testUnit = Mockito.mock(SpecificationRepresentable.class);
    Mockito.when(testUnit.hasEqualSpecificationAsElement(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.doReturn(ImmutableNode.withHeaderAndChildNodes("a", "b", "d")).when(testUnit).getSpecification();

    // execute
    final var result = testUnit.hasEqualSpecificationAsElement(elementMock);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_hasEqualSpecificationAsElement_whenGivenElementIsNull() {
    // setup
    final var testUnit = Mockito.mock(SpecificationRepresentable.class);
    Mockito.when(testUnit.hasEqualSpecificationAsElement(ArgumentMatchers.any())).thenCallRealMethod();
    Mockito.doReturn(ImmutableNode.withHeaderAndChildNodes("a", "b", "c")).when(testUnit).getSpecification();

    // execute
    final var result = testUnit.hasEqualSpecificationAsElement(null);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_toFormattedString() {
    // setup
    final var specification = ImmutableNode.withHeaderAndChildNodes("a", "b", "c");
    final var testUnit = Mockito.mock(SpecificationRepresentable.class);
    Mockito.when(testUnit.toFormattedString()).thenCallRealMethod();
    Mockito.doReturn(specification).when(testUnit).getSpecification();

    // execute
    final var result = testUnit.toFormattedString();

    // verify
    expect(result).isEqualTo(specification.toFormattedString());
  }
}
