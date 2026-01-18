/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.element.base;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.system.element.base.AbstractElement;

/**
 * @author Silvan Wyss
 */
final class ElementTest extends StandardTest {
  @Test
  void testCase_hashCode() {
    //setup
    final var testUnit = new AbstractElement() {
      @Override
      public IContainer<INode<?>> getAttributes() {
        return ImmutableList.withElements(Node.withHeader("my_flag"));
      }
    };

    //verification setup
    final var expectedResult = testUnit.getSpecification().hashCode();

    //execution
    final var result = testUnit.hashCode();

    //verification
    expect(result).isEqualTo(expectedResult);
  }
}
