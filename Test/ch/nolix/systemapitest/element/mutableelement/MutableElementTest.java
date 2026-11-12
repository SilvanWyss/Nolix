/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapitest.element.mutableelement;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.element.mutableelement.MutableElement;

/**
 * @author Silvan Wyss
 */
final class MutableElementTest extends StandardTest {
  @Test
  void testCase_addOrChangeAttributes() {
    // setup
    final var attribute1 = ImmutableNode.withHeader("a");
    final var attribute2 = ImmutableNode.withHeader("b");
    final var testUnit = Mockito.mock(MutableElement.class);
    Mockito.doCallRealMethod().when(testUnit).addOrChangeAttributes(ArgumentMatchers.any());

    // execute
    testUnit.addOrChangeAttributes(ImmutableList.withElements(attribute1, attribute2));

    // verify
    Mockito.verify(testUnit).addOrChangeAttribute(attribute1);
    Mockito.verify(testUnit).addOrChangeAttribute(attribute2);
  }

  @Test
  void testCase_addOrChangeAttributes_whenGivenAttributesIsNull() {
    // setup
    final var testUnit = Mockito.mock(MutableElement.class);
    Mockito.doCallRealMethod().when(testUnit).addOrChangeAttributes(ArgumentMatchers.any());

    // execute
    testUnit.addOrChangeAttributes(null);

    // verify
    Mockito.verify(testUnit, Mockito.never()).addOrChangeAttribute(ArgumentMatchers.any(Node.class));
  }

  @Test
  void testCase_resetFromAttributes() {
    // setup
    final var attribute1 = ImmutableNode.withHeader("a");
    final var attribute2 = ImmutableNode.withHeader("b");
    final var testUnit = Mockito.mock(MutableElement.class);
    Mockito.doCallRealMethod().when(testUnit).resetFromAttributes(ArgumentMatchers.any());
    Mockito.doCallRealMethod().when(testUnit).addOrChangeAttributes(ArgumentMatchers.any());

    // execute
    testUnit.resetFromAttributes(ImmutableList.withElements(attribute1, attribute2));

    // verify
    Mockito.verify(testUnit).reset();
    Mockito.verify(testUnit).addOrChangeAttribute(attribute1);
    Mockito.verify(testUnit).addOrChangeAttribute(attribute2);
  }

  @Test
  void testCase_resetFromSpecification() {
    // setup
    final var specification = ImmutableNode.withHeaderAndChildNodes("Element", "a", "b");
    final var testUnit = Mockito.mock(MutableElement.class);
    Mockito.doCallRealMethod().when(testUnit).resetFromSpecification(ArgumentMatchers.any());
    Mockito.doCallRealMethod().when(testUnit).resetFromAttributes(ArgumentMatchers.any());
    Mockito.doCallRealMethod().when(testUnit).addOrChangeAttributes(ArgumentMatchers.any());

    // execute
    testUnit.resetFromSpecification(specification);

    // verify
    Mockito.verify(testUnit).reset();
    Mockito.verify(testUnit).addOrChangeAttribute(ImmutableNode.withHeader("a"));
    Mockito.verify(testUnit).addOrChangeAttribute(ImmutableNode.withHeader("b"));
  }
}
